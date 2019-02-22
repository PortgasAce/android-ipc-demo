package com.portgas.app.ipc.binder;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import com.portgas.app.ipc.Constant;
//import com.portgas.app.ipc.MsgData;

public class BinderService extends IntentService {

  public BinderService() {
    super("binder");
  }

  @Override
  public IBinder onBind(Intent intent) {
    // if return null , ServiceConnection.onServiceConnected will not called!
    // return null;
    Log.d(Constant.TAG, "onBind: " + mMessenger + ",binder " + mMessenger.getBinder());
    return mMessenger.getBinder();
  }

  @Override
  protected void onHandleIntent(Intent intent) {

  }

  @SuppressLint("HandlerLeak")
  private Messenger mMessenger = new Messenger(new Handler() {
    @Override
    public void handleMessage(Message msg) {

      switch (msg.what) {
        case Constant
            .MSG_FROM_CLIENT:
          Message message = Message.obtain();
          message.what = Constant.MSG_FROM_SERVER;
          Bundle bundle = new Bundle();
          bundle.putString(Constant.KEY_SERVER,
              "server receive " + msg.getData().getString(Constant.KEY_CLIENT));
          message.setData(bundle);
          message.replyTo = mMessenger;
          Log.d(Constant.TAG, "server handleMessage: replyTo = " + mMessenger);
          try {
            msg.replyTo.send(message);
          } catch (RemoteException e) {
            e.printStackTrace();
          }
          break;
      }
      super.handleMessage(msg);
    }
  });
}
