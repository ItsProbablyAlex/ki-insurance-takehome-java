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
    // TODO: We could give a better interface to other modules by making methods in this class static.
    // TODO: Overload getPayments to give
    public Payment[] getPayments(String csvPath, String source) {
        // Create a map of provided sources to Payment Mapping strategies
        Map<String, PaymentMappingStrategy> mappingStrategies = new HashMap<String, PaymentMappingStrategy>(){{
            put("card", new CardPaymentMappingStrategy());
            put("bank", new BankPaymentMappingStrategy());
        }};
        // If a valid mapping strategy has been provided use the overload
        if (mappingStrategies.containsKey(source)) {
            return getPayments(csvPath, mappingStrategies.get(source));
        }
        // Else return an empty array
        // TODO: we could alternatively silently fail when an invalid payment method is provided
        return new Payment[]{};
    }

    public Payment[] getPayments(String csvPath, PaymentMappingStrategy strategy) {
        List<Payment> payments = new ArrayList<>();
        try {
            FileReader reader = new FileReader(csvPath);
            payments = new CsvToBeanBuilder<Payment>(reader)
                    .withMappingStrategy(strategy)
                    .build()
                    .parse();
        } catch (IOException e) {
            e.printStackTrace();
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
