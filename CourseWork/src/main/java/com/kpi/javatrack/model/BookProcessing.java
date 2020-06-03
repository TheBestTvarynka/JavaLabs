package com.kpi.javatrack.model;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.IOException;

public class BookProcessing {
    public static String standartFile = "datafiles/saved.json";
    public static Logger logger = Logger.getLogger(BookProcessing.class);
    static {
        logger.setLevel(Level.DEBUG);
    }
    DataStore dataStore;
    Book[] lastResult;

    private String writeInFile(String filename, Book[] books) {
        try {
            FileIO.writeBooksToFile(books, filename);
        } catch (IOException e) {
            logger.error(e);
            return e.getMessage();
        }
        logger.info("All books successfully written in " + filename);
        return "successfully_writing";
    }

    public BookProcessing() {
        this.dataStore = null;
    }

    public BookProcessing(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    public void setDataStore(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    public void setDataStore(Book[] books) {
        if (dataStore != null) {
            dataStore.setData(books);
        } else {
            dataStore = new DataStore(books);
        }
    }

    public String writeBooksInFile(String filename) {
        return writeInFile(filename, dataStore.getData());
    }

    public String writeBooksInFile() {
        return writeBooksInFile(standartFile);
    }

    public String writeLastResultBooksInFile(String filename) {
        return writeInFile(filename, lastResult == null ? dataStore.getData() : lastResult);
    }

    public String readBooksFromFile(String filename) {
        try {
            dataStore.setData(FileIO.readBooksFromFile(filename));
        } catch (IOException e) {
            logger.error(e);
            return e.getMessage();
        }
        logger.info("All books successfully read from " + filename);
        return "successfully_reading";
    }

    public String readBooksFromFile() {
        return readBooksFromFile(standartFile);
    }

    public Book[] selectAll() {
        return dataStore.getData();
    }

    public Book[] selectByAuthor(String author) {
        if (dataStore == null) {
            return null;
        }
        Book[] books = dataStore.getData();
        if (books == null) {
            return null;
        }
        Book[] resultBooks;
        int count = 0;
        for (Book book: books) {
            if (author.equals(book.getAuthor())) {
                count++;
            }
        }
        resultBooks = new Book[count];
        count = 0;
        for (Book book: books) {
            if (author.equals(book.getAuthor())) {
                resultBooks[count++] = book;
            }
        }
        lastResult = resultBooks;
        return resultBooks;
    }

    public Book[] selectByPublishing(String publishing) {
        if (dataStore == null) {
            return null;
        }
        Book[] books = dataStore.getData();
        if (books == null) {
            return null;
        }
        Book[] resultBooks;
        int count = 0;
        for (Book book: books) {
            if (publishing.equals(book.getPublishing())) {
                count++;
            }
        }
        resultBooks = new Book[count];
        count = 0;
        for (Book book: books) {
            if (publishing.equals(book.getPublishing())) {
                resultBooks[count++] = book;
            }
        }
        lastResult = resultBooks;
        return resultBooks;
    }

    public Book[] selectByYearLater(int year) {
        if (dataStore == null) {
            return null;
        }
        Book[] books = dataStore.getData();
        if (books == null) {
            return null;
        }
        Book[] resultBooks;
        int count = 0;
        for (Book book: books) {
            if (year < book.getYear()) {
                count++;
            }
        }
        resultBooks = new Book[count];
        count = 0;
        for (Book book: books) {
            if (year < book.getYear()) {
                resultBooks[count++] = book;
            }
        }
        lastResult = resultBooks;
        return resultBooks;
    }
}
