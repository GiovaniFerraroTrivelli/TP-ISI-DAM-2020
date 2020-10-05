package isi.dam.sendmeal;

import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;

public class CustomIntentService extends IntentService {

    public CustomIntentService() {
        super("CustomIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        try {
            Thread.sleep(5000);
            Intent intentPedidoRegistrado = new Intent();
            intentPedidoRegistrado.setAction(CustomReceiver.EVENTO_PEDIDO_REGISTRADO);
            sendBroadcast(intentPedidoRegistrado);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
