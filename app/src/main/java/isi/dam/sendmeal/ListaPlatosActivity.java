package isi.dam.sendmeal;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import isi.dam.sendmeal.dataAccess.PlatoDao;

public class ListaPlatosActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private PlatoAdapter platoAdapter;
    private PlatoAdapterFromAltaPedido platoAdapterFromAltaPedido;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_platos);

        toolbar = (Toolbar) findViewById(R.id.nuevo_pedido_toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recycler_view_lista_platos);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        // recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // verifica si la actividad fue iniciada desde PedidoActivity (!= null)
        // o desde HomeActivity (== null), y setea el adapter corespondiente.
        if(this.getCallingActivity() != null) {
            platoAdapterFromAltaPedido = new PlatoAdapterFromAltaPedido(PlatoDao.getListaPlatos(), this);
            recyclerView.setAdapter(platoAdapterFromAltaPedido);
        }
        else {
            platoAdapter = new PlatoAdapter(PlatoDao.getListaPlatos(), this);
            recyclerView.setAdapter(platoAdapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lista_platos, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nuevo_pedido:
                if(!PlatoDao.getListaPlatos().isEmpty()) {
                    Intent registarIntent =  new Intent(this, PedidoActivity.class);
                    startActivity(registarIntent);
                }
                else {
                    Toast.makeText(this, "No hay platos cargados", Toast.LENGTH_SHORT).show();
                }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}