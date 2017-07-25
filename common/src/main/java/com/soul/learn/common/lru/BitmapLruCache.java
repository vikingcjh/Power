package com.soul.learn.common.lru;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by chenjianhua on 2017/6/1.
 */

public class BitmapLruCache {
    private static BitmapLruCache instance = null;

    private LruCache<String,Bitmap> mMemoryCache;

    private BitmapLruCache(){
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        final int cacheSize = maxMemory / 8;
        mMemoryCache = new LruCache<String,Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getByteCount()/1024;
            }
        };
    }

    public static BitmapLruCache getInstance(){
        if (instance == null){
            instance = new BitmapLruCache();
        }
        return instance;
    }

    public Bitmap getBitmapFromMemCache(String key){
        return mMemoryCache.get(key);
    }

    public void addBitmapToMemCache(String key,Bitmap bitmap){
        if (getBitmapFromMemCache(key)==null){
            mMemoryCache.put(key,bitmap);
        }
    }
}
