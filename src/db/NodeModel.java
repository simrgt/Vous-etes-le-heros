package db;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NodeModel {
    private final int id;
    private final String displayed_text;
    private final List<Integer> children;
    private final String type;
    private final String sound_path;
    private final String image_path;

    public NodeModel(int id, String displayed_text, String children, String type, String sound_path, String image_path) {
        this.id = id;
        this.displayed_text = displayed_text;
        if ((children == null) || children.isEmpty()) this.children = List.of(Integer.MAX_VALUE);
        else this.children = Arrays.stream(children.split(",")).map(Integer::parseInt).toList();
        this.type = type;
        this.sound_path = sound_path;
        this.image_path = image_path;
    }

    public int getId() {
        return id;
    }

    public String getDisplayed_text() {
        return displayed_text;
    }

    public List<Integer> getChildren() {
        return children;
    }

    public String getType() {
        return type;
    }

    public String getSound_path() {
        return sound_path;
    }

    public String getImage_path() {
        return image_path;
    }
}
