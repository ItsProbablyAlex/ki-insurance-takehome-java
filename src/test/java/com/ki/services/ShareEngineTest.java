package com.ki.services;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import com.ki.models.Payment;
import com.ki.models.ShareOrder;

import java.math.BigDecimal;

public class ShareEngineTest {
    @Test
    public void testGenerateShareOrdersDifferentCustomers() {
        // Given a share engine
        ShareEngine sut = new ShareEngine();
        // And some mock payments
        Payment[] payments = new Payment[] {
                createPayment(456, 900),
                createPayment(123, 4200),
        };
        // When I call generateShareOrders
        ShareOrder[] result = sut.generateShareOrders(new BigDecimal("1.2"), payments);
        // Then two share orders will be returned
        assertEquals(2, result.length);
        // And both will have generated the share order correctly
        assertEquals(3500, result[0].getShares());
        assertEquals(750, result[1].getShares());

    }

    @Test
    public void testGenerateShareOrdersSameCustomer() {
        // Given a share engine
        ShareEngine sut = new ShareEngine();
        // And some mock payments
        int customerId = 456;
        Payment[] payments = new Payment[] {
                createPayment(customerId, 900),
                createPayment(customerId, 4200),
        };
        // When I call generateShareOrders
        ShareOrder[] result = sut.generateShareOrders(new BigDecimal("1.2"), payments);
        // Then one share orders will be returned
        assertEquals(1, result.length);
        // And both will generated the share order correctly
        assertEquals(4250, result[0].getShares());

    }

    private Payment createPayment(int customerId, int amount) {
        Payment payment = mock(Payment.class);
        given(payment.getCustomerId()).willReturn(customerId);
        given(payment.getAmount()).willReturn(amount);
        return payment;
    }
}
