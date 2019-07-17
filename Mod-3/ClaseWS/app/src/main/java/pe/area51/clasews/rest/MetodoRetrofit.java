package pe.area51.clasews.rest;

import pe.area51.clasews.rest.request.UsuarioRequest;
import pe.area51.clasews.rest.response.UsuarioResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface MetodoRetrofit {

    @POST("user")
    @FormUrlEncoded
    Call<UsuarioResponse> validarUsuario(@Field("username")String usuario,
                                         @Field("password")String contrasenia);

    @POST("user/save")
    Call<UsuarioResponse> guardarUsuario(@Body UsuarioRequest request);
}
