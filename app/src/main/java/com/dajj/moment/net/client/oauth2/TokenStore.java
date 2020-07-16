package com.dajj.moment.net.client.oauth2;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.dajj.moment.net.client.login.BearerToken;

public class TokenStore {
    private static TokenStore instance;
    private final SharedPreferences prefs;


    public static TokenStore getInstance (Context context){
        if (instance==null) {
            instance =new TokenStore(context);
        }
        return instance;

    }

    public TokenStore(Context context) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void save(AccessToken accessToken) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("authorized", true);
        editor.putString("access_token", accessToken.getValue());
        editor.putString("scope", accessToken.getScope());
        editor.putString("token_type", accessToken.getTokenType());
        editor.putLong("expires_in", accessToken.getExpiresIn());
        editor.putLong("issued_at", accessToken.getIssuedAt());
        editor.commit();
    }

    public void save(BearerToken bearerToken) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("bearer_token", bearerToken.getAccessToken());
        editor.putString("bearer_token_type", bearerToken.getTokenType());
        editor.commit();
    }

    public void clear() {
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove("authorized");
        editor.remove("access_token");
        editor.remove("scope");
        editor.remove("token_type");
        editor.remove("expires_in");
        editor.remove("issued_at");
        editor.apply();
    }

    public AccessToken getToken() {
        AccessToken token = null;

        boolean authorized = prefs.getBoolean("authorized", false);
        if (authorized) {
            token = new AccessToken();
            token.setValue(prefs.getString("access_token", null));
            token.setScope(prefs.getString("scope", ""));
            token.setTokenType(prefs.getString("token_type", "bearer"));
            token.setExpiresIn(prefs.getLong("expires_in", -1)); // prevents / 0
            token.setIssuedAt(prefs.getLong("issued_at", -1)); // prevents / 0
        }
        return token;
    }


    public BearerToken getBearerToken() {
        BearerToken token = new BearerToken();
        String bearerToken = prefs.getString("bearer_token", "");
        String bearer_token_type = prefs.getString("bearer_token_type", "");
        token.setAccessToken(bearerToken);
        token.setTokenType(bearer_token_type);
        return token;
    }
}
