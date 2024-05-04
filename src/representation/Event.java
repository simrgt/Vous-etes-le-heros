package representation;

public interface Event {
    String display();

    boolean isTerminal();

    Event getNextNode(int choice);
}
