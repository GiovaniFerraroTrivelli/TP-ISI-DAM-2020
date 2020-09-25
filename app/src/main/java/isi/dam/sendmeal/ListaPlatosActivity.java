package isi.dam.sendmeal;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import isi.dam.sendmeal.dataAccess.PlatoDao;

public class ListaPlatosActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PlatoAdapter platoAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_platos);
        recyclerView = findViewById(R.id.recycler_view_lista_platos);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        platoAdapter = new PlatoAdapter(PlatoDao.pruebaListaPlatos(), this);
        recyclerView.setAdapter(platoAdapter);

    }
}