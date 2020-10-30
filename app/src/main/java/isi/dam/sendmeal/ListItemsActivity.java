package isi.dam.sendmeal;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import isi.dam.sendmeal.repositories.AppRepository;

public class ListItemsActivity extends AppCompatActivity implements AppRepository.OnResultCallback {
    /* ..... */
    private AppRepository repository;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        repository = new AppRepository(this.getApplication(), this);
        repository.buscarTodos();
    }

    @Override
    public void onResult(List result) {
        // Vamos a obtener una Lista de items como resultado cuando finalize
        Toast.makeText(ListItemsActivity.this, "Exito!", Toast.LENGTH_SHORT).show();
    }

}