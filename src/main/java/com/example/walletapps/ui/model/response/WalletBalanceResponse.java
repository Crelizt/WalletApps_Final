package com.example.walletapps.ui.model.response;

public class WalletBalanceResponse {
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
