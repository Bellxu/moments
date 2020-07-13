package com.dajj.moment.net.client.userinfo;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface UserInfoAPI {

    @GET("/auth-service/api/user-info")
    Call<UserInfo> token(@Header("Authorization") String accessToken);

}
