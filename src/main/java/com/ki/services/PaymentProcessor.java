package com.ki.services;

import com.ki.mappings.BankPaymentMappingStrategy;
import com.ki.mappings.CardPaymentMappingStrategy;
import com.ki.mappings.PaymentMappingStrategy;
import com.ki.models.Payment;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentProcessor {
    public Payment[] getPayments(String csvPath, String source) {
        Map<String, PaymentMappingStrategy> mappingStrategies = new HashMap<String, PaymentMappingStrategy>(){{
            put("card", new CardPaymentMappingStrategy());
            put("bank", new BankPaymentMappingStrategy());
        }};
        List<Payment> payments = new ArrayList<>();
        if (mappingStrategies.containsKey(source)) {
            try {
                FileReader reader = new FileReader(csvPath);
                PaymentMappingStrategy strategy = mappingStrategies.get(source);
                payments = new CsvToBeanBuilder<Payment>(reader)
                        .withMappingStrategy(strategy)
                        .build()
                        .parse();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return payments.toArray(new Payment[]{});
    }

    public Payment[] verifyPayments(Payment[] payments) {
        ArrayList<Payment> filtered = new ArrayList<>();
        for (Payment payment : payments) {
            if (payment.isSuccessful()) {
                filtered.add(payment);
            }
        }
        return filtered.toArray(new Payment[]{});
    }
}
