package ui;

public interface Ui {
    /**
     * @param text text to show
     */
    void show(String text);

    /**
     * @return user input
     */
    int ask();
}
