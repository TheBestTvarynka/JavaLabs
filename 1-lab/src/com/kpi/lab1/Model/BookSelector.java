package com.kpi.lab1.Model;

public class BookSelector {
    public static Book[] selectByAuthor(Book[] books, String author) {
        Book[] resultBooks;
        // count books which we need to select
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
        return resultBooks;
    }
    public static Book[] selectByYear(Book[] books, int year) {
        Book[] resultBooks;
        // count books we need to select
        int count = 0;
        for (Book book: books) {
            if (year == book.getYear()) {
                count++;
            }
        }
        resultBooks = new Book[count];
        count = 0;
        for (Book book: books) {
            if (year == book.getYear()) {
                resultBooks[count++] = book;
            }
        }
        return resultBooks;
    }
    public static Book[] selectByYearLater(Book[] books, int year) {
        Book[] resultBooks;
        // count books we need to select
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
        return resultBooks;
    }
}
