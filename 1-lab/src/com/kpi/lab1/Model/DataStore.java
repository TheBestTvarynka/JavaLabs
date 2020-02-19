package com.kpi.lab1.Model;

import java.io.PrintStream;
import java.util.Arrays;

public class DataStore {
    Book[] data;
    public DataStore() {
        data = null;
    }
    public DataStore(Book[] data) {
        this.data = data;
    }
    public void setData(Book[] data) {
        this.data = data;
    }
    public Book[] getData() { return this.data; }
    public static String booksToString(Book[] books) {
        StringBuilder result = new StringBuilder();
        for (Book book: books) {
            result.append(book.toString());
        }
        return result.toString();
    }
    @Override
    public String toString() {
        return "DataStore{" +
                "data=" + Arrays.toString(data) +
                '}';
    }
}
