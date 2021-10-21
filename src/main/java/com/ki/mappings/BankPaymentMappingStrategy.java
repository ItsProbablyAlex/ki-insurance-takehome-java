package com.ki.mappings;

import com.ki.models.BankTransfer;
import com.ki.models.Payment;
import com.ki.models.PaymentMethod;
import com.opencsv.exceptions.CsvBeanIntrospectionException;
import com.opencsv.exceptions.CsvValidationException;

import java.time.LocalDate;

public class BankPaymentMappingStrategy extends PaymentMappingStrategy {
    @Override
    public Payment populateNewBean(String[] line) throws CsvBeanIntrospectionException, CsvValidationException {
        // TODO: header validation should preface bean population rather than being part of it
        // Check that we have the correct number of columns in our CSV
        if (this.headerIndex.findMaxIndex() != 3) {
            throw new CsvValidationException();
        }
        // And that we have the right set of column headers
        if (!(this.headerIndex.getByPosition(0).equals("customer_id") &&
                this.headerIndex.getByPosition(1).equals("date") &&
                this.headerIndex.getByPosition(2).equals("amount") &&
                this.headerIndex.getByPosition(3).equals("bank_account_id"))) {
            throw new CsvValidationException();
        }
        // If all good build the payment method and populate the customer
        // TODO: Try/Catch exceptions when reading CSV values.
        int customerId = Integer.parseInt(line[0]);
        LocalDate date = LocalDate.parse(line[1]);
        int totalAmount = Integer.parseInt(line[2]);
        int accountId = Integer.parseInt(line[3]);
        PaymentMethod paymentMethod = new BankTransfer(accountId);
        return new Payment(customerId, date, totalAmount, paymentMethod);
    }
}
