package com.kpi.javatrack.view;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Locale;
import java.util.Scanner;

public class Menu {
    Scanner reader;
    PrintStream printer;
    LanguageManager languageManager;
    public Menu(InputStream input, PrintStream output) {
        reader = new Scanner(input);
        printer = output;
        languageManager = LanguageManager.INSTANCE;
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
        printer.println(languageManager.getLabel("menu_title"));
        printer.println(languageManager.getLabel("menu1"));
        printer.println(languageManager.getLabel("menu2"));
        printer.println(languageManager.getLabel("menu3"));
        printer.println(languageManager.getLabel("menu4"));
        printer.println(languageManager.getLabel("menu5"));
        printer.println(languageManager.getLabel("menu6"));
        printer.println(languageManager.getLabel("menu7"));
        printer.println(languageManager.getLabel("menu8"));
        printer.println(languageManager.getLabel("menu9"));
        printer.println(languageManager.getLabel("menu10"));
    }

    public String getActions() {
        printMenu();
        String action = reader.nextLine();
        return action;
    }

    public void printMessage(String text, String color) {
        printer.println(colorize(color) + languageManager.getLabel(text));
    }

    public void printMessage(String text) {
        printer.println(colorize("white") + languageManager.getLabel(text));
    }

    public void printData(String data, String color) {
        printer.println(colorize(color) + data);
    }

    public String getAnswer(String text, String color) {
        printer.println(colorize(color) + languageManager.getLabel(text));
        String answer = reader.nextLine();
        return answer;
    }

    public String getAnswer(String text) {
        return getAnswer(text, "white");
    }

    public void changeLanguage(String newLanguage) {
        if (newLanguage.equals("uk")) {
            languageManager.changeLanguage(new Locale("uk", "UA"));
        } else if (newLanguage.equals("en")) {
            languageManager.changeLanguage(Locale.getDefault());
        } else {
            printMessage("wrong_language", "red");
            return;
        }
        printMessage("successfully_changed_language");
    }
}
