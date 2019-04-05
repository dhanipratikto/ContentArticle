package cakrait.com.contentarticle.network;

import cakrait.com.contentarticle.model.retrofit.ResponseLogin;
import cakrait.com.contentarticle.model.retrofit.ResponseRegister;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RestApi {

    @FormUrlEncoded
    @POST("login.php")
    Call<ResponseLogin> loginUser(
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("register.php")
    Call<ResponseRegister> registerUser(
            @Field("username") String username,
            @Field("email") String email,
            @Field("password") String password,
            @Field("phone") String phone
    );

}
