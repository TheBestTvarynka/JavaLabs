package model;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileIO {
    public static Logger logger = Logger.getLogger(FileIO.class);
    public static Book[] readBooksFromFile (String filename) throws IOException {
        logger.debug("start reading from the file: " + filename);
        Gson gson = new Gson();
        Book[] books = null;
        try {
            Path path = Paths.get(filename);
            books = gson.fromJson(Files.readString(path), Book[].class);
        } catch (FileNotFoundException e) {
            logger.debug(e.getMessage());
            throw new IOException("Error: file not found!");
        } catch (IOException e) {
            logger.debug(e.getMessage());
            throw new IOException("Error: unable to read from file!");
        } catch (JsonSyntaxException e) {
            logger.debug(e.getMessage());
            throw new IOException("Error: input file has broken syntax!");
        }
        logger.debug("successful read data from the file " + filename);
        return books;
    }
    public static void writeBooksToFile (Book[] books, String filename) throws IOException {
        logger.debug("start writing to the file: " + filename);
        Gson gson = new Gson();
        try {
            Path path = Paths.get(filename);
            String booksStr = gson.toJson(books);
            Files.write(path, booksStr.getBytes());
        } catch (FileNotFoundException ex) {
            logger.debug(ex.getMessage());
            throw new IOException("Error: file not found!");
        } catch (IOException e) {
            logger.debug(e.getMessage());
            throw new IOException("Error: unable to write in file!");
        }
        logger.debug("successful write data to the file " + filename);
    }
}
