package com.ki.models;

import com.ki.Config;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PaymentTest {
    private final static BigDecimal FEE_RATE = Config.getPaymentFeeRate();
    private static final int MOCK_CUSTOMER_ID = 123;
    private static final int MOCK_AMOUNT = 10;
    private static final LocalDate MOCK_DATE = LocalDate.now();

    @Mock
    private PaymentMethod mockPaymentMethod;

    private Payment sut;

    @Before
    public void setUp() {
        sut = new Payment(MOCK_CUSTOMER_ID, MOCK_DATE, MOCK_AMOUNT, mockPaymentMethod);
    }

    @Test
    public void testIsSuccessful() {
        // Given the payment method returns that it was successful
        given(mockPaymentMethod.isSuccessful()).willReturn(true);
        // When I call isSuccessful
        Boolean actual = sut.isSuccessful();
        // Then I expect the payment method to return the success status
        then(mockPaymentMethod).should(times(1)).isSuccessful();
        // And the result of this call to be returned
        assertTrue(actual);
    }

    @Test
    public void testGetCustomerId() {
        // When I call getCustomerId
        int actual = sut.getCustomerId();
        // Then customerId will be returned
        assertEquals(MOCK_CUSTOMER_ID, actual);
    }

    @Test
    public void testSetCustomerId() {
        // Given a customerId
        int expectedCustomerId = 5;
        // When I call setCustomerId
        sut.setCustomerId(expectedCustomerId);
        // Then customerId will have been updated
        assertEquals(expectedCustomerId, sut.getCustomerId());
    }

    @Test
    public void testGetAmount() {
        // When I call getAmount
        int actual = sut.getAmount();
        // Then I expect the fee rate to be the initial amount minus th fee
        int expected = MOCK_AMOUNT - FEE_RATE.multiply(new BigDecimal(MOCK_AMOUNT)).intValue();
        assertEquals(expected, actual);
    }

    @Test
    public void testSetAmount() {
        // Given an amount
        int expectedAmount = 5;
        // When I call setAmount
        sut.setAmount(expectedAmount);
        // Then amount will have been updated
        assertEquals(expectedAmount, sut.getAmount());
    }

    @Test
    public void testGetFee() {
        // When I call getFee
        int actual = sut.getFee();
        // Then I expect the fee rate to be a product of the initial amount and the fee rate
        int expected = FEE_RATE.multiply(new BigDecimal(MOCK_AMOUNT)).intValue();
        assertEquals(expected, actual);
    }

    @Test
    public void testSetFee() {
        // Given a fee
        int expectedFee = 5;
        // When I call setFee
        sut.setFee(expectedFee);
        // Then fee will have been updated
        assertEquals(expectedFee, sut.getFee());
    }

    @Test
    public void testGetDate() {
        // When I call getDate
        LocalDate actual = sut.getDate();
        // Then I expect the payment date to be returned
        assertEquals(MOCK_DATE, actual);
    }

    @Test
    public void testSetDate() {
        // Given a date
        LocalDate expectedDate = LocalDate.now();
        // When I call setDate
        sut.setDate(expectedDate);
        // Then the payment date will have been updated
        assertEquals(expectedDate, sut.getDate());
    }

    @Test
    public void testGetPaymentMethod() {
        // When I call getPaymentMethod
        PaymentMethod actual = sut.getPaymentMethod();
        // Then I expect the payment method to be returned
        assertEquals(mockPaymentMethod, actual);
    }

    @Test
    public void testSetPaymentMethod() {
        // Given a payment method
        PaymentMethod expectedPaymentMethod = mock(PaymentMethod.class);
        // When I call setPaymentMethod
        sut.setPaymentMethod(expectedPaymentMethod);
        // Then the payment date will have been updated
        assertEquals(expectedPaymentMethod, sut.getPaymentMethod());
    }

    @Test
    public void equalsReturnsTrueWhenSameObject() {
        // When I compare the object to itself
        Boolean expected = sut.equals(sut);
        // Then I expect the result to be true
        //noinspection ConstantConditions
        assertTrue(expected);
    }

    @Test
    public void equalsReturnsFalseWhenNull() {
        // When I compare the object to null
        Boolean expected = sut.equals(null);
        // Then I expect the result to be true
        //noinspection ConstantConditions
        assertFalse(expected);
    }

    @Test
    public void equalsReturnsTrueOnPropertyEquality(){
        // Given an identical Card
        Payment payment = new Payment(MOCK_CUSTOMER_ID, MOCK_DATE, MOCK_AMOUNT, mockPaymentMethod);
        // When I compare the object to another with deep equality
        Boolean expected = sut.equals(payment);
        // Then I expect the result to be true
        assertTrue(expected);
    }
}
