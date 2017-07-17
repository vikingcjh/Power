package com.soul.learn.power.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RemoteViews;

import com.soul.learn.power.MainActivity;
import com.soul.learn.power.R;

public class StickyService extends Service {
    public StickyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Notification notification = new Notification(R.mipmap.ic_launcher, "服务正在运行",System.currentTimeMillis());
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,notificationIntent,0);
        RemoteViews remoteView = new RemoteViews(this.getPackageName(),R.layout.notification);
        remoteView.setImageViewResource(R.id.image, R.mipmap.ic_launcher);
        remoteView.setTextViewText(R.id.text , "Hello,this message is in a custom expanded view");
        notification.contentView = remoteView;
        notification.contentIntent = pendingIntent;
        startForeground(1, notification);
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        startService(new Intent(this, StickyService.class));
    }
}
