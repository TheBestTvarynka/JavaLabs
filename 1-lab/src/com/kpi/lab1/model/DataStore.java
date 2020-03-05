package com.kpi.lab1.model;

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

    @Override
    public String toString() {
        if (data == null) {
            return "Books:\nBooks not found\n";
        }
        StringBuilder dataString = new StringBuilder("Books:\n");
        for (Book book: data) {
            dataString.append(book.toString());
        }
        return dataString.toString();
    }
}
