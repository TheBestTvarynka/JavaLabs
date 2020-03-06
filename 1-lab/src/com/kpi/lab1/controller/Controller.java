package com.kpi.lab1.controller;

import com.kpi.lab1.model.*;
import com.kpi.lab1.view.MenuViewer;

public class Controller {
    MenuViewer menuViewer;
    BookSelector bookSelector;
//    final String OUTPUT = "blue";
//    final String ERROR = "red";

    public Controller() {
        menuViewer = new MenuViewer(System.in, System.out);
        bookSelector = new BookSelector(new DataStore());
    }

    public void printAllData() {
        menuViewer.printMessage(DataFormatter.formatData(bookSelector.selectAll()), "blue");
    }

    public void selectByAuthor() {
        String author = menuViewer.getAnswer("Enter author of the book:");
        Book[] books = bookSelector.selectByAuthor(author);
        menuViewer.printMessage(DataFormatter.formatData(books), "blue");
    }

    public void selectByPublishing() {
        String publishing = menuViewer.getAnswer("Enter publishing of the book:");
        Book[] books = bookSelector.selectByPublishing(publishing);
        menuViewer.printMessage(DataFormatter.formatData(books), "blue");
    }

    public void selectByYearLater() {
        String year;
        while (true) {
            year = menuViewer.getAnswer("Enter year or 'cancel' for cancelling:");
            if (year.equals("cancel")) {
                return;
            }
            try {
                Validator.isNumber(year);
            } catch (NumberFormatException ex) {
                menuViewer.printMessage("Error: entered value is not int!", "red");
                continue;
            }
            break;
        }
        Book[] books = bookSelector.selectByYearLater(Integer.parseInt(year));
        menuViewer.printMessage(DataFormatter.formatData(books), "blue");
    }

    public void generateNewData() {
        String amount = menuViewer.getAnswer("Enter amount of the books:");
        if (!Validator.isNumber(amount)) {
            menuViewer.printMessage("Error: entered value is not int!", "red");
            return;
        }
        bookSelector.setDataStore(DataSource.generateRandomBooks(Integer.parseInt(amount)));
        menuViewer.printMessage("Generated books:", "blue");
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
            menuViewer.printMessage("Wrong action! Action not found.", "red");
        }
    }

    public void run() {
        String action;
        int actionInt;
        while (true) {
            action = menuViewer.getActions();
            if (!Validator.isNumber(action)) {
                menuViewer.printMessage("Error: entered value is not int!", "red");
            } else if (action.equals("6")) {
                break;
            } else {
                actionInt = Integer.parseInt(action);
                perform(actionInt);
            }
        }
    }
}
