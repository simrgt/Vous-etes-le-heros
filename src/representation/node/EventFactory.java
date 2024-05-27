package representation.node;

import db.NodeModel;
import representation.Event;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

/**
 * Factory for creating nodes.
 */
public class EventFactory {

    /**
     * @param id id of the node
     * @return the node corresponding to the id
     */
    protected static Event createNode(int id) {
        try {
            NodeModel nodeModel = NodeModel.getNode(id);
            // Récupérer le constructeur correspondant au type de nœud
            Constructor<? extends Node> constructor = NodeType.valueOf(nodeModel.getType()).getNodeConstructor();

            // Instancier le nœud en utilisant le constructeur
            Event node = constructor.newInstance(nodeModel.getId(), nodeModel.getDisplayed_text(), nodeModel.getChildren());
            if (nodeModel.getSound_path() != null) {
                node = new SoundNode(node, nodeModel.getSound_path());
            }
            return node;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return the start node
     */
    public static Event createStartNode() {
        return createNode(0);
    }
}
