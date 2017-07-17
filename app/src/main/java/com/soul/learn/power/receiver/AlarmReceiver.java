package com.soul.learn.power.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.soul.learn.power.service.StickyService;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        context.startService(new Intent(context, StickyService.class));
    }
}
