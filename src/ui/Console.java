package ui;

public class Console implements Ui {
    @Override
    public void afficher(String text) {
        System.out.println(text);
    }

    @Override
    public String demander(String text) {
        System.out.println(text);
        return System.console().readLine();
    }
}
