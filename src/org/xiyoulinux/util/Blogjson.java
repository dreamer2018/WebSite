package org.xiyoulinux.util;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.xiyoulinux.dao.BlogDAO;
import org.xiyoulinux.model.Blog;
import org.json.JSONException;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by zhoupan on 11/30/16.
 */
public class Blogjson {
    public static ArrayList<Blog> getBlogFromJson() {
        Document doc;
        //如果地址访问错误，则跳过，不影响定时器继续执行
        try {
            doc = Jsoup.connect("http://blog.xiyoulinux.org/blogjson").get();
        } catch (IOException ie) {
            System.out.println("\nGet Blog From http://blog.xiyoulinux.org/blogjson");
            System.out.println("JSON Resolve Error :  " + ie.toString());
            System.out.println("================================================");
            return null;
        }
        Elements js = doc.getElementsByTag("body");
        String jsonString = js.html();
        ArrayList<Blog> blogs = new ArrayList<>();
        JSONObject jsonObjects;
        //如果获取的json数据出错，则直接跳过
        try {
            jsonObjects = new JSONObject(jsonString);
        } catch (JSONException je) {
            System.out.println("\nGet Blog From http://blog.xiyoulinux.org/blogjson");
            System.out.println("JSON Resolve Error :  " + je.toString());
            System.out.println("================================================");
            return null;
        }
        for (int i = 0; i < 10; i++) {
            String key = "blog-" + i;
            JSONObject jsonObject = jsonObjects.getJSONObject(key);
            String datetime = jsonObject.getString("PubDate");
            SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
            Date date = null;
            SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat t = new SimpleDateFormat("HH:mm:ss");
            try {
                date = sdf.parse(datetime);
            } catch (ParseException e) {
                date = new Date();
            }
            Blog blog = new Blog();
            // 获取相关属性
            String title = jsonObject.getString("Title");
            String author = jsonObject.getString("Author");
            String link = jsonObject.getString("BlogArticleLink");
            //数据库设计，最大长度为50
            if (title.length() > 50) {
                title = title.substring(0, 49);
            }
            //数据库设计，最大长度为20
            if (author.length() > 20) {
                author = author.substring(0, 19);
            }
            //数据库设计，最大长度为256
            if (link.length() > 256) {
                link = link.substring(0, 255);
            }
            blog.setTitle(title);
            blog.setAuthor(author);
            blog.setUrl(link);
            blog.setDate(d.format(date));
            blog.setTime(t.format(date));
            blog.setSummary(jsonObject.getString("ArticleDetail"));
            //写入数据库
            blogs.add(blog);
        }
        return blogs;
    }

    public static void saveBlog() throws IOException, ParseException {
        BlogDAO blogDAO = new BlogDAO();
        ArrayList<Blog> blogs = getBlogFromJson();
        if (null == blogs) {
            return;
        }
        System.out.println("\nGet Blog From http://blog.xiyoulinux.org/blogjson");
        System.out.println("Blog Count:\t" + blogs.size());
        System.out.println("================================================");
        for (Blog blog : blogs) {
            // 看文章里面有没有此文章，有的话，就不再存储
            ArrayList<Blog> b = blogDAO.getBlogByTitle(blog.getTitle());
            // 如果没有，则保存
            if (b.size() == 0) {
                blogDAO.insert(blog);
            }
        }
    }
}
