package kpi.java.view;

public interface View {
    void print(String data);
    void print(String data, String color);
    void error(String data);
    String read();
    String getAnswer(String data);
}
