package isi.dam.sendmeal.api;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import isi.dam.sendmeal.model.Pedido;
import isi.dam.sendmeal.model.Plato;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PedidoApiCallbacks {
    PedidoService pedidoService;

    public PedidoApiCallbacks(){
        pedidoService = ApiInterface.getInstance().getPedidoService();
    }

    public void getPedidos(){
        Pedido pedido = new Pedido();

        Call<List<Pedido>> callPedidos= pedidoService.getPedidoList();

        callPedidos.enqueue(
                new Callback<List<Pedido>>() {
                    @Override
                    public void onResponse(Call<List<Pedido>> call, Response<List<Pedido>> response) {
                        if (response.code() == 200) {
                            Log.d("DEBUG", "Retorno Exitoso");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Pedido>> call, Throwable t) {
                        Log.d("DEBUG", "Retorno Fallido");
                    }
                }
        );
    }

    public void getPedidoById(String id){

        Call<Pedido>callPedidos= pedidoService.getPedido(id);

        callPedidos.enqueue(
                new Callback<Pedido>() {
                    @Override
                    public void onResponse(Call<Pedido> call, Response<Pedido> response) {
                        if (response.code() == 200) {
                            Log.d("DEBUG", "Retorno Exitoso");
                        }
                    }

                    @Override
                    public void onFailure(Call<Pedido> call, Throwable t) {
                        Log.d("DEBUG", "Retorno Fallido");
                    }
                }
        );

    }

    public void createPedido(Pedido pedido) throws JSONException {

        Call<Pedido> callPedidos = pedidoService.createPedido(pedido);

        callPedidos.enqueue(
                new Callback<Pedido>() {
                    @Override
                    public void onResponse(Call<Pedido> call, Response<Pedido> response) {
                        if (response.code() == 200) {
                            Log.d("DEBUG", "Retorno Exitoso");
                        }
                    }

                    @Override
                    public void onFailure(Call<Pedido> call, Throwable t) {
                        Log.d("DEBUG", "Retorno Fallido");
                    }
                }
        );


    }

}
