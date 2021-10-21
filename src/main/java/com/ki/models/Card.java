package com.ki.models;

public class Card implements PaymentMethod {
    private int cardId;
    private String status;

    public Card(int cardId, String status) {
        this.cardId = cardId;
        this.status = status;
    }

    public boolean isSuccessful() {
        return this.status.equals("processed");
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

