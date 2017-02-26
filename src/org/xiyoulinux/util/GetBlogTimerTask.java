package org.xiyoulinux.util;

/**
 * Created by zhoupan on 11/30/16.
 */

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

public class GetBlogTimerTask extends TimerTask {
    @Override
    public void run() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.print("run task time:"+ df.format(new Date()));
        try {
            Blogjson.saveBlog();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
