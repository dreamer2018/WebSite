package org.xiyoulinux.util;

/**
 * Created by zhoupan on 11/30/16.
 */

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HtmlUtil {
    public static String getTextFromTHML(String htmlStr) {
        Document doc = Jsoup.parse(htmlStr);
        String text = doc.text();
        // remove extra white space
        StringBuilder builder = new StringBuilder(text);
        int index = 0;
        while (builder.length() > index) {
            char tmp = builder.charAt(index);
            if (Character.isSpaceChar(tmp) || Character.isWhitespace(tmp)) {
                builder.setCharAt(index, ' ');
            }
            index++;
        }
        //去除< > ' " 防止漏网之鱼
        text = builder.toString().replaceAll(" +", " ").trim();
        String text1 = text.replace("<", "&lt;");
        String text2 = text1.replace(">", "&gt;");
        String text3 = text2.replace("'", "&amp;");
        return  text3.replace("\"", "&quot;");
    }

    public static void main(String[] args) {
        String text = "\"/><script>alert(\"haha\");</script><!--ssssssssssssssss";
        System.out.println(HtmlUtil.getTextFromTHML("fdsfdsfsvf>><script></script>&lt;&gt;"));
    }
}