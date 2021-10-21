package com.ki.mappings;

import com.ki.models.BankTransfer;
import com.ki.models.Card;
import com.ki.models.Payment;
import com.ki.models.PaymentMethod;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.exceptions.CsvBeanIntrospectionException;
import com.opencsv.exceptions.CsvValidationException;

import java.time.LocalDate;

public class BankPaymentMappingStrategy extends ColumnPositionMappingStrategy<Payment> {
    public BankPaymentMappingStrategy() {
        setType(Payment.class);
    }

    @Override
    public Payment populateNewBean(String[] line) throws CsvBeanIntrospectionException, CsvValidationException {
        String[] headers = super.getColumnMapping();
        if (headers.length != 4) {
            throw new CsvValidationException();
        }
        if (!(headers[0].equals("customer_id") &&
                headers[1].equals("date") &&
                headers[2].equals("amount") &&
                headers[3].equals("bank_account_id"))) {
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
