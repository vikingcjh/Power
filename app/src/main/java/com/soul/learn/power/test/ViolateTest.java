package com.soul.learn.power.test;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by chenjianhua on 2017/7/13.
 */

public class ViolateTest {
    public static volatile int a = 1;

    public static void getValue1(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                a++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.i("niodata","thread1111,a= "+a);
            }
        }).start();
    }

    public static void getValue2(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                a++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.i("niodata","thread222,a= "+a);
            }
        }).start();
    }
}
