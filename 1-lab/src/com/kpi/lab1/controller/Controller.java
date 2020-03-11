package com.kpi.lab1.controller;

import com.kpi.lab1.model.*;
import com.kpi.lab1.view.MenuViewer;

public class Controller {
    MenuViewer menuViewer;
    BookSelector bookSelector;

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
        String yearStr;
        int year;
        while (true) {
            yearStr = menuViewer.getAnswer("Enter year or 'cancel' for cancelling:");
            if (yearStr.equals("cancel")) {
                return;
            }
            try {
                Validator.isNumber(yearStr);
                year = Integer.parseInt(yearStr);
                Validator.isYear(year);
            } catch (NumberFormatException ex) {
                menuViewer.printMessage(ex.getMessage(), OutputColor.ERROR);
                continue;
            } catch (NotYearException ex) {
                menuViewer.printMessage(ex.getMessage(), OutputColor.ERROR);
                continue;
            }
            break;
        }
        Book[] books = bookSelector.selectByYearLater(year);
        menuViewer.printMessage(DataFormatter.formatData(books), OutputColor.OUTPUT);
    }

    public void generateNewData() {
        String amountStr;
        int amount;
        while (true) {
            amountStr = menuViewer.getAnswer("Enter amount of the books:");
            try {
                Validator.isNumber(amountStr);
                amount = Integer.parseInt(amountStr);
                Validator.isAmount(amount);
            } catch (NumberFormatException ex) {
                menuViewer.printMessage(ex.getMessage(), OutputColor.ERROR);
                continue;
            } catch (NotAmountException ex) {
                menuViewer.printMessage(ex.getMessage(), OutputColor.ERROR);
                continue;
            }
            break;
        }
        bookSelector.setDataStore(DataSource.generateRandomBooks(amount));
        menuViewer.printMessage("Generated books:", OutputColor.OUTPUT);
        printAllData();
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
