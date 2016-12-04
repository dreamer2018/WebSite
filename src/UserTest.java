/**
 * Created by zhoupan on 10/25/16.
 */
import org.xiyoulinux.dao.BlogDAO;
import org.xiyoulinux.dao.UserDAO;
import org.xiyoulinux.idao.Iuser;
import org.xiyoulinux.model.Blog;
import org.xiyoulinux.model.User;

import java.util.ArrayList;

public class UserTest {
    public static void main(String[] args) {
//        String string = "你好，dajia,秋,天,,hwfgrvegvb";
//        System.out.println(string);
//        String str = "";
//        for(int i=0; i < string.length(); i++){
//            if(string.charAt(i) == ',' || string.charAt(i) == '，'){
//                if(!(str.equals("") || str.equals(",") || str.equals("，"))) {
//                    System.out.println("Test:" + str);
//                }
//                str="";
//            }else {
//                str+=string.charAt(i);
//            }
//        }
//        if(!(str.equals("") || str.equals(",") || str.equals("，"))){
//            System.out.println(str);
//        }
        BlogDAO blogDAO = new BlogDAO();
        ArrayList<Blog> list = new ArrayList<>();
        blogDAO.getBlogByPage(1,"lala");
        System.out.println(list.size());
    }
}
