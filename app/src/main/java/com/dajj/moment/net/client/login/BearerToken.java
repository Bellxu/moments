package com.dajj.moment.net.client.login;

public class BearerToken {

    @Override
    public String toString() {
        return "BearerToken{" +
                "accessToken='" + accessToken + '\'' +
                ", tokenType='" + tokenType + '\'' +
                '}';
    }

    /**
     * accessToken : eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNTk0NzM3ODAyLCJleHAiOjE1OTU2MDE4MDJ9.AZZHdVSZh6TawPBXGQx7Rk8Ddy01yliAPDmLsHK99v5V1bjgT7V8fBkEI0k_oBAA-LKAcTXISEUIB_AAzx849w
     * tokenType : Bearer
     */

    private String accessToken;
    private String tokenType;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
