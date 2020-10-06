package isi.dam.sendmeal;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import isi.dam.sendmeal.model.Plato;

public class PedidoActivity extends AppCompatActivity {

    private static final int LISTA_PLATOS_REQUEST_CODE = 0;
    static public List<Plato> listaPlatosPedido = new ArrayList<Plato>();
    private RecyclerView recyclerView;
    private EditText textEmail;
    private EditText textCallePedido;
    private Spinner spinnerCiudades;
    private EditText textNroCallePedido;
    private PlatoPedidoAdapter platoAdapter;
    private TextView total;
    private TextView textCantidadPlatos;
    private TextView textFilaDetallePedido;
    private TextView textPrecioPedido;
    private View linea;

    // TODO: Revisar qué clase deberia tener el BroadcastReceiver
    private CustomReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        textEmail = (EditText) findViewById(R.id.Email_pedido);
        textCallePedido = (EditText) findViewById(R.id.Calle_pedido);
        textNroCallePedido = (EditText) findViewById(R.id.Numero_calle_pedido);

        // Sólo admite una ciudad
        spinnerCiudades = (Spinner) findViewById(R.id.Spinner_ciudades);
        spinnerCiudades.setEnabled(false);


        recyclerView = findViewById(R.id.recycler_view_platos_pedido);
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        platoAdapter = new PlatoPedidoAdapter(listaPlatosPedido, this);
        recyclerView.setAdapter(platoAdapter);
        textFilaDetallePedido = (TextView) findViewById(R.id.Fila_detalle_pedido);
        textPrecioPedido = (TextView) findViewById(R.id.Precio_pedido);
        linea = (View) findViewById(R.id.Space);
        total = (TextView) findViewById(R.id.Total);
        textCantidadPlatos = (TextView) findViewById(R.id.Cantidad_platos);

        broadcastReceiver = new CustomReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(CustomReceiver.EVENTO_PEDIDO_REGISTRADO);
        this.registerReceiver(broadcastReceiver, filter);

        //TODO: Revisar qué hace esto.
        crearCanal(this);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("EMAIL", String.valueOf(textEmail.getText()));
        outState.putString("CALLE_PEDIDO", String.valueOf(textCallePedido.getText()));
        outState.putString("NRO_CALLE_PEDIDO", String.valueOf(textNroCallePedido.getText()));
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        textEmail.setText(savedInstanceState.getString("EMAIL"));
        textCallePedido.setText(savedInstanceState.getString("CALLE_PEDIDO"));
        textNroCallePedido.setText(savedInstanceState.getString("NRO_CALLE_PEDIDO"));
        this.actualizarDetallePedido();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // check that it is the SecondActivity with an OK result
        if (requestCode == LISTA_PLATOS_REQUEST_CODE) {
            if (resultCode == RESULT_OK) { // Activity.RESULT_OK
                this.recyclerView.getAdapter().notifyDataSetChanged();
                this.actualizarDetallePedido();
            }
        }
    }

    public void onClickAgregarPlatos(View view) {
        Intent intentListaPlatos = new Intent(this, ListaPlatosActivity.class);
        startActivityForResult(intentListaPlatos, LISTA_PLATOS_REQUEST_CODE);
    }

    public void onClickFinalizarPedido(View view) {
        if(listaPlatosPedido.isEmpty()) {
            Toast.makeText(this, "¡No agregaste ningún plato!", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(this, CustomIntentService.class);
        startService(intent);
    }

    void actualizarDetallePedido() {
        if (!listaPlatosPedido.isEmpty()) {
            textFilaDetallePedido.setVisibility(View.VISIBLE);
            linea.setVisibility(View.VISIBLE);
            total.setVisibility(View.VISIBLE);
            textCantidadPlatos.setText(String.valueOf(listaPlatosPedido.size()).concat(" plato(s)"));
            textCantidadPlatos.setVisibility(View.VISIBLE);
            DecimalFormat df = new DecimalFormat("#.00");
            textPrecioPedido.setText("$".concat(df.format(calcularPrecioTotal())));
            textPrecioPedido.setVisibility(View.VISIBLE);
        }
    }

    static private Double calcularPrecioTotal() {
        Double total = 0.0;
        for (Plato p : listaPlatosPedido)
            total += p.getPrecio();
        return total;
    }

    static public Boolean addToListaPlatos(Plato plato) {
        return listaPlatosPedido.add(plato);
    }

    public static List<Plato> getListaPlatosPedido() {
        return listaPlatosPedido;
    }

    public static void addToListaPlatosPedido(Plato plato) {
        listaPlatosPedido.add(plato);
    }

    public void crearCanal(Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel = new NotificationChannel(CustomReceiver.NOTIFICATION_CHANNEL_ID, "Confirmación de pedidos", importance);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

}