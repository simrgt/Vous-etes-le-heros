package representation;

public abstract class Node implements Event {
    private int id;
    private static int serial = 0;

    public Node() {
        this.id = serial;
        serial++;
    }


}
