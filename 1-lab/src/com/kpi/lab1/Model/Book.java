package com.kpi.lab1.Model;

public class Book {
    String bookIDNumber;
    String title;
    String author;
    String publishing;
    int year;
    int pageNumber;
    float price;

    public Book(String bookIDNumber, String title, String author, String publishing, int pageNumber, float price, int year) {
        this.bookIDNumber = bookIDNumber;
        this.title = title;
        this.author = author;
        this.publishing = publishing;
        this.pageNumber = pageNumber;
        this.price = price;
        this.year = year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public void setBookIDNumber(String bookIDNumber) {
        this.bookIDNumber = bookIDNumber;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setPublishing(String publishing) {
        this.publishing = publishing;
    }
    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public String getBookIDNumber() { return bookIDNumber; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getPublishing() { return publishing; }
    public int getYear() { return year; }
    public int getPageNumber() { return pageNumber; }
    public float getPrice() { return price; }
    @Override
    public String toString() {

        return "======== Book ========\n" +
                "bookIDNumber = " + bookIDNumber + '\n' +
                "title='" + title + '\n' +
                "author = " + author + '\n' +
                "publishing = " + publishing + '\n' +
                "pageNumber = " + pageNumber + '\n' +
                "price = " + price + '\n' +
                "year = " + year + '\n' +
                "======================\n";
    }
}
