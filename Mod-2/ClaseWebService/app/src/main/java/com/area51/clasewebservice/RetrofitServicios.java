package com.area51.clasewebservice;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitServicios {

    @GET("posts")
    Call<ArrayList<PostResponse>> obtenerPost();
}
