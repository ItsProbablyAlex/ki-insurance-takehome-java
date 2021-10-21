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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BankTransfer c = (BankTransfer) obj;
        return accountId == c.accountId;
    }
}
