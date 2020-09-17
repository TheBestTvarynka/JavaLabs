package controller;

import java.util.Calendar;
import controller.exceptions.NotAmountException;
import controller.exceptions.NotYearException;

public class Validator {
    public static void isNumber(String str) throws NumberFormatException {
        int asciiCode;
        for (int character = 0; character < str.length(); character++) {
            asciiCode = str.charAt(character);
            if (asciiCode == 45) {
                continue;
            }
            if (asciiCode < 48 || asciiCode > 57) {
                throw new NumberFormatException("Entered value " + str + " is not number!");
            }
        }
    }
    public static Void isYear(String yearStr) throws NotYearException {
        try {
            Validator.isNumber(yearStr);
            int year = Integer.parseInt(yearStr);
            final int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            if (year < 0 || year > currentYear) {
                throw new NotYearException("Entered year " + yearStr + " is not valid!");
            }
        } catch (NumberFormatException ex) {
            throw new NotYearException("Entered year " + yearStr + " is not a number!");
        }
        return null;
    }
    public static Void isAmount(String amountStr) throws NotAmountException {
        try {
            Validator.isNumber(amountStr);
            int amount = Integer.parseInt(amountStr);
            if (amount < 1) {
                throw new NotAmountException("Entered amount " + amountStr + " is not valid!");
            }
        } catch (NumberFormatException ex) {
            throw new NotAmountException("Entered amount " + amountStr + " is not a number!");
        }
        return null;
    }
}