package com.example.dev_qualicom.gsca;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessageService extends FirebaseMessagingService {
    public MyFirebaseMessageService() {
    }



    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        sendNotification(remoteMessage.getNotification().getBody());
    }


    @Override
    public void onNewToken(String s){
        sendRegistrationToServer(s);
    }

    private void sendRegistrationToServer(String token) {
        Log.d("TEST", "Refreshed token: " + token);
    }

    private  void sendNotification(String messageBoby){

        Intent intent;

        if(messageBoby.startsWith("Vous avez été invité a la réunion")){
            intent = new Intent(getApplicationContext(), list_events.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        }else{
            intent = new Intent(getApplicationContext(), list_docs.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        }

        long notificatioId = System.currentTimeMillis();


        PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);
        notificationBuilder.setSmallIcon(R.drawable.ic_launcher_background);
        notificationBuilder.setContentTitle("GSCA");
        notificationBuilder.setAutoCancel(true);
        notificationBuilder.setDefaults(Notification.FLAG_AUTO_CANCEL | Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE | Notification.DEFAULT_SOUND);
        notificationBuilder.setPriority(Notification.PRIORITY_HIGH);
        notificationBuilder.setContentIntent(contentIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify((int) notificatioId,notificationBuilder.build());
    }
}
