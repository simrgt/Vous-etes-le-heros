package representation.node;

import db.NodeModel;
import exception.CreateNodeException;
import representation.Event;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

/**
 * Factory for creating nodes.
 */
public class EventFactory {

    /**
     * @param id id of the node
     * @return the node corresponding to the id
     */
    public static Event createNode(int id) {
        try {
            NodeModel nodeModel = NodeModel.getNode(id);
            // Récupérer le constructeur correspondant au type de nœud
            Constructor<? extends Node> constructor = NodeType.valueOf(nodeModel.getType()).getNodeConstructor();

            // Instancier le nœud en utilisant le constructeur
            Event node = constructor.newInstance(nodeModel.getId(), nodeModel.getDisplayed_text(), nodeModel.getChildren(), nodeModel.getValue(), nodeModel.getAttribute());
            if (nodeModel.getSound_path() != null) {
                node = new SoundNode(node, nodeModel.getSound_path());
            }
            return node;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | SQLException e) {
            System.out.println(e.getMessage());
            throw new CreateNodeException("Error while creating node", e);
        }
    }

    /**
     * @return the start node
     */
    public static Event createStartNode() throws CreateNodeException {
        return createNode(0);
    }
}
