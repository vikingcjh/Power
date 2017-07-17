package com.soul.learn.safelib;

/**
 * Created by chenjianhua on 2017/7/17.
 */

public class CaesarCipher {
    public static String encrypt(String input, int key){
        char[] array = input.toCharArray();
        for (int i=0; i<array.length; i++){
            int num = array[i];
            num=num+key;
            array[i]=(char)num;
        }
        return new String(array);
    }

    public static String descrypt(String input,int key){
        char[] array = input.toCharArray();
        for (int i=0; i<array.length; i++){
            int num = array[i];
            num=num-key;
            array[i]=(char)num;
        }
        return new String(array);
    }
}
