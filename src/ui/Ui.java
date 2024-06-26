package ui;

/**
 * User interface
 */
public interface Ui {
    /**
     * @param text text to show in the console
     */
    void show(String text);

    /**
     * @return user input
     */
    String askString();

    /**
     * @return user input
     */
    int ask();

    /**
     * @return the escape character
     */
    String getEscape();

    /**
     * @return the tabulation
     */
    String getTab();

    /**
     * @return the life balise
     */
    String getBaliseLife();
}
