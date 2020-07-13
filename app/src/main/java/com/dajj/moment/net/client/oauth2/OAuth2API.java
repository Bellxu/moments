package com.dajj.moment.net.client.oauth2;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface OAuth2API {
    @FormUrlEncoded
    @POST("/auth-service/oauth/token")
    Call<AccessToken> requestToken(@FieldMap Map<String, String> tokenRequest);
}
