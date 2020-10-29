package kpi.java.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class ViewContext implements View {
    Scanner input;
    PrintStream output;

    public ViewContext(InputStream input, OutputStream output) {
        this.input = new Scanner(input);
        this.output = new PrintStream(output);
    }

    private String colorize(String color) {
        switch (color) {
            case "red":
                return (char) 27 + "[31m";
            case "blue":
                return (char) 27 + "[34m";
//            case "black":
//                return (char) 27 + "[30m";
            case "white":
                return (char) 27 + "[30m";
            case "yellow":
                return (char) 27 + "[33m";
            case "green":
                return (char) 27 + "[36m";
        }
        return (char)27 + "[37m";
    }

    @Override
    public void print(String data) {
        output.println(colorize("white") + data);
    }

    @Override
    public void print(String data, String color) {
        output.println(colorize(color) + data);
    }

    @Override
    public void error(String error) {
        output.println(colorize("red") + error);
    }

    @Override
    public String read() {
        return input.nextLine();
    }

    @Override
    public String getAnswer(String data) {
        output.println(colorize("white") + data);
        return input.nextLine();
    }
}
