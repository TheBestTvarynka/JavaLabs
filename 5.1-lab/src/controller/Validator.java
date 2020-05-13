package controller;

import java.util.Calendar;
import controller.exceptions.NotAmountException;
import controller.exceptions.NotYearException;
import model.FileIO;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class Validator {
    public static Logger logger = Logger.getLogger(FileIO.class);
    static {
        logger.setLevel(Level.DEBUG);
    }
    public static void isNumber(String str) throws NumberFormatException {
        int asciiCode;
        for (int character = 0; character < str.length(); character++) {
            asciiCode = str.charAt(character);
            if (asciiCode == 45) {
                continue;
            }
            if (asciiCode < 48 || asciiCode > 57) {
                logger.error("Entered value " + str + " is not number!");
                throw new NumberFormatException("Entered value is not number!");
            }
        }
    }
    public static Void isYear(String yearStr) throws NotYearException {
        try {
            Validator.isNumber(yearStr);
            int year = Integer.parseInt(yearStr);
            final int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            if (year < 0 || year > currentYear) {
                logger.error("Entered year " + yearStr + " is not valid!");
                throw new NotYearException("Entered year is not valid!");
            }
        } catch (NumberFormatException ex) {
            logger.error("Entered year " + yearStr + " is not a number!");
            throw new NotYearException("Entered year is not a number!");
        }
        return null;
    }
    public static Void isAmount(String amountStr) throws NotAmountException {
        try {
            Validator.isNumber(amountStr);
            int amount = Integer.parseInt(amountStr);
            if (amount < 1) {
                logger.error("Entered amount " + amountStr + " is not valid!");
                throw new NotAmountException("Entered amount is not valid!");
            }
        } catch (NumberFormatException ex) {
            logger.error("Entered year " + amountStr + " is not a number!");
            throw new NotAmountException("Entered amount is not a number!");
        }
        return null;
    }
}