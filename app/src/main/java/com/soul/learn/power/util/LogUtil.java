package com.soul.learn.power.util;

import android.util.Log;

import com.soul.learn.power.AppConfig;


/**
 * Log日志输出控制
 */
public class LogUtil {
	public static final String TAG ="NIODATA";
	
	public static void i(String tag, String msg){
		if(AppConfig.DEBUG_MODE&&msg!=null) Log.i(tag, msg);
	}
	public static void sysout(String msg){
		if(AppConfig.DEBUG_MODE&&msg!=null) System.out.println(msg);
	}

	public static void i(String msg){
		if(AppConfig.DEBUG_MODE&&msg!=null) Log.i(TAG, msg);
	}

}
