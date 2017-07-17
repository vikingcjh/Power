package com.soul.learn.power.test;

import com.soul.learn.power.log.LogUtil;
import com.soul.learn.safelib.AesUtil;
import com.soul.learn.safelib.CaesarCipher;

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

    public static void caesarTest(){
        String input = "chen";
        LogUtil.i("init-res:"+input);
        String result = CaesarCipher.encrypt(input,4);
        LogUtil.i("enc-res:"+result);
        String decresult = CaesarCipher.descrypt(result,4);
        LogUtil.i("desc-res:"+decresult);
    }

    public static void aesTest(){
        String input = "abc";//defghijklmn
        String key = "klmnopqrstuvwxyz";
        LogUtil.i("init-aes:"+input);
        byte[] result1 = AesUtil.encrypt(input,key);
        LogUtil.i("enc-aes:result1.length"+result1.length);
        String hexstr = AesUtil.parseByte2HexStr(result1);
        LogUtil.i("enc-aes:"+hexstr);

        byte hexbyte[] = AesUtil.parseHexStr2Byte(hexstr);
        byte[] result2 = AesUtil.descrypt(hexbyte,key);
        String result3 = new String(result2);
        LogUtil.i("desc-aes:"+result3);
    }

}
