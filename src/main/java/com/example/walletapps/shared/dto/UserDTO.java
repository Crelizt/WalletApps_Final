package com.example.walletapps.shared.dto;

import java.io.Serializable;

public class UserDTO implements Serializable {
    private static final long serialVersionUID = -2622339165734985866L;
    private long id;
    private String userId;
    private String userName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
