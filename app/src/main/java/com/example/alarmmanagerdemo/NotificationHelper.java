package com.example.alarmmanagerdemo;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.activity.result.ActivityResultLauncher;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;

public class NotificationHelper {
    private static final String CHANNEL_ID = "Test";
    private Context notiContext;
    private NotificationManager notificationManager;

    public NotificationHelper(Context context){
        this.notiContext = context;
        this.notificationManager = context.getSystemService(NotificationManager.class);
    }
    public void createNotificationChannel(){
        System.out.println("Channel created");
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,"Channel NAme",NotificationManager.IMPORTANCE_HIGH);
            if(notificationManager != null){
                notificationManager.createNotificationChannel(channel);
            }
        }
    }
    public void showNotification(int notificationId){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(notiContext,CHANNEL_ID)
                .setContentTitle("Notification  demo")
                .setContentText("This notification is for testing purpose")
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.baseline_notifications_24);
        if(notificationManager != null){
            notificationManager.notify(notificationId,builder.build());
        }
    }
    //Method to request notification permission for Android 13 and above
    public void requestNotificationPermission(ActivityResultLauncher activityResultLauncher){
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
            ActivityCompat.checkSelfPermission(notiContext,android.Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {
        activityResultLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS);
    } else{
        showNotification(1);
    }
    }
}
