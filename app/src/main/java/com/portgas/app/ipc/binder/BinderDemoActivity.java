package com.portgas.app.ipc.binder;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.portgas.app.ipc.Constant;
import com.portgas.app.ipc.R;

public class BinderDemoActivity extends AppCompatActivity implements View.OnClickListener {

  private Messenger mServerMessenger;
  @SuppressLint("HandlerLeak")
  private Messenger mClientMessenger = new Messenger(new Handler() {
    @Override
    public void handleMessage(Message msg) {
      super.handleMessage(msg);
      switch (msg.what) {
        case Constant.MSG_FROM_SERVER:
          Log.d(Constant.TAG,
              "client handleMessage: replyTo " + msg.replyTo + ",response = "
                  + msg.getData().getString(Constant.KEY_SERVER));
          break;
      }
    }
  });


  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_binder);

    findViewById(R.id.bind_service).setOnClickListener(this);
    findViewById(R.id.unbind_service).setOnClickListener(this);
    findViewById(R.id.send_msg).setOnClickListener(this);
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.bind_service:
        bindBinderService();
        break;
      case R.id.unbind_service:
        unbindBinderService();
        break;
      case R.id.send_msg:
        sendMsg();
        break;
    }
  }

  ServiceConnection mServiceConnection = new ServiceConnection() {
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
      Toast.makeText(BinderDemoActivity.this, "connect", Toast.LENGTH_LONG).show();
      mServerMessenger = new Messenger(service);
      Log.d(Constant.TAG,
          "onServiceConnected: name = " + name + ", service = " + service + ", messenger = "
              + mServerMessenger);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
      Log.d(Constant.TAG, "onServiceDisconnected: name = " + name);
    }
  };

  private void bindBinderService() {
    Intent intent = new Intent(this, BinderService.class);
    bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
  }

  private void unbindBinderService() {
    unbindService(mServiceConnection);
  }

  private void sendMsg() {
    if (mServerMessenger == null) {
      return;
    }
    Message message = Message.obtain();
    message.replyTo = mClientMessenger;
    message.what = Constant.MSG_FROM_CLIENT;
    Bundle bundle = new Bundle();
    bundle.putString(Constant.KEY_CLIENT, "client say hello");
    message.setData(bundle);
    try {
      mServerMessenger.send(message);
    } catch (RemoteException e) {
      e.printStackTrace();
    }
  }

}
