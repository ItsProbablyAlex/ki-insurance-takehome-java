package com.ki.models;

public class BankTransfer implements PaymentMethod {
    private int accountId;

    public BankTransfer(int accountId) {
        this.accountId = accountId;
    }

    public boolean isSuccessful() {
        return true;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
}
