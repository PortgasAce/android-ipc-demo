package com.portgas.app.ipc;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import com.portgas.app.ipc.binder.BinderDemoActivity;

@SuppressLint("SetTextI18n")
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Constant.STATIC_VALUE = 2;
    Singleton.getInstance().value = 2;

    TextView processTv = findViewById(R.id.process);
    processTv.setText("进程：" + ProcessUtil.getProcessName(this));

    findViewById(R.id.btn1).setOnClickListener(this);
    findViewById(R.id.btn2).setOnClickListener(this);
  }

  @Override
  protected void onResume() {
    super.onResume();

    TextView staticVarTv = findViewById(R.id.static_variable);
    staticVarTv.setText("静态变量 STATIC_VALUE：" + Constant.STATIC_VALUE);

    TextView singletonTv = findViewById(R.id.singleton);
    singletonTv.setText("单例 singleton.value：" + Singleton.getInstance().value);

  }

  @Override
  public void onClick(View v) {
    final int id = v.getId();
    switch (id) {
      case R.id.btn1: {
        Intent intent = new Intent(MainActivity.this, ProcessActivity.class);
        MainActivity.this.startActivity(intent);
        break;
      }
      case R.id.btn2: {
        Intent intent = new Intent(MainActivity.this, BinderDemoActivity.class);
        MainActivity.this.startActivity(intent);
        break;
      }
    }
  }
}
