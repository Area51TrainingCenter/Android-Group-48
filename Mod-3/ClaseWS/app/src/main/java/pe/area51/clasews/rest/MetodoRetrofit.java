package pe.area51.clasews.rest;

import pe.area51.clasews.rest.request.ProductRequest;
import pe.area51.clasews.rest.request.UsuarioRequest;
import pe.area51.clasews.rest.response.Datum;
import pe.area51.clasews.rest.response.ProductoObjectResponse;
import pe.area51.clasews.rest.response.ProductoResponse;
import pe.area51.clasews.rest.response.UsuarioResponse;
import retrofit2.Call;
import retrofit2.http.*;

public interface MetodoRetrofit {

    @POST("user")
    @FormUrlEncoded
    Call<UsuarioResponse> validarUsuario(@Field("username") String usuario,
                                         @Field("password") String contrasenia);

    @POST("user/save")
    Call<UsuarioResponse> guardarUsuario(@Body UsuarioRequest request);

    @GET("product/user/{codigo}")
    Call<ProductoResponse> obtenerProductos(@Path("codigo") int codigo);

    @POST("product/save")
    Call<ProductoObjectResponse> guardarProducto(@Body ProductRequest request);
}
