package com.ki.mappings;

import com.ki.models.Card;
import com.ki.models.Payment;
import com.ki.models.PaymentMethod;
import com.opencsv.exceptions.CsvValidationException;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class CardPaymentMappingStrategyTest {
    private static final String CARD_HEADER_DEFINITION[] = new String[] {
            "customer_id", "date", "amount", "card_id", "card_status" };
    private static final String BANK_HEADER_DEFINITION[] = new String[] {
            "customer_id", "date", "amount", "bank_account_id" };

    private static String MOCK_ID = "123";
    private static String MOCK_DATE = "2019-01-12";
    private static String MOCK_AMOUNT = "900";
    private static String MOCK_CARD_ID = "30";
    private static String MOCK_CARD_STATUS = "processed";

    @Test
    public void populateNewBeanWhenSourceCard() throws CsvValidationException {
        // Given a list of strings (from a csv)
        String[] csvLine = {MOCK_ID, MOCK_DATE, MOCK_AMOUNT, MOCK_CARD_ID, MOCK_CARD_STATUS};
        // And a PaymentMappingStrategy
        CardPaymentMappingStrategy sut = new CardPaymentMappingStrategy();
        sut.setColumnMapping(CARD_HEADER_DEFINITION);
        // When I call populateNewBean
        Payment actual = sut.populateNewBean(csvLine);
        // Then I expect a returned payment like
        PaymentMethod expectedPaymentMethod = new Card(Integer.parseInt(MOCK_CARD_ID), MOCK_CARD_STATUS);
        Payment expected = new Payment(
                Integer.parseInt(MOCK_ID),
                LocalDate.parse(MOCK_DATE),
                Integer.parseInt(MOCK_AMOUNT),
                expectedPaymentMethod
        );
        assertEquals(expected, actual);
    }

    @Test(expected = CsvValidationException.class)
    public void populateNewBeanWhenSourceCardMismatchNumColumns() throws CsvValidationException {
        // Given a list of strings (from a csv)
        String[] csvLine = {MOCK_ID, MOCK_DATE, MOCK_AMOUNT, MOCK_CARD_ID, MOCK_CARD_STATUS};
        // And a PaymentMappingStrategy
        CardPaymentMappingStrategy sut = new CardPaymentMappingStrategy();
        sut.setColumnMapping(BANK_HEADER_DEFINITION);
        // When I call populateNewBean
        sut.populateNewBean(csvLine);
        // Then I expect an exception to be thrown (implicit)
    }

    @Test(expected = CsvValidationException.class)
    public void populateNewBeanWhenSourceBankMismatchHeaders() throws CsvValidationException {
        // Given a list of strings (from a csv)
        String[] csvLine = {MOCK_ID, MOCK_DATE, MOCK_AMOUNT, MOCK_CARD_ID, MOCK_CARD_STATUS};
        // And a PaymentMappingStrategy
        BankPaymentMappingStrategy sut = new BankPaymentMappingStrategy();
        sut.setColumnMapping("customer_id", "date", "amount", "card_id", "incorrect_column");
        // When I call populateNewBean
        sut.populateNewBean(csvLine);
        // Then I expect an exception to be thrown (implicit)
    }
}