package com.ki.mappings;

import com.ki.models.Payment;
import com.opencsv.CSVReader;
import com.opencsv.bean.HeaderNameBaseMappingStrategy;
import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.util.ResourceBundle;

public class PaymentMappingStrategy extends HeaderNameBaseMappingStrategy<Payment> {
    public PaymentMappingStrategy() {
        setType(Payment.class);
    }

    @Override
    public void captureHeader(CSVReader reader) throws IOException {
        if (this.type == null) {
            throw new IllegalStateException(ResourceBundle.getBundle("opencsv", this.errorLocale).getString("type.unset"));
        } else {
            String[] header = ArrayUtils.nullToEmpty(reader.readNextSilently());
            for(int i = 0; i < header.length; ++i) {
                if (header[i] == null) {
                    header[i] = "";
                }
            }
            this.headerIndex.initializeHeaderIndex(header);
        }
    }
}
