package representation.node;

import db.NodeModel;
import db.SQLiteJDBC;
import representation.Event;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

public class EventFactory {
    private static final SQLiteJDBC sqlite;

    static {
        try {
            sqlite = new SQLiteJDBC();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected static Event createNode(int id) {
        try {
            NodeModel nodeModel = sqlite.getNode(id);
            // Récupérer le constructeur correspondant au type de nœud
            Class<? extends Node> nodeClass = NodeType.valueOf(nodeModel.getType()).getNodeClass();
            Constructor<? extends Node> constructor = nodeClass.getDeclaredConstructor(int.class, String.class, List.class);

            // Instancier le nœud en utilisant le constructeur
            Event node = constructor.newInstance(nodeModel.getId(), nodeModel.getDisplayed_text(), nodeModel.getChildren());
            if (nodeModel.getSound_path() != null) {
                node = new SoundNode(node, nodeModel.getSound_path());
            }
            return node;
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public static Event createStartNode() {
        return createNode(0);
    }
}

