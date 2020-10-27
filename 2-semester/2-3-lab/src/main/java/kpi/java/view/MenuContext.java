package kpi.java.view;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

public class MenuContext implements Menu {
    Scanner input;
    OutputStream output;

    public MenuContext(Scanner input, OutputStream output) {
        this.input = input;
        this.output = output;
    }

    public void print(String data) {
        try {
            output.write(data.getBytes());
        } catch(IOException e) {
            // log with logger this error
        }
    }

    public String read() {
        return input.nextLine();
    }

    public String getAnswer(String data) {
        try {
            output.write(data.getBytes());
            return input.nextLine();
        } catch(IOException e) {
            // log with logger this error
        }
        return "";
    }
}
