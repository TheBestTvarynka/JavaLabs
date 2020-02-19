package com.kpi.lab1.Model;

public class Validator {
    public static boolean isNumber(String str) {
        boolean result = true;
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException ex) {
            result = false;
        }
        return result;
    }
}
