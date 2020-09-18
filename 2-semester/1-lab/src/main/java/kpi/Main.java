package kpi;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        System.out.println("Type a name of the directory that you want to scan:");
        String dir = reader.nextLine();

        Path directory = Paths.get(dir);
        if (!Files.exists(directory)) {
            System.out.println("Entered directory does not exist!");
            System.exit(1);
        }

        new DirectoryScanner(dir).scan();
    }
}
