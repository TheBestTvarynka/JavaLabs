package utils;

import model.Book;

public class DataFormatter {
    public static String formatData(Book[] books) {
        StringBuilder result = new StringBuilder("");
        if (books != null) {
            for (Book book : books) {
                result.append("-----------------------" + '\n'
                        + "BookID" + book.getBookIDNumber() + '\n'
                        + "Title: " + book.getTitle() + '\n'
                        + "Author: " + book.getAuthor() + '\n'
                        + "Publishing: " + book.getPublishing() + '\n'
                        + "Year: " + book.getYear() + '\n'
                        + "Pages amount: " + book.getPageNumber() + '\n'
                        + "Price: " + book.getPrice() + '\n'
                        + "-----------------------");
            }
        }
        return result.toString();
    }
}
