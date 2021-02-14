package isi.dam.sendmeal.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

import isi.dam.sendmeal.adapters.PlatoAdapter;
import isi.dam.sendmeal.adapters.PlatoAdapterFromAltaPedido;
import isi.dam.sendmeal.R;
import isi.dam.sendmeal.dataAccess.PlatoDao;
import isi.dam.sendmeal.model.Plato;
import isi.dam.sendmeal.repositories.PlatoRepository;

public class ListaPlatosActivity extends AppCompatActivity implements PlatoRepository.OnResultCallback {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private PlatoAdapter platoAdapter;
    private PlatoAdapterFromAltaPedido platoAdapterFromAltaPedido;
    private RecyclerView.LayoutManager layoutManager;
    private PlatoRepository repository;
    private Boolean hasPlatos;
    private ImageView imagenPlato;

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
        repository  = new PlatoRepository(this.getApplication(), this);
        repository.buscarTodos();
    }

    @Override
    public void onResult(List result) {

        // TODO: ARREGLAR ESTO URGENTE
        // verifica si la actividad fue iniciada desde PedidoActivity (!= null)
        // o desde HomeActivity (== null), y setea el adapter corespondiente.
        if(this.getCallingActivity() != null) {
            platoAdapterFromAltaPedido = new PlatoAdapterFromAltaPedido(result, this);
            recyclerView.setAdapter(platoAdapterFromAltaPedido);
        }
        else {
            platoAdapter = new PlatoAdapter(result, this);
            recyclerView.setAdapter(platoAdapter);
        }

        hasPlatos = result.size() > 0;
    }

    @Override
    public void onInsert() {
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
                if(hasPlatos) {
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