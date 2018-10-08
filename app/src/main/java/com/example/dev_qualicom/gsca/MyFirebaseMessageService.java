package com.example.dev_qualicom.gsca;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
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

        Intent intent = new Intent(this, accueil.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent, PendingIntent.FLAG_ONE_SHOT);

        Uri defaulSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);
        notificationBuilder.setSmallIcon(R.drawable.ic_launcher_background);
        notificationBuilder.setContentTitle("GSCA");
        notificationBuilder.setAutoCancel(true);
        notificationBuilder.setSound(defaulSoundUri);
        notificationBuilder.setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,notificationBuilder.build());
    }
}
