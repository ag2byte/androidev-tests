package com.example.l4add;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlertReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationClass ncx = new NotificationClass(context);
        ncx.sendNotification("Wake up Sid!","I have the high ground");
    }
}
