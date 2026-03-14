package DetailPath;

import java.util.HashMap;

public class Graph {
    private HashMap<String, HashMap<String, Edge>> adjacencyList;
    private HashMap<String, Node> nodes;

    public Graph() {
        adjacencyList = new HashMap<>();
        nodes = new HashMap<>();
    }

    public void addNode(String name, double latitude, double longitude) {
        nodes.put(name, new Node(name, latitude, longitude));
        adjacencyList.computeIfAbsent(name, k -> new HashMap<>());
    }
}
