package com.dajj.moment.net.client.oauth2;

import android.net.Uri;

import com.dajj.moment.net.client.ClientAPI;

public class AuthorizationRequest {
    public static final String REDIRECT_URI
        = "oauth2://userinfo/callback";

    public static Uri createAuthorizationUri(String state) {
        return new Uri.Builder()
            .scheme("http")
            .encodedAuthority(ClientAPI.BASE_URL)
            .path("/auth-service/oauth/authorize")
            .appendQueryParameter("client_id", "android-app")
            .appendQueryParameter("response_type", "code")
            .appendQueryParameter("redirect_uri", REDIRECT_URI)
            .appendQueryParameter("scope", "read")
            .appendQueryParameter("state", state)
            .build();
    }

}
