/**
 * Created by zhoupan on 10/25/16.
 */
import org.xiyoulinux.dao.UserDAO;
import org.xiyoulinux.idao.Iuser;
import org.xiyoulinux.model.User;
public class UserTest {
    public static void main(String[] args) {
        UserDAO userdao = new UserDAO();
        User u = userdao.getUserByName("zhoupan");
        System.out.println(u.getName());
        System.out.println(u.getPasswd());
    }
}
