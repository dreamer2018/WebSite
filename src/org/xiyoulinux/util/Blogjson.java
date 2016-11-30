package org.xiyoulinux.util;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.xiyoulinux.dao.BlogDAO;
import org.xiyoulinux.model.Blog;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by zhoupan on 11/30/16.
 */
public class Blogjson {
    public static ArrayList<Blog> getBlogFromJson() throws IOException, ParseException {
        Document doc = Jsoup.connect("http://blog.xiyoulinux.org/blogjson").get();
        Elements js = doc.getElementsByTag("body");
        String jsonString = js.html();
        ArrayList<Blog> blogs = new ArrayList<>();
        JSONObject jsonObjects = new JSONObject(jsonString);
        for (int i = 0; i < 10; i++) {
            String key = "blog-" + i;
            JSONObject jsonObject = jsonObjects.getJSONObject(key);
            String datetime = jsonObject.getString("PubDate");
            SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
            Date date = sdf.parse(datetime);
            SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat t = new SimpleDateFormat("HH:mm:ss");

            Blog blog = new Blog();
            // 获取相关属性
            blog.setTitle(jsonObject.getString("Title"));
            blog.setAuthor(jsonObject.getString("Author"));
            blog.setUrl(jsonObject.getString("BlogArticleLink"));
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
