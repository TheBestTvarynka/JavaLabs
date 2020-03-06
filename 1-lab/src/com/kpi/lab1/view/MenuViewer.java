package com.kpi.lab1.view;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class MenuViewer {
    Scanner reader;
    PrintStream printer;
    public MenuViewer(InputStream input, PrintStream output) {
        reader = new Scanner(input);
        printer = output;
    }

    private String colorize(String color) {
        if (color.equals("red")) {
            return (char)27 + "[31m";
        } else if (color.equals("blue")) {
            return (char)27 + "[34m";
        } else if (color.equals("black")) {
            return (char)27 + "[30m";
        } else if (color.equals("white")) {
            return (char)27 + "[37m";
        } else if (color.equals("yellow")) {
            return (char) 27 + "[33m";
        }
        return (char)27 + "[37m";
    }

    public void printMenu() {
        printer.println(colorize("white"));
        printer.println("Enter number [1 - 6]:");
        printer.println("1 - print all books");
        printer.println("2 - generate new books");
        printer.println("3 - select books by the author");
        printer.println("4 - select books by the publishing");
        printer.println("5 - select books by the year");
        printer.println("6 - Exit");
    }

    public String getActions() {
        printMenu();
        String action = reader.nextLine();
        return action;
    }

    public void printMessage(String text, String color) {
        printer.println(colorize(color) + text);
    }

    public void printMessage(String text) {
        printer.println(colorize("white") + text);
    }

    public String getAnswer(String text, String color) {
        printer.println(colorize(color) + text);
        String answer = reader.nextLine();
        return answer;
    }

    public String getAnswer(String text) {
        return getAnswer(text, "white");
    }
}
