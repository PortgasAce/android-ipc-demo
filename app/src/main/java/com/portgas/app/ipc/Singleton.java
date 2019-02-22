package com.portgas.app.ipc;

public class Singleton {

  private static Singleton sInstance;

  public int value = 1;

  private Singleton() {
  }

  public static Singleton getInstance() {
    if (sInstance == null) {
      synchronized (Singleton.class) {
        if (sInstance == null) {
          sInstance = new Singleton();
        }
      }
    }
    return sInstance;
  }

}
