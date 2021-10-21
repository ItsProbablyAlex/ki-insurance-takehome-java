package com.ki.mappings;

import com.ki.Fixture;
import com.ki.models.BankTransfer;
import com.ki.models.Card;
import com.ki.models.Payment;
import com.ki.models.PaymentMethod;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.opencsv.exceptions.CsvValidationException;
import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

import static org.junit.Assert.*;

public class BankPaymentMappingStrategyTest {

    private static final String CARD_FIXTURE_PATH = Fixture.getPath("card_payments_empty.csv");
    private static final String BANK_FIXTURE_PATH = Fixture.getPath("bank_payments_empty.csv");

    private static String MOCK_ID = "123";
    private static String MOCK_DATE = "2019-01-12";
    private static String MOCK_AMOUNT = "900";
    private static String MOCK_ACCOUNT_ID = "30";

    @Test
    public void testPopulateNewBeanWhenValid() throws CsvValidationException, CsvRequiredFieldEmptyException, IOException {
        // Given a set of source headers
        CSVReader reader = new CSVReader(new FileReader(BANK_FIXTURE_PATH));
        // and a line from a csv
        String[] csvLine = {MOCK_ID, MOCK_DATE, MOCK_AMOUNT, MOCK_ACCOUNT_ID};
        // And a PaymentMappingStrategy
        BankPaymentMappingStrategy sut = new BankPaymentMappingStrategy();
        sut.captureHeader(reader);
        // When I call populateNewBean
        Payment actual = sut.populateNewBean(csvLine);
        // Then I expect a returned payment like
        PaymentMethod expectedPaymentMethod = new BankTransfer(Integer.parseInt(MOCK_ACCOUNT_ID));
        Payment expected = new Payment(
                Integer.parseInt(MOCK_ID),
                LocalDate.parse(MOCK_DATE),
                Integer.parseInt(MOCK_AMOUNT),
                expectedPaymentMethod
        );
        assertEquals(expected, actual);
    }

    @Test(expected = CsvValidationException.class)
    public void testPopulateNewBeanWhenMismatchNumColumns() throws CsvValidationException, IOException, CsvRequiredFieldEmptyException {
        // Given a set of source headers
        CSVReader reader = new CSVReader(new FileReader(CARD_FIXTURE_PATH));
        // And a line from a csv
        String[] csvLine = {MOCK_ID, MOCK_DATE, MOCK_AMOUNT, MOCK_ACCOUNT_ID, "Another Column"};
        // And a PaymentMappingStrategy
        BankPaymentMappingStrategy sut = new BankPaymentMappingStrategy();
        sut.captureHeader(reader);
        // When I call populateNewBean
        sut.populateNewBean(csvLine);
        // Then I expect an exception to be thrown (implicit)
    }

    @Test(expected = CsvValidationException.class)
    public void testPopulateNewBeanWhenMismatchHeaders() throws CsvValidationException, IOException, CsvRequiredFieldEmptyException {
        // Given a set of source headers
        CSVReader reader = new CSVReader(new FileReader(CARD_FIXTURE_PATH));
        // And a line from a csv
        String[] csvLine = {MOCK_ID, MOCK_DATE, MOCK_AMOUNT, MOCK_ACCOUNT_ID};
        // And a PaymentMappingStrategy
        BankPaymentMappingStrategy sut = new BankPaymentMappingStrategy();
        sut.captureHeader(reader);
        // When I call populateNewBean
        sut.populateNewBean(csvLine);
        // Then I expect an exception to be thrown (implicit)
    }
}