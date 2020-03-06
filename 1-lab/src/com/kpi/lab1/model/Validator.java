package com.kpi.lab1.model;

public class Validator {
    public static boolean isNumber(String str) throws NumberFormatException {
        Integer.parseInt(str);
        return true;
    }
}
