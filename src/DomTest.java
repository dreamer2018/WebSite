
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xiyoulinux.dao.BlogDAO;
import org.xiyoulinux.model.Blog;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by zhoupan on 11/6/16.
 */
public class DomTest {
    public static void main(String[] args) throws IOException, ParseException {
        Document doc = Jsoup.connect("http://blog.xiyoulinux.org/blogjson").get();
        Elements js = doc.getElementsByTag("body");
        String jsonString = js.html();
        JSONObject jsonObjects = new JSONObject(jsonString);
        for (int i = 0; i < 10; i++) {
            String key = "blog-" + i;
            System.out.println(key);
            JSONObject jsonObject = jsonObjects.getJSONObject(key);
            System.out.println(jsonObject.getString("PubDate"));
            System.out.println(jsonObject.getString("BlogArticleLink"));
            System.out.println(jsonObject.getString("Title"));
            System.out.println(jsonObject.getString("Author"));
            System.out.println(jsonObject.getString("ArticleDetail"));
        }
    }
}

//        Element bbox = doc.getElementById("bbox");
//        Elements vbox = bbox.getElementsByClass("vbox");
//        for (int i = 0; i < vbox.size(); i++) {
//            Elements node = vbox.get(i).getElementsByClass("node");
//            Elements link = vbox.get(i).getElementsByClass("more-link");
//            Elements fsize = vbox.get(i).getElementsByClass("fsize");
//            Elements title = fsize.get(0).getElementsByTag("a");
//            Elements afont = vbox.get(i).getElementsByClass("afont");
//            Elements time = vbox.get(i).getElementsByClass("time");
//            System.out.println("地址：" + link.get(0).attr("href"));
//            System.out.println("标题：" + title.get(0).text());
//            System.out.println("作者：" + afont.get(0).text());
//            System.out.println("时间：" + time.get(0).text());
//            System.out.println("内容：" + node.get(0).text());
//            System.out.println("_______________________________________________");
//
//            SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
//            Date date = sdf.parse(time.get(0).text());
//            SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
//            SimpleDateFormat t = new SimpleDateFormat("HH:mm:ss");
//            BlogDAO blogDAO = new BlogDAO();
//            Blog blog = new Blog();
//            blog.setTitle(title.get(0).text());
//            blog.setAuthor(afont.get(0).text());
//            blog.setDate(d.format(date));
//            blog.setTime(t.format(date));
//            blog.setSummary(node.get(0).text());
//            blog.setUrl(link.get(0).attr("href"));
////            blogDAO.insert(blog);
////        }
//    }
//}
