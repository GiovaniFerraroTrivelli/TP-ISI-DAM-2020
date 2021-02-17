package isi.dam.sendmeal.repositories;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.room.*;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import isi.dam.sendmeal.BuscarPedidos;
import isi.dam.sendmeal.OnPedidoResultCallback;
import isi.dam.sendmeal.dataAccess.PedidoDao;
import isi.dam.sendmeal.model.Pedido;

public class PedidoRepository implements OnPedidoResultCallback {
    private PedidoDao pedidoDao;
    private OnResultCallback callback;

    public PedidoRepository(Application application, OnResultCallback context){
        AppDatabase db = AppDatabase.getInstance(application);
        pedidoDao = db.pedidoDao();
        callback = context;
    }

    @Override
    public void onResult(List<Pedido> pedidos) {
        Log.d("DEBUG", "Pedido found");
        callback.onResult(pedidos);
    }

    public void insertar(final Pedido pedido){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                pedidoDao.insertar(pedido);
                callback.onInsert();
            }
        });
    }

    public void borrar(final Pedido pedido){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                pedidoDao.borrar(pedido);
            }
        });
    }

    public void actualizar(final Pedido pedido){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                pedidoDao.actualizar(pedido);
            }
        });
    }

    /*public void buscar(String id) {
        new BuscarPlatoById(platoDao, this).execute(id);
    }*/

    public void buscarTodos() {
        new BuscarPedidos(pedidoDao, this).execute();
    }


    public interface OnResultCallback<T> {
        void onResult(List<T> result);
        void onInsert();
    }
}