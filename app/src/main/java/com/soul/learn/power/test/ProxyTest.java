package com.soul.learn.power.test;

import com.soul.learn.power.proxy.ISubject;
import com.soul.learn.power.proxy.RealSubject;
import com.soul.learn.power.proxy.SubjectProxyHandler;

import java.lang.reflect.Proxy;

/**
 * Created by chenjianhua on 2017/7/18.
 */

public class ProxyTest {
    private static final ProxyTest ourInstance = new ProxyTest();

    public static ProxyTest getInstance() {
        return ourInstance;
    }

    private ProxyTest() {

    }

    public void test(){
        RealSubject realSubject = new RealSubject();
        SubjectProxyHandler proxyHandler = new SubjectProxyHandler(realSubject);
        ISubject iSubject = (ISubject) Proxy.newProxyInstance(RealSubject.class.getClassLoader(),
                RealSubject.class.getInterfaces(),proxyHandler);
        iSubject.request();
    }
}
