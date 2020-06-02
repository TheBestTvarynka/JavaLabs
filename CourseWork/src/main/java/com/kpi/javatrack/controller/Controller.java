package com.kpi.javatrack.controller;

import com.kpi.javatrack.model.*;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import utils.DataFormatter;
import com.kpi.javatrack.view.*;
import utils.DataSource;
import utils.OutputColor;

import java.util.concurrent.CancellationException;
import java.util.function.Function;

public class Controller {
    public static Logger logger = Logger.getLogger(Controller.class);
    static {
        logger.setLevel(Level.DEBUG);
    }
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
                logger.error(ex);
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
        changeLanguage();
        menu.printMessage(bookProcessing.readBooksFromFile());
    }

    public void printAllData() {
        menu.printData(DataFormatter.formatData(bookProcessing.selectAll()), OutputColor.OUTPUT);
    }

    public void selectByAuthor() {
        String author = menu.getAnswer("author_promt");
        Book[] books = bookProcessing.selectByAuthor(author);
        menu.printData(DataFormatter.formatData(books), OutputColor.OUTPUT);
    }

    public void selectByPublishing() {
        String publishing = menu.getAnswer("publishing_promt");
        Book[] books = bookProcessing.selectByPublishing(publishing);
        menu.printData(DataFormatter.formatData(books), OutputColor.OUTPUT);
    }

    public void selectByYearLater() {
        try {
            int year = getNumber("year_promt", Validator::isYear);
            Book[] books = bookProcessing.selectByYearLater(year);
            menu.printData(DataFormatter.formatData(books), OutputColor.OUTPUT);
        } catch (CancellationException ex) {}
    }

    public void generateNewData() {
        try {
            int amount = getNumber("amount_promt", Validator::isAmount);
            bookProcessing.setDataStore(DataSource.generateRandomBooks(amount));
            menu.printMessage("generated_books", OutputColor.OUTPUT);
            printAllData();
        } catch (CancellationException ex) {}
    }

    public void writeBooksToFile() {
        String filename = menu.getAnswer("filename_promt");
        String res = bookProcessing.writeBooksInFile(filename);
        menu.printMessage(res);
    }

    public void readBooksFromFile() {
        String filename = menu.getAnswer("filename_promt");
        String res = bookProcessing.readBooksFromFile(filename);
        menu.printMessage(res);
    }

    public void changeLanguage() {
        String newLanguage = menu.getAnswer("language_promt");
        menu.changeLanguage(newLanguage);
    }

    public void saveLastResult() {
        String filename = menu.getAnswer("filename_promt");
        String res = bookProcessing.writeLastResultBooksInFile(filename);
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
        } else if (action == 9) {
            changeLanguage();
        } else if (action == 10) {
            saveLastResult();
        }else {
            menu.printMessage("wrong_action", OutputColor.ERROR);
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