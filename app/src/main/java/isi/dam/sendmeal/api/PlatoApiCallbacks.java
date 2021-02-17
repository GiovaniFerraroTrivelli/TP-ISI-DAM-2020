package isi.dam.sendmeal.api;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import isi.dam.sendmeal.model.Plato;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlatoApiCallbacks {
    PlatoService platoService;

    public PlatoApiCallbacks(){
        platoService = ApiInterface.getInstance().getPlatoService();
    }

    public void getPlatos(){
        Plato plato = new Plato();

        Call<List<Plato>> callPlatos = platoService.getPlatoList();

        callPlatos.enqueue(
                new Callback<List<Plato>>() {
                    @Override
                    public void onResponse(Call<List<Plato>> call, Response<List<Plato>> response) {
                        if (response.code() == 200) {
                            Log.d("DEBUG", "Retorno Exitoso");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Plato>> call, Throwable t) {
                        Log.d("DEBUG", "Retorno Fallido");
                    }
                }
        );

    }

    public void getPlatoById(String id){
        Plato plato = new Plato();

        Call<Plato> callPlatos = platoService.getPlato(id);

        callPlatos.enqueue(
                new Callback<Plato>() {
                    @Override
                    public void onResponse(Call<Plato> call, Response<Plato> response) {
                        if (response.code() == 200) {
                            Log.d("DEBUG", "Retorno Exitoso");
                        }
                    }

                    @Override
                    public void onFailure(Call<Plato> call, Throwable t) {
                        Log.d("DEBUG", "Retorno Fallido");
                    }
                }
        );

    }

    public void createPlato(Plato plato) throws JSONException {

        Call<Plato> callPlatos = platoService.createPlato(plato);

        callPlatos.enqueue(
                new Callback<Plato>() {
                    @Override
                    public void onResponse(Call<Plato> call, Response<Plato> response) {
                        if (response.code() == 200) {
                            Log.d("DEBUG", "Retorno Exitoso");
                        }
                    }

                    @Override
                    public void onFailure(Call<Plato> call, Throwable t) {
                        Log.d("DEBUG", "Retorno Fallido");
                    }
                }
        );

    }

}
