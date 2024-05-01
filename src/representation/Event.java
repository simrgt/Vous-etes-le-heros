package representation;

public interface Event {
    String display();

    Event chooseNext();
}
