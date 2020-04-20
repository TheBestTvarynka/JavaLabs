package com.kpi.lab1.model;

import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileIO {
    public static Book[] readBooksFromFile (String filename) throws IOException {
        Gson gson = new Gson();
        FileInputStream input = new FileInputStream(filename);
        StringBuilder booksStr = new StringBuilder("");
        byte byteData;
        while ((byteData = (byte)input.read()) != -1) {
            booksStr.append((char)byteData);
        }
        Book[] books = gson.fromJson(booksStr.toString(), Book[].class);
        input.close();
        return books;
    }
    public static void writeBooksToFile (Book[] books, String filename) throws IOException {
        Gson gson = new Gson();
        FileOutputStream output = new FileOutputStream(filename);
        String booksStr = gson.toJson(books);
        output.write(booksStr.getBytes());
        output.close();
    }
}
