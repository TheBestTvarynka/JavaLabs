package utils;

import model.Book;

public class DataFormatter {
    public static String formatData(Book[] books) {
        StringBuilder result = new StringBuilder("Books:");
        if (books != null) {
            for (Book book : books) {
                result.append('\n' + "-----------------------" + '\n'
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
        else {
            result.append("\nBooks not found!");
        }
        return result.toString();
    }
}
