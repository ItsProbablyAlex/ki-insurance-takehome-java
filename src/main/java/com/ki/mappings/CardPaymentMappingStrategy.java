package com.ki.mappings;

import com.ki.models.Card;
import com.ki.models.Payment;
import com.ki.models.PaymentMethod;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.exceptions.CsvBeanIntrospectionException;
import com.opencsv.exceptions.CsvValidationException;

import java.time.LocalDate;

public class CardPaymentMappingStrategy extends ColumnPositionMappingStrategy<Payment> {
    public CardPaymentMappingStrategy() {
        setType(Payment.class);
    }

    @Override
    public Payment populateNewBean(String[] line) throws CsvBeanIntrospectionException, CsvValidationException {
        String[] headers = super.getColumnMapping();
        if (headers.length != 5) {
            throw new CsvValidationException();
        }
        if (!(headers[0].equals("customer_id") &&
                headers[1].equals("date") &&
                headers[2].equals("amount") &&
                headers[3].equals("card_id") &&
                headers[4].equals("card_status"))) {
            throw new CsvValidationException();
        }
        int customerId = Integer.parseInt(line[0]);
        LocalDate date = LocalDate.parse(line[1]);
        int totalAmount = Integer.parseInt(line[2]);
        int cardId = Integer.parseInt(line[3]);
        String status = line[4];
        PaymentMethod paymentMethod = new Card(cardId, status);
        return new Payment(customerId, date, totalAmount, paymentMethod);
    }
}
