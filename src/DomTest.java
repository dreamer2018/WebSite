
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xiyoulinux.dao.BlogDAO;
import org.xiyoulinux.model.Blog;
import sun.swing.BakedArrayList;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * Created by zhoupan on 11/6/16.
 */
public class DomTest {
    public static void main(String[] args) {
        ArrayList<Blog> blogs = DomTest.getBlogFromHtml();
        if (null != blogs) {
            for (Blog blog : blogs) {
                System.out.println("地址：" + blog.getUrl());
                System.out.println("标题：" + blog.getTitle());
                System.out.println("作者：" + blog.getAuthor());
                System.out.println("时间：" + blog.getDate() + " " + blog.getTime());
                System.out.println("内容：" + blog.getSummary());
                System.out.println("__________________________________________");
            }
        }
    }

    private static ArrayList<Blog> getBlogFromHtml() {
        Document doc;
        try {
            doc = Jsoup.connect("http://blog.xiyoulinux.org").get();
        } catch (IOException ie) {
            System.out.println("\nGet Blog From http://blog.xiyoulinux.org");
            System.out.println("JSON Resolve Error :  " + ie.toString());
            System.out.println("================================================");
            return null;
        }
        Element bbox = doc.getElementById("bbox");
        Elements vbox = bbox.getElementsByClass("vbox");
        ArrayList<Blog> blogs = new ArrayList<>();
        for (int i = 0; i < vbox.size(); i++) {
            Blog blog = new Blog();
            Elements node = vbox.get(i).getElementsByClass("node");
            Elements link = vbox.get(i).getElementsByClass("more-link");
            Elements fsize = vbox.get(i).getElementsByClass("fsize");
            Elements title = fsize.get(0).getElementsByTag("a");
            Elements afont = vbox.get(i).getElementsByClass("afont");
            Elements time = vbox.get(i).getElementsByClass("time");
            SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
            Date date;
            try {
                date = sdf.parse(time.get(0).text());
            } catch (ParseException pe) {
                System.out.println("\nGet Blog From http://blog.xiyoulinux.org");
                System.out.println("JSON Resolve Error :  " + pe.toString());
                System.out.println("================================================");
                return null;
            }
            SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat t = new SimpleDateFormat("HH:mm:ss");
            blog.setTitle(title.get(0).text());
            blog.setAuthor(afont.get(0).text());
            blog.setUrl("http://blog.xiyoulinux.org/" + link.get(0).attr("href"));
            blog.setDate(d.format(date));
            blog.setTime(t.format(date));
            blog.setSummary(node.get(0).text());
            blogs.add(blog);
        }
        return blogs;
    }
}
