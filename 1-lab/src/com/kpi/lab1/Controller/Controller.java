package com.kpi.lab1.Controller;

import com.kpi.lab1.Model.*;
import com.kpi.lab1.View.MenuViewer;

public class Controller {
    DataStore dataStore;
    MenuViewer menuViewer;

    public Controller() {
        dataStore = new DataStore();
        menuViewer = new MenuViewer(System.in, System.out);
    }
    public void printAllData() {
        menuViewer.printMessage(DataFormatter.formatData(dataStore.getData()), "blue");
    }
    public void selectByAuthor() {
        String author = menuViewer.getAnswer("Enter author of the book:");
        Book[] books = BookSelector.selectByAuthor(dataStore.getData(), author);
        menuViewer.printMessage(DataFormatter.formatData(books), "blue");
    }
    public void selectByPublishing() {
        String publishing = menuViewer.getAnswer("Enter publishing of the book:");
        Book[] books = BookSelector.selectByPublishing(dataStore.getData(), publishing);
        menuViewer.printMessage(DataFormatter.formatData(books), "blue");
    }
    public void selectByYearLater() {
        String year = menuViewer.getAnswer("Enter year:");
        if (!Validator.isNumber(year)) {
            menuViewer.printMessage("Error: entered value is not int!", "red");
            return;
        }
        Book[] books = BookSelector.selectByYearLater(dataStore.getData(), Integer.parseInt(year));
        menuViewer.printMessage(DataFormatter.formatData(books), "blue");
    }
    public void generateNewData() {
        String amount = menuViewer.getAnswer("Enter amount of the books:");
        if (!Validator.isNumber(amount)) {
            menuViewer.printMessage("Error: entered value is not int!", "red");
            return;
        }
        dataStore.setData(DataSource.generateRandomBooks(Integer.parseInt(amount)));
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
