package com.dajj.moment.net.client.login;

import com.dajj.moment.net.client.login.BearerToken;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginAPI {

    @POST("/auth-service/auth/login")
    Call<BearerToken> requestToken(@Body UserLogin userLogin);


}
