package com.soul.learn.power;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.soul.learn.power.db.DaoMaster;
import com.soul.learn.power.db.DaoSession;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by chenjianhua on 2017/7/18.
 */

public class PowerApplication extends Application {
    private static Context mContext;
    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);

        initGreenDao();

    }

    public static Context getGlobalContext() {
        return mContext;
    }

    private void initGreenDao(){
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "test.db");
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster master = new DaoMaster(db);
        daoSession = master.newSession();
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }

}
