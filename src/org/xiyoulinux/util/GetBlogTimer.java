package org.xiyoulinux.util;

/**
 * Created by zhoupan on 11/30/16.
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;

public class GetBlogTimer {
    public Timer timer;

    public void timerStart() {

        timer = new Timer();

        Date datetime = new Date();
        Date midnightDate = new Date();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            midnightDate = sdf2.parse(sdf1.format(datetime) + " 23:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long in = midnightDate.getTime() - datetime.getTime();

        System.out.println("before task");
        //立刻执行，然后每隔30s执行一次
        timer.schedule(new GetBlogTimerTask(), 0, 30000);
    }

    public void timerStop() {
        if (timer != null)
            timer.cancel();
    }

    public static void main(String[] args) {
        GetBlogTimer myTimer = new GetBlogTimer();
        myTimer.timerStart();
    }
}