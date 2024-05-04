package representation;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

public class NodeFactory {
    private static final Random random = new Random();

    public static Node createRandomNode() {
        // Récupérer la liste de tous les types de nœuds
        NodeType[] nodeTypes = NodeType.values();

        // Choisir un type de nœud aléatoire
        NodeType randomNodeType = nodeTypes[random.nextInt(nodeTypes.length)];

        try {
            // Récupérer le constructeur correspondant au type de nœud
            Class<? extends Node> nodeClass = randomNodeType.getNodeClass();
            Constructor<? extends Node> constructor = nodeClass.getDeclaredConstructor();

            // Instancier le nœud en utilisant le constructeur
            return constructor.newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public static Node createStartNode() {
        try {
            // Récupérer le constructeur correspondant au type de nœud
            Class<? extends Node> nodeClass = NodeType.START_NODE.getNodeClass();
            Constructor<? extends Node> constructor = nodeClass.getDeclaredConstructor();

            // Instancier le nœud en utilisant le constructeur
            return constructor.newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}

