package isi.dam.sendmeal.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiInterface {

    private static ApiInterface INSTANCE;
    private static final String urlApiRest = "http://10.0.2.2:3001/";
    private Retrofit retrofit;

    private ApiInterface() {

        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder()
                .baseUrl("{urlApiRest}")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public PlatoService getPlatoService(){
        return retrofit.create(PlatoService.class);
    }
    public PedidoService getPedidoService(){
        return retrofit.create(PedidoService.class);
    }

    public static ApiInterface getInstance(){
        if(INSTANCE == null){
            INSTANCE = new ApiInterface();
        }
        return INSTANCE;
    }
}
