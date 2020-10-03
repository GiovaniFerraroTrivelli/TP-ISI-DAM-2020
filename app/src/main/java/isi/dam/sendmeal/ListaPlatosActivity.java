package isi.dam.sendmeal;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import isi.dam.sendmeal.dataAccess.PlatoDao;

public class ListaPlatosActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private PlatoAdapter platoAdapter;
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
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        platoAdapter = new PlatoAdapter(PlatoDao.getListaPlatos(), this);
        recyclerView.setAdapter(platoAdapter);

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
                Intent registarIntent =  new Intent(this, PedidoActivity.class);
                startActivity(registarIntent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}