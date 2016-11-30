package org.xiyoulinux.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by zhoupan on 11/30/16.
 */

public class MyTimerListener implements ServletContextListener {
    private GetBlogTimer mytimer = new GetBlogTimer();
    public void contextInitialized(ServletContextEvent event) {
        mytimer.timerStart();
    }
    public void contextDestroyed(ServletContextEvent event) {
        mytimer.timerStop();
    }
}