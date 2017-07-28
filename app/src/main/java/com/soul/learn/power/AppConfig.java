package com.soul.learn.power;

public class AppConfig {
	/**
	 * 测试模式,在打包时需要修改为False
	 */
	public static boolean DEBUG_MODE = true;

	public static final String SDCARD_ROOT_DIR = "";

	public static String IMAGE_CACHE_DIR = "";
	
	public static String IMAGE_DISK_CACHE_DIR="";

	public static class BuildConfig {
		public static boolean DEBUG = DEBUG_MODE;
	}

}
