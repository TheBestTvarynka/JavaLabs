package controller;

import model.*;
import utils.DataFormatter;
import view.*;
import utils.DataSource;
import utils.OutputColor;

import java.util.concurrent.CancellationException;
import java.util.function.Function;

public class Controller {
    Menu menu;
    BookProcessing bookProcessing;

    private int getNumber (String message, Function<String, Void> validateFn) {
        String answer;
        int result;
        while (true) {
            answer = menu.getAnswer(message);
            if (answer.equals("cancel")) {
                throw new CancellationException();
            }
            try {
                validateFn.apply(answer);
                result = Integer.parseInt(answer);
            } catch (RuntimeException ex) {
                menu.printMessage(ex.getMessage(), OutputColor.ERROR);
                continue;
            }
            break;
        }
        return result;
    }

    public Controller() {
        menu = new Menu(System.in, System.out);
        bookProcessing = new BookProcessing(new DataStore());
        menu.printMessage(bookProcessing.readBooksFromFile());
    }

    public void printAllData() {
        menu.printMessage(DataFormatter.formatData(bookProcessing.selectAll()), OutputColor.OUTPUT);
    }

    public void selectByAuthor() {
        String author = menu.getAnswer("Enter author of the book:");
        Book[] books = bookProcessing.selectByAuthor(author);
        menu.printMessage(DataFormatter.formatData(books), OutputColor.OUTPUT);
    }

    public void selectByPublishing() {
        String publishing = menu.getAnswer("Enter publishing of the book:");
        Book[] books = bookProcessing.selectByPublishing(publishing);
        menu.printMessage(DataFormatter.formatData(books), OutputColor.OUTPUT);
    }

    public void selectByYearLater() {
        try {
            int year = getNumber("Enter year ('cancel' for cancelling):", Validator::isYear);
            Book[] books = bookProcessing.selectByYearLater(year);
            menu.printMessage(DataFormatter.formatData(books), OutputColor.OUTPUT);
        } catch (CancellationException ex) {}
    }

    public void generateNewData() {
        try {
            int amount = getNumber("Enter amount of the books ('cancel' for cancelling):", Validator::isAmount);
            bookProcessing.setDataStore(DataSource.generateRandomBooks(amount));
            menu.printMessage("Generated books:", OutputColor.OUTPUT);
            printAllData();
        } catch (CancellationException ex) {}
    }

    public void writeBooksToFile() {
        String filename = menu.getAnswer("Enter name of the file:");
        String res = bookProcessing.writeBooksInFile(filename);
        menu.printMessage(res);
    }

    public void readBooksFromFile() {
        String filename = menu.getAnswer("Enter name of the file:");
        String res = bookProcessing.readBooksFromFile(filename);
        menu.printMessage(res);
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
        } else if (action == 6) {
            writeBooksToFile();
        } else if (action == 7) {
            readBooksFromFile();
        } else {
            menu.printMessage("Wrong action! Action not found.", OutputColor.ERROR);
        }
    }

    public void run() {
        String action;
        int actionInt;
        while (true) {
            action = menu.getActions();
            try {
                Validator.isNumber(action);
            } catch (NumberFormatException ex) {
                menu.printMessage(ex.getMessage(), OutputColor.ERROR);
                continue;
            }
            if (action.equals("8")) {
                menu.printMessage(bookProcessing.writeBooksInFile());
                break;
            } else {
                actionInt = Integer.parseInt(action);
                perform(actionInt);
            }
        }
    }
}