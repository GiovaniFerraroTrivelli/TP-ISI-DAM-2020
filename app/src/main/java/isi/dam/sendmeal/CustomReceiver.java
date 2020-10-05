package isi.dam.sendmeal;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

public class CustomReceiver extends BroadcastReceiver {
    public static final String NOTIFICATION_CHANNEL_ID = "10001";
    public static final String EVENTO_PEDIDO_REGISTRADO = "isi.dam.sendmeal.EVENTO_PEDIDO_REGISTRADO";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (EVENTO_PEDIDO_REGISTRADO.equalsIgnoreCase(intent.getAction())) {
            enviarNotificacionPedido(context, intent);
        }
    }

    private void enviarNotificacionPedido(Context context, Intent intent) {
        Intent intentNotificacionPedido = new Intent(context, PedidoActivity.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intentNotificacionPedido, 0);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.person_white)
                .setContentTitle("¡Tu pedido fue guardado!")
                .setContentText("Ya lo estamos preparando")
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        Notification notificacionPedido = mBuilder.build();
        NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
        notificationManager.notify(99, notificacionPedido);

    }
}
