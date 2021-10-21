package com.ki.mappings;

import com.ki.models.Card;
import com.ki.models.Payment;
import com.ki.models.PaymentMethod;
import com.opencsv.exceptions.CsvBeanIntrospectionException;
import com.opencsv.exceptions.CsvValidationException;

import java.time.LocalDate;

public class CardPaymentMappingStrategy extends PaymentMappingStrategy {
    @Override
    public Payment populateNewBean(String[] line) throws CsvBeanIntrospectionException, CsvValidationException {
        // TODO: header validation should preface bean population rather than being part of it
        // Check that we have the correct number of columns in our CSV
        if (this.headerIndex.findMaxIndex() != 4) {
            throw new CsvValidationException();
        }
        // And that we have the right set of column headers
        if (!(this.headerIndex.getByPosition(0).equals("customer_id") &&
                this.headerIndex.getByPosition(1).equals("date") &&
                this.headerIndex.getByPosition(2).equals("amount") &&
                this.headerIndex.getByPosition(3).equals("card_id") &&
                this.headerIndex.getByPosition(4).equals("card_status"))) {
            throw new CsvValidationException();
        }
        // If all good build the payment method and populate the customer
        // TODO: Try/Catch exceptions when reading CSV values.
        int customerId = Integer.parseInt(line[0]);
        LocalDate date = LocalDate.parse(line[1]);
        int totalAmount = Integer.parseInt(line[2]);
        int cardId = Integer.parseInt(line[3]);
        String status = line[4];
        PaymentMethod paymentMethod = new Card(cardId, status);
        return new Payment(customerId, date, totalAmount, paymentMethod);
    }
}
