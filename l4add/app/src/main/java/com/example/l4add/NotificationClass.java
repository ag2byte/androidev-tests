package com.example.l4add;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class NotificationClass  extends ContextWrapper {
    NotificationManager manager;
    public NotificationClass(Context ctx) {
        super(ctx);
        createNotificationChannel();
    }
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            manager = (NotificationManager)
                    getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel channel = new NotificationChannel("Q2", "AT Lab Channel", NotificationManager.IMPORTANCE_HIGH);
                    channel.enableLights(true);
            channel.enableVibration(true);
            channel.setLightColor(R.color.purple_500);
            channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
            manager.createNotificationChannel(channel);
        }
    }
    public void sendNotification(String title,String text) {
        manager.notify(0,notify(title,text).build());
    }
    private NotificationCompat.Builder notify(String title,String text){
        return new NotificationCompat.Builder(getApplicationContext(),"Q2")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(title)
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(Notification.DEFAULT_ALL);
    }


}