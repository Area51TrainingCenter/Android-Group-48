package pe.area51.clasews.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {
    public static Retrofit obtenerConfiguracion() {
        return new Retrofit.Builder()
                .baseUrl("http://192.168.2.241:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
