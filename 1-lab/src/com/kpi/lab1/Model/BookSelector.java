package com.kpi.lab1.Model;

public class BookSelector {
    public static Book[] selectByAuthor(Book[] books, String author) {
        if (books == null) {
            return null;
        }
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
    public static Book[] selectByPublishing(Book[] books, String publishing) {
        if (books == null) {
            return null;
        }
        Book[] resultBooks;
        // count books we need to select
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
        return resultBooks;
    }
    public static Book[] selectByYearLater(Book[] books, int year) {
        if (books == null) {
            return null;
        }
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