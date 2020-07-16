package com.dajj.moment.net.client.login;

public class UserLogin {

    /**
     * principal : 1088555@qq.com
     * credentials : 123
     * type : email
     */

    private String principal;
    private String credentials;
    private String type;

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getCredentials() {
        return credentials;
    }

    public void setCredentials(String credentials) {
        this.credentials = credentials;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
