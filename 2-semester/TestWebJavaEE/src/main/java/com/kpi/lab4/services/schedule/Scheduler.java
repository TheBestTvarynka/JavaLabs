package com.kpi.lab4.services.schedule;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Scheduler {
    private static Timer timer = new Timer();

    public static void scheduleJob(TimerTask job, Date date) {
        timer.schedule(job, date);
    }
}
