package com.example.walletapps.shared.dto;

import java.io.Serializable;

public class WalletsBalanceDTO implements Serializable {
    private static final long serialVersionUID = -465309759079918902L;
    private String userName;
    private long totalBalance;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(long totalBalance) {
        this.totalBalance = totalBalance;
    }
}
