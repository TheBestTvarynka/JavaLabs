package kpi.java.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class MenuContext implements Menu {
    Scanner input;
    PrintStream output;

    public MenuContext(InputStream input, OutputStream output) {
        this.input = new Scanner(input);
        this.output = new PrintStream(output);
    }

    public void print(String data) {
        output.println(data);
    }

    public String read() {
        return input.nextLine();
    }

    public String getAnswer(String data) {
        output.println(data);
        return input.nextLine();
    }
}
