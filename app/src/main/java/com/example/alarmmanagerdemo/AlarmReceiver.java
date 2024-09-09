package com.example.alarmmanagerdemo;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver {
    private static final String CHANNEL_ID = "Test";
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationCompat.Builder builder= new NotificationCompat.Builder(context,CHANNEL_ID)
                .setContentText("This notification is triggered by Alarm Manager")
                .setContentTitle("Alarm Notification")
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.baseline_notifications_24);
        //Show Notification
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        if(notificationManager != null){
            notificationManager.notify(1,builder.build());
        }
    }
}
