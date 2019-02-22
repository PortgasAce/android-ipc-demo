package com.portgas.app.ipc;

import android.app.Application;
import android.util.Log;

public class IPCApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();

    Log.d(Constant.TAG, "onCreate: " + ProcessUtil.getProcessName(this));
  }
}
