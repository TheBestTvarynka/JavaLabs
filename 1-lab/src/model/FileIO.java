package model;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileIO {
    public static Book[] readBooksFromFile (String filename) throws IOException {
        Gson gson = new Gson();
        StringBuilder booksStr = new StringBuilder("");
        Book[] books = null;
        try {
            FileInputStream input = new FileInputStream(filename);
            byte byteData;
            while ((byteData = (byte)input.read()) != -1) {
                booksStr.append((char)byteData);
            }
            books = gson.fromJson(booksStr.toString(), Book[].class);
        } catch (FileNotFoundException e) {
            throw new IOException("Error: file not found!");
        } catch (IOException e) {
            throw new IOException("Error: unable to read from file!");
        } catch (JsonSyntaxException e) {
            throw new IOException("Error: input file has broken syntax!");
        }
        return books;
    }
    public static void writeBooksToFile (Book[] books, String filename) throws IOException {
        Gson gson = new Gson();
        try {
            FileOutputStream output = new FileOutputStream(filename);
            String booksStr = gson.toJson(books);
            output.write(booksStr.getBytes());
        } catch (FileNotFoundException ex) {
            throw new IOException("Error: file not found!");
        } catch (IOException e) {
            throw new IOException("Error: unable to write in file!");
        }
    }
}
