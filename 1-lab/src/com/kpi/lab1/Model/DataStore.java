package com.kpi.lab1.Model;

public class DataStore {
    Book[] data;
    DataStore(Book[] data) {
        this.data = data;
    }
    public void setData(Book[] data) {
        this.data = data;
    }
    public Book[] getData() { return this.data; }
}
