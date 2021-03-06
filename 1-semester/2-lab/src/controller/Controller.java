package com.kpi.lab1.controller;

import com.kpi.lab1.model.*;
import com.kpi.lab1.view.MenuViewer;

import java.util.concurrent.CancellationException;
import java.util.function.Function;

public class Controller {
    MenuViewer menuViewer;
    BookSelector bookSelector;

    private int getNumber (String message, Function<String, Void> validateFn) {
        String answer;
        int result;
        while (true) {
            answer = menuViewer.getAnswer(message);
            if (answer.equals("cancel")) {
                throw new CancellationException();
            }
            try {
                validateFn.apply(answer);
                result = Integer.parseInt(answer);
            } catch (RuntimeException ex) {
                menuViewer.printMessage(ex.getMessage(), OutputColor.ERROR);
                continue;
            }
            break;
        }
        return result;
    }

    public Controller() {
        menuViewer = new MenuViewer(System.in, System.out);
        bookSelector = new BookSelector(new DataStore());
    }

    public void printAllData() {
        menuViewer.printMessage(DataFormatter.formatData(bookSelector.selectAll()), OutputColor.OUTPUT);
    }

    public void selectByAuthor() {
        String author = menuViewer.getAnswer("Enter author of the book:");
        Book[] books = bookSelector.selectByAuthor(author);
        menuViewer.printMessage(DataFormatter.formatData(books), OutputColor.OUTPUT);
    }

    public void selectByPublishing() {
        String publishing = menuViewer.getAnswer("Enter publishing of the book:");
        Book[] books = bookSelector.selectByPublishing(publishing);
        menuViewer.printMessage(DataFormatter.formatData(books), OutputColor.OUTPUT);
    }

    public void selectByYearLater() {
        try {
            int year = getNumber("Enter year ('cancel' for cancelling):", Validator::isYear);
            Book[] books = bookSelector.selectByYearLater(year);
            menuViewer.printMessage(DataFormatter.formatData(books), OutputColor.OUTPUT);
        } catch (CancellationException ex) {}
    }

    public void generateNewData() {
        try {
            int amount = getNumber("Enter amount of the books ('cancel' for cancelling):", Validator::isAmount);
            bookSelector.setDataStore(DataSource.generateRandomBooks(amount));
            menuViewer.printMessage("Generated books:", OutputColor.OUTPUT);
            printAllData();
        } catch (CancellationException ex) {}
    }

    public void perform(int action) {
        if (action == 1) {
            printAllData();
        } else if (action == 2) {
            generateNewData();
        } else if (action == 3) {
            selectByAuthor();
        } else if (action == 4) {
            selectByPublishing();
        } else if (action == 5) {
            selectByYearLater();
        } else {
            menuViewer.printMessage("Wrong action! Action not found.", OutputColor.ERROR);
        }
    }

    public void run() {
        String action;
        int actionInt;
        while (true) {
            action = menuViewer.getActions();
            try {
                Validator.isNumber(action);
            } catch (NumberFormatException ex) {
                menuViewer.printMessage(ex.getMessage(), OutputColor.ERROR);
                continue;
            }
            if (action.equals("6")) {
                break;
            } else {
                actionInt = Integer.parseInt(action);
                perform(actionInt);
            }
        }
    }
}
