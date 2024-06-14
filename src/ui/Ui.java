package ui;

public interface Ui {
    void show(String text);

    void clear();

    String askString();

    int ask();
}
