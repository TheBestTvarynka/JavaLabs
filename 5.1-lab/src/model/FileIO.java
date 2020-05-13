package model;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileIO {
    public static Logger logger = Logger.getLogger(FileIO.class);
    static {
        logger.setLevel(Level.DEBUG);
    }
    public static Book[] readBooksFromFile (String filename) throws IOException {
        logger.info("start reading from the file: " + filename);
        Gson gson = new Gson();
        Book[] books = null;
        try {
            Path path = Paths.get(filename);
            String fileData = Files.readString(path);
            books = gson.fromJson(fileData, Book[].class);
        } catch (FileNotFoundException e) {
            logger.error("Error: file " + filename + " not found!");
            throw new IOException("Error: file not found!");
        } catch (IOException e) {
            logger.error("Error: unable to read from file!");
            throw new IOException("Error: unable to read from file!");
        } catch (JsonSyntaxException e) {
            logger.error("Error: input file has broken syntax!");
            throw new IOException("Error: input file has broken syntax!");
        }
        logger.info("successful read data from the file " + filename);
        return books;
    }
    public static void writeBooksToFile (Book[] books, String filename) throws IOException {
        logger.info("start writing to the file: " + filename);
        Gson gson = new Gson();
        try {
            Path path = Paths.get(filename);
            String booksStr = gson.toJson(books);
            Files.write(path, booksStr.getBytes());
        } catch (FileNotFoundException ex) {
            logger.error("Error: file " + filename + " not found!");
            throw new IOException("Error: file not found!");
        } catch (IOException e) {
            logger.error("Error: unable to write in file!");
            throw new IOException("Error: unable to write in file!");
        }
        logger.info("successful write data to the file " + filename);
    }
}
