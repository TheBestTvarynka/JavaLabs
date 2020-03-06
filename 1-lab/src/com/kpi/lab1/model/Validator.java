package com.kpi.lab1.model;

import java.util.Calendar;

public class Validator {
    public static boolean isNumber(String str) throws NumberFormatException {
        for (int character = 0; character < str.length(); character++) {
            if (str.charAt(character) < 48 || str.charAt(character) > 57) {
                throw new NumberFormatException("Entered value id not number!");
            }
        }
        return true;
    }
    public static void isYear(int year) throws NotYearException {
        final int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        if (year < 0 || year > currentYear) {
            throw new NotYearException("Entered year is not valid!");
        }
    }
}
