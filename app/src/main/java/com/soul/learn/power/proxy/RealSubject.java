package com.soul.learn.power.proxy;

/**
 * Created by chenjianhua on 2017/7/18.
 */

public class RealSubject implements ISubject {
    @Override
    public void request() {
        System.out.println("====RealSubject Request====");
    }
}
