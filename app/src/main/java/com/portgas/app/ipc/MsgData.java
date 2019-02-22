package com.portgas.app.ipc;

import android.os.Parcel;
import android.os.Parcelable;

public class MsgData implements Parcelable {

  public String msg;

  public MsgData(String msg) {
    this.msg = msg;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.msg);
  }

  public MsgData() {
  }

  protected MsgData(Parcel in) {
    this.msg = in.readString();
  }

  public static final Creator<MsgData> CREATOR = new Creator<MsgData>() {
    @Override
    public MsgData createFromParcel(Parcel source) {
      return new MsgData(source);
    }

    @Override
    public MsgData[] newArray(int size) {
      return new MsgData[size];
    }
  };
}
