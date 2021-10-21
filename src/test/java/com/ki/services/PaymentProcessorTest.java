package com.ki.services;

import com.ki.models.BankTransfer;
import com.ki.models.Card;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;
import com.ki.Fixture;
import com.ki.models.Payment;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PaymentProcessorTest {
    @Test
    public void testGetPaymentsFromCard() {
        // Given a source file containing three card payments
        String fixturePath = Fixture.getPath("card_payments_mixed.csv");
        // And a payment processor
        PaymentProcessor sut = new PaymentProcessor();
        // When I call get payments
        Payment[] payments = sut.getPayments(fixturePath, "card");
        // Then I expect three payments to be returned
        assertEquals(3, payments.length);
        // And the cardId for each to be equal to the input fixture
        assertEquals(30, ((Card)payments[0].paymentMethod).getCardId());
        assertEquals(45, ((Card)payments[1].paymentMethod).getCardId());
        assertEquals(10, ((Card)payments[2].paymentMethod).getCardId());
    }

    @Test
    public void testGetPaymentsFromBankTransfer() {
        // Given a source file containing two bank transfer payments
        String fixturePath = Fixture.getPath("bank_payments.csv");
        // And a payment processor
        PaymentProcessor sut = new PaymentProcessor();
        // When I call get payments
        Payment[] payments = sut.getPayments(fixturePath, "bank");
        // Then I expect three payments to be returned
        assertEquals(2, payments.length);
        // And the cardId for each to be equal to the input fixture
        assertEquals(20, ((BankTransfer)payments[0].paymentMethod).getAccountId());
        assertEquals(60, ((BankTransfer)payments[1].paymentMethod).getAccountId());
    }


    @Test
    public void testGetPaymentsEmpty() {
        // Given a source file containing no payments
        String fixturePath = Fixture.getPath("card_payments_empty.csv");
        // And a payment processor
        PaymentProcessor sut = new PaymentProcessor();
        // When I call get payments
        Payment[] payments = sut.getPayments(fixturePath, "card");
        // Then I expect an empty list of payments to be returned
        assertEquals(0, payments.length);
    }

    @Test
    public void testVerifyPayments() {
        // Given a list of payments
        Payment mockPayment1 = createPayment(true);
        Payment mockPayment2 = createPayment(true);
        Payment mockPayment3 = createPayment(false);
        Payment[] payments = {mockPayment1, mockPayment2, mockPayment3};
        // And a payment processor
        PaymentProcessor sut = new PaymentProcessor();
        // When I call verifyPayments
        Payment[] actualPayment = sut.verifyPayments(payments);
        // Then I expect isSuccessful to be called for each payment
        then(mockPayment1).should(times(1)).isSuccessful();
        // And the filtered list of payments should be returned
        Payment[] expectedPayment = {mockPayment1, mockPayment2};
        assertArrayEquals(expectedPayment, actualPayment);
    }

    private Payment createPayment(Boolean successful) {
        Payment mockPayment = mock(Payment.class);
        given(mockPayment.isSuccessful())
                .willReturn(successful);
        return mockPayment;
    }
}
