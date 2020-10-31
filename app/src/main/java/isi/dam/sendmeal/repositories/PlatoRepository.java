package isi.dam.sendmeal.repositories;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.room.*;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import isi.dam.sendmeal.BuscarPlatos;
import isi.dam.sendmeal.OnPlatoResultCallback;
import isi.dam.sendmeal.dataAccess.PlatoDao;
import isi.dam.sendmeal.model.Plato;

public class PlatoRepository implements OnPlatoResultCallback {
    private PlatoDao platoDao;
    private OnResultCallback callback;

    public PlatoRepository(Application application, OnResultCallback context){
        AppDatabase db = AppDatabase.getInstance(application);
        platoDao = db.platoDao();
        callback = context;
    }

    @Override
    public void onResult(List<Plato> platos) {
        Log.d("DEBUG", "Plato found");
        callback.onResult(platos);
    }

    public void insertar(final Plato plato){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                platoDao.insertar(plato);
                callback.onInsert();
            }
        });
    }

    public void borrar(final Plato plato){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                platoDao.borrar(plato);
            }
        });
    }

    public void actualizar(final Plato plato){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                platoDao.actualizar(plato);
            }
        });
    }

    /*public void buscar(String id) {
        new BuscarPlatoById(platoDao, this).execute(id);
    }*/

    public void buscarTodos() {
        new BuscarPlatos(platoDao, this).execute();
    }


    public interface OnResultCallback<T> {
        void onResult(List<T> result);
        void onInsert();
    }
}