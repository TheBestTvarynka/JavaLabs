package com.kpi.lab1.model;

public class BookSelector {
    DataStore dataStore;

    public BookSelector() {
        this.dataStore = null;
    }

    public BookSelector(DataStore dataStore) {
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

    public Book[] selectByPublishing(String publishing) {
        if (dataStore == null) {
            return null;
        }
        Book[] books = dataStore.getData();
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

    public Book[] selectByYearLater(int year) {
        if (dataStore == null) {
            return null;
        }
        Book[] books = dataStore.getData();
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
