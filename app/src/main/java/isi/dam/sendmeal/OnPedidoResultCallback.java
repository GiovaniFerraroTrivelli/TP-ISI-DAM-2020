package isi.dam.sendmeal;

import java.util.List;

import isi.dam.sendmeal.model.Pedido;

public interface OnPedidoResultCallback {
    void onResult(List<Pedido> pedido);
}
