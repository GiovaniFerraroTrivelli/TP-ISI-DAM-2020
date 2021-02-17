package isi.dam.sendmeal;

import android.os.AsyncTask;

import java.util.List;

import isi.dam.sendmeal.dataAccess.PedidoDao;
import isi.dam.sendmeal.model.Pedido;

public class BuscarPedidos extends AsyncTask<String, Void, List<Pedido>> {

    private PedidoDao dao;
    private OnPedidoResultCallback callback;

    public BuscarPedidos(PedidoDao dao, OnPedidoResultCallback context) {
        this.dao = dao;
        this.callback = context;
    }

    @Override
    protected List<Pedido> doInBackground(String... strings) {
        List<Pedido> pedidos = dao.buscarTodos();
        return pedidos;
    }

    @Override
    protected void onPostExecute(List<Pedido> pedidos) {
        super.onPostExecute(pedidos);
        callback.onResult(pedidos);
    }
}