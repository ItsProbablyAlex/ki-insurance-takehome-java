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
        if (this.headerIndex.findMaxIndex() != 3) {
            throw new CsvValidationException();
        }
        if (!(this.headerIndex.getByPosition(0).equals("customer_id") &&
                this.headerIndex.getByPosition(1).equals("date") &&
                this.headerIndex.getByPosition(2).equals("amount") &&
                this.headerIndex.getByPosition(3).equals("bank_account_id"))) {
            throw new CsvValidationException();
        }
        int customerId = Integer.parseInt(line[0]);
        LocalDate date = LocalDate.parse(line[1]);
        int totalAmount = Integer.parseInt(line[2]);
        int accountId = Integer.parseInt(line[3]);
        PaymentMethod paymentMethod = new BankTransfer(accountId);
        return new Payment(customerId, date, totalAmount, paymentMethod);
    }
}
