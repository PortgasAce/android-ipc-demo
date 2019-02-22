package com.portgas.app.ipc;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import java.util.List;

public class ProcessUtil {


  public static String getProcessName(Context context) {
    try {
      ActivityManager activityManager = (ActivityManager) context
          .getSystemService(Context.ACTIVITY_SERVICE);
      List<RunningAppProcessInfo> processes = activityManager
          .getRunningAppProcesses();
      for (RunningAppProcessInfo info : processes) {
        if (info.pid == android.os.Process.myPid()) {
          return info.processName;
        }
      }
    } catch (Exception ex) {
      if (BuildConfig.DEBUG) {
        ex.printStackTrace();
      }
    }
    return "unknown";
  }

}
