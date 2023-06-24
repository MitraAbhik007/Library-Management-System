package com.xfactor.openlibrary.domain;

public class Login {
    String username;
    String password;
    String user;
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }

    public Login(String username,String password,String user)
    {
        this.username=username;
        this.password=password;
        this.user=user;
    }



}
