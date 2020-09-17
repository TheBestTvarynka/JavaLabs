package model;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileIO {
    public static Book[] readBooksFromFile (String filename) throws IOException {
        Gson gson = new Gson();
        Book[] books = null;
        try {
            Path path = Paths.get(filename);
            String fileData = Files.readString(path);
            books = gson.fromJson(fileData, Book[].class);
        } catch (FileNotFoundException e) {
            throw new IOException("file_not_found");
        } catch (IOException e) {
            throw new IOException("unable_to_read");
        } catch (JsonSyntaxException e) {
            throw new IOException("broken_syntax");
        }
        return books;
    }
    public static void writeBooksToFile (Book[] books, String filename) throws IOException {
        Gson gson = new Gson();
        try {
            Path path = Paths.get(filename);
            String booksStr = gson.toJson(books);
            Files.write(path, booksStr.getBytes());
        } catch (FileNotFoundException ex) {
            throw new IOException("file_not_found");
        } catch (IOException e) {
            throw new IOException("unable_to_write");
        }
    }
}
