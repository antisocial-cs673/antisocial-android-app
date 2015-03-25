package com.antisocial.app.util;

import android.app.ActivityManager;
import android.os.Build;

/**
 * Created by Igibek on 3/25/2015.
 */
public class ApiAdapter {

    public static String getPackageName(ActivityManager activityManager)
    {
        if(Build.VERSION.SDK_INT > 20)
        {
            return activityManager.getRunningAppProcesses().get(0).processName;
        }
        else
        {
            return activityManager.getRunningTasks(1).get(0).topActivity.getPackageName();
        }

    }
}
