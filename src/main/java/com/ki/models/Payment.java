package com.ki.models;

import com.ki.Config;

import java.math.BigDecimal;
import java.time.LocalDate;


public class Payment {
    private final static BigDecimal FEE_RATE = Config.getPaymentFeeRate();

    private int customerId;
    private LocalDate date;
    private int amount;
    private int fee;
    public PaymentMethod paymentMethod;

    public Payment(int customerId, LocalDate date, int totalAmount,
                   PaymentMethod paymentMethod
    ) {
        this.customerId = customerId;
        this.date = date;
        this.fee = FEE_RATE.multiply(new BigDecimal(totalAmount)).intValue();
        this.amount = totalAmount - this.fee;
        this.paymentMethod = paymentMethod;
    }

    public boolean isSuccessful() {
        return paymentMethod.isSuccessful();
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
