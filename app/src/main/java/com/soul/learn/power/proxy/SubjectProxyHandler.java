package com.soul.learn.power.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by chenjianhua on 2017/7/18.
 */

public class SubjectProxyHandler implements InvocationHandler {
    private ISubject iSubject;

    public SubjectProxyHandler(ISubject iSubject) {
        this.iSubject = iSubject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //定义预处理的工作，当然你也可以根据 method 的不同进行不同的预处理工作
        System.out.println("====before====");
        Object result = method.invoke(iSubject,args);
        System.out.println("====after====");
        return result;
    }
}
