package com.portgas.app.ipc;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

@SuppressLint("SetTextI18n")
public class ProcessActivity extends AppCompatActivity implements View.OnClickListener {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_process);

    TextView processTv = findViewById(R.id.process);
    processTv.setText("进程：" + ProcessUtil.getProcessName(this));

    TextView staticVarTv = findViewById(R.id.static_variable);
    staticVarTv.setText("静态变量 STATIC_VALUE：" + Constant.STATIC_VALUE);

    TextView singletonTv = findViewById(R.id.singleton);
    singletonTv.setText("单例 singleton.value：" + Singleton.getInstance().value);

    findViewById(R.id.break_point_btn).setOnClickListener(this);
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.break_point_btn:
        // break point here
        Log.d(Constant.TAG, "onClick: break point click");
        break;
    }
  }
}
