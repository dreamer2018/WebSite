import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;


/**
 * Created by zhoupan on 11/6/16.
 */
public class DomTest {
    public static void main(String[] args) throws IOException{
        Document doc = Jsoup.connect("http://blog.xiyoulinux.org").get();
        Element bbox = doc.getElementById("bbox");
        Elements vbox = bbox.getElementsByClass("vbox");
        for(int i=0;i < vbox.size(); i++){
            Elements node = vbox.get(i).getElementsByClass("node");
            Elements link = vbox.get(i).getElementsByClass("more-link");
            Elements fsize = vbox.get(i).getElementsByClass("fsize");
            Elements title = fsize.get(0).getElementsByTag("a");
            Elements afont = vbox.get(i).getElementsByClass("afont");
            Elements time = vbox.get(i).getElementsByClass("time");
            System.out.println("地址："+link.get(0).attr("href"));
            System.out.println("标题："+title.get(0).text());
            System.out.println("作者："+afont.get(0).text());
            System.out.println("时间："+time.get(0).text());
            System.out.println("内容："+node.get(0).text());
            System.out.println("_______________________________________________");
        }
    }
}
