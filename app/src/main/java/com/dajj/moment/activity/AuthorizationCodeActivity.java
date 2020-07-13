package com.dajj.moment.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.dajj.moment.R;
import com.dajj.moment.net.client.ClientAPI;
import com.dajj.moment.net.client.oauth2.AccessToken;
import com.dajj.moment.net.client.oauth2.AccessTokenRequest;
import com.dajj.moment.net.client.oauth2.OAuth2StateManager;
import com.dajj.moment.net.client.oauth2.TokenStore;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AuthorizationCodeActivity extends BaseActvity {

    private String code;
    private String state;
    private TokenStore tokenStore;
    private OAuth2StateManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_home);

        tokenStore = new TokenStore(this);
        manager = new OAuth2StateManager(this);

        Uri callbackUri = Uri.parse(getIntent().getDataString());

        code = callbackUri.getQueryParameter("code");
        state = callbackUri.getQueryParameter("state");

        // validates state
        if (!manager.isValidState(state)) {
            Toast.makeText(this, "CSRF Attack detected", Toast.LENGTH_SHORT).show();
            return;
        }

        Call<AccessToken> accessTokenCall = ClientAPI
                .oauth2()
                .requestToken(AccessTokenRequest.fromCode(code));

        accessTokenCall.enqueue(new Callback<AccessToken>() {
            @Override
            public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                AccessToken token = response.body();
                tokenStore.save(token);

                // go to the other activity with an access token in hands!!!!!!!

                Intent intent = new Intent(AuthorizationCodeActivity.this,
                        HomeActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(Call<AccessToken> call, Throwable t) {
                Log.e("AuthorizationCode", "Error retrieving access token", t);
            }
        });


    }

}
