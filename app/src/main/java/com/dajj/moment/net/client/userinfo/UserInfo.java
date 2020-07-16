package com.dajj.moment.net.client.userinfo;

import java.util.List;
import java.util.Map;

public class UserInfo {

    /**
     * id : 1
     * username : dapengcheng
     * phoneNum : null
     * email : 1088555@qq.com
     * authorities : [{"authority":"ROLE_USER"}]
     * attributes : null
     * accountNonExpired : true
     * accountNonLocked : true
     * credentialsNonExpired : true
     * enabled : true
     * name : 1
     */

    private int id;
    private String username;
    private String phoneNum;
    private String email;
    private Map attributes;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private String name;
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

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Map getAttributes() {
        return attributes;
    }

    public void setAttributes(Map attributes) {
        this.attributes = attributes;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
