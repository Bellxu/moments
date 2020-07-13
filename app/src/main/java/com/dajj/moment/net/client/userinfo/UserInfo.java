package com.dajj.moment.net.client.userinfo;

import java.util.List;

public class UserInfo {

    /**
     * id : 1
     * authorities : [{"authority":"ROLE_USER"}]
     * username : dapengcheng
     * accountNonExpired : true
     * accountNonLocked : true
     * credentialsNonExpired : true
     * enabled : true
     */

    private int id;
    private String username;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private List<AuthoritiesBean> authorities;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<AuthoritiesBean> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<AuthoritiesBean> authorities) {
        this.authorities = authorities;
    }

    public static class AuthoritiesBean {
        /**
         * authority : ROLE_USER
         */

        private String authority;

        public String getAuthority() {
            return authority;
        }

        public void setAuthority(String authority) {
            this.authority = authority;
        }
    }
}
