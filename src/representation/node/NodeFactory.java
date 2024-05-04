package representation.node;

import db.NodeModel;
import db.SQLiteJDBC;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

public class NodeFactory {
    private static final Random random = new Random();
    private static final SQLiteJDBC sqlite;

    static {
        try {
            sqlite = new SQLiteJDBC();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*private static Node createNode(NodeType nodeType) {
        try {
            // Récupérer le constructeur correspondant au type de nœud
            Class<? extends Node> nodeClass = nodeType.getNodeClass();
            Constructor<? extends Node> constructor = nodeClass.getDeclaredConstructor(int.class, String.class);

            // Instancier le nœud en utilisant le constructeur
            return constructor.newInstance(0, sqlite.getRandomNode(nodeType.getNodeClass().getSimpleName()));
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }*/

    protected static Node createNode(int id) {
        try {
            NodeModel nodeModel = sqlite.getNode(id);
            // Récupérer le constructeur correspondant au type de nœud
            Class<? extends Node> nodeClass = NodeType.valueOf(nodeModel.getType()).getNodeClass();
            Constructor<? extends Node> constructor = nodeClass.getDeclaredConstructor(int.class, String.class, List.class);

            // Instancier le nœud en utilisant le constructeur
            return constructor.newInstance(nodeModel.getId(), nodeModel.getDisplayed_text(), nodeModel.getChildren());
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public static Node createStartNode() {
        return createNode(0);
    }
}

