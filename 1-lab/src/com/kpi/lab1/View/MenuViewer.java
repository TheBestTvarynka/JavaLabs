package com.kpi.lab1.View;

import com.kpi.lab1.Model.Validator;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuViewer {
    Scanner reader;
    PrintStream printer;
    public MenuViewer(InputStream input, PrintStream output) {
        reader = new Scanner(input);
        printer = output;
    }
    public void printMenu() {
        printer.println("Enter number [1 - 6]:");
        printer.println("1 - print all books");
        printer.println("2 - generate new books");
        printer.println("3 - select books by the author");
        printer.println("4 - select books by the publishing");
        printer.println("5 - select books by the year");
        printer.println("6 - Exit");
    }
    public int getActions() {
        printMenu();
        String action = reader.nextLine();
        if (!Validator.isNumber(action)) {
            return -1;
        }
        return Integer.parseInt(action);
    }
    public void printMessage(String text) {
        printer.println(text);
    }
    public String getAnswer(String text) {
        printer.println(text);
//        reader.nextLine();
        String answer = reader.nextLine();
        return answer;
    }
}
