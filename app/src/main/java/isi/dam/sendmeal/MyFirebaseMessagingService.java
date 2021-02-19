package isi.dam.sendmeal;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import isi.dam.sendmeal.activities.HomeActivity;

import static androidx.core.content.ContextCompat.getSystemService;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String PEDIDO_CHANNEL = "pedido_channel";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        if (remoteMessage.getData().size() > 0) {
            Log.d("TAG", "Payload del mensaje: " + remoteMessage.getData());
        }
        if (remoteMessage.getNotification() != null) {
            Log.d("TAG", "Cuerpo de la notificación del mensaje: " + remoteMessage.getNotification().getBody());
        }
        sendNotification(remoteMessage.getNotification().getBody(), remoteMessage.getNotification().getTitle());
    }

    private void sendNotification(String messageBody, String title) {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(PEDIDO_CHANNEL, "Pedido channel", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, PEDIDO_CHANNEL)
                        .setAutoCancel(true)
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setWhen(System.currentTimeMillis())
                        .setContentTitle(title)
                        .setContentText(messageBody);

        notificationManager.notify(0, notificationBuilder.build());
    }

}