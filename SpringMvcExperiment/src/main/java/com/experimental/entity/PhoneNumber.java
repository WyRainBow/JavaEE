package com.experimental.entity;

public class PhoneNumber {
    private String numberString;

    public PhoneNumber(String numberString) {
        this.numberString = numberString;
    }

    public String getNumberString() {
        return numberString;
    }

    @Override
    public String toString() {
        return "PhoneNumber{" +
                "numberString='" + numberString + '\'' +
                '}';
    }
}
