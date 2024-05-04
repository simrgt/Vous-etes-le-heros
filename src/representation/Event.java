package representation;

public interface Event {
    String display();

    Event chooseNext();

    void addNextNode(Event node);
}
