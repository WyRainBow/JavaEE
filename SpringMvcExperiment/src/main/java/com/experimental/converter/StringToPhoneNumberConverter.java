package com.experimental.converter;

import com.experimental.entity.PhoneNumber;
import org.springframework.core.convert.converter.Converter;

// This converter could, for example, expect a specific format like "NNN-NNN-NNNN"
// and parse it, or simply wrap the string as is for this demo.
public class StringToPhoneNumberConverter implements Converter<String, PhoneNumber> {

    @Override
    public PhoneNumber convert(String source) {
        if (source == null || source.trim().isEmpty()) {
            return null;
        }
        System.out.println("StringToPhoneNumberConverter: Converting '" + source + "' to PhoneNumber object.");
        // For a real application, you might add validation or parsing here.
        // For example, if the format should be XXX-XXX-XXXX:
        // if (!source.matches("^\\d{3}-\\d{3}-\\d{4}$")) {
        //     throw new IllegalArgumentException("Invalid phone number format. Expected XXX-XXX-XXXX.");
        // }
        return new PhoneNumber(source);
    }
} 