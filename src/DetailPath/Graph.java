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

    public void addEdge(String sourceName, String destName, double distance, double time) {
        adjacencyList.putIfAbsent(sourceName, new HashMap<>());
        adjacencyList.get(sourceName).put(destName, new Edge(distance, time));
    }

    public HashMap<String, Edge> getEdges(String current) {
        return adjacencyList.getOrDefault(current, new HashMap<>());
    }
}
