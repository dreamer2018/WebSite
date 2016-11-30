package org.xiyoulinux.util;

/**
 * Created by zhoupan on 11/30/16.
 */

import java.io.IOException;
import java.text.ParseException;
import java.util.TimerTask;

public class GetBlogTimerTask extends TimerTask {
    @Override
    public void run() {
        System.out.print("run task");
        try {
            Blogjson.saveBlog();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
