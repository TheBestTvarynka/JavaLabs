package com.kpi.lab1.model;

import java.util.Calendar;

public class Validator {
    public static void isNumber(String str) throws NumberFormatException {
        int asciiCode;
        for (int character = 0; character < str.length(); character++) {
            asciiCode = str.charAt(character);
            if (asciiCode == 45) {
                continue;
            }
            if (asciiCode < 48 || asciiCode > 57) {
                throw new NumberFormatException("Entered value is not number!");
            }
        }
    }
    public static void isYear(int year) throws NotYearException {
        final int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        if (year < 0 || year > currentYear) {
            throw new NotYearException("Entered year is not valid!");
        }
    }
    public static void isAmount(int amount) throws NotAmountException {
        if (amount < 1) {
            throw new NotAmountException();
        }
    }
}
