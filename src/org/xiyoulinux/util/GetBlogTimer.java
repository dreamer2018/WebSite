package org.xiyoulinux.util;

/**
 * Created by zhoupan on 11/30/16.
 */

import java.util.Timer;

public class GetBlogTimer {
    public Timer timer;

    public void timerStart() {

        timer = new Timer();

        System.out.println("before task");
        //立刻执行，然后每隔30s执行一次
        timer.schedule(new GetBlogTimerTask(), 10, 1000*3600*6);
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