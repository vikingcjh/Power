package com.soul.learn.power.test;

import com.soul.learn.power.log.LogUtil;

/**
 * Created by chenjianhua on 2017/7/14.
 */

public class Test {
    static int x =    1;
    public static int getValue(){
//        int x = 1;
        try
        {
            return x;
        }
        finally
        {
            ++x;
            LogUtil.i("finally x= "+ x);
        }
    }

}
