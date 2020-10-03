package isi.dam.sendmeal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import isi.dam.sendmeal.model.Plato;

public class PedidoActivity extends AppCompatActivity {

    private static final int LISTA_PLATOS_REQUEST_CODE = 0;
    static public List<Plato> listaPlatosPedido = new ArrayList<Plato>();
    private Spinner spinnerCiudades;
    private RecyclerView recyclerView;
    private PlatoPedidoAdapter platoAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        // Solo admite una ciudad
        spinnerCiudades = (Spinner) findViewById(R.id.Spinner_ciudades);
        spinnerCiudades.setEnabled(false);
    }

    static public Boolean addToListaPlatos(Plato plato) {
        return listaPlatosPedido.add(plato);
    }

    public static List<Plato> getListaPlatosPedido() {
        return listaPlatosPedido;
    }

    public void onClickAgregarPlatos(View view) {
        Intent intentListaPlatos = new Intent(this, ListaPlatosActivity.class);
        intentListaPlatos.putExtra("SENDER_CLASS", String.valueOf(PedidoActivity.class));
        startActivityForResult(intentListaPlatos, LISTA_PLATOS_REQUEST_CODE);
    }

    public void onClickPedir(View view) {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // check that it is the SecondActivity with an OK result
        if (requestCode == LISTA_PLATOS_REQUEST_CODE) {
            if (resultCode == RESULT_OK) { // Activity.RESULT_OK

                Log.d("result", "here");

            }
        }
    }
}