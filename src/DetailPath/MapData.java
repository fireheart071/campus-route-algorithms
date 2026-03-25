package DetailPath;

public class MapData {
    // populate graph data with University campus locations
    public static Graph loadGraph() {
        Graph graph = new Graph();

        graph.addNode("Library", 3, 5);
        graph.addNode("Cafeteria", 10, 20);
        graph.addNode("Lecture Hall", 15, 25);
        graph.addNode("Gym", 20, 30);
        graph.addNode("Parking Lot", 5, 10);
        graph.addNode("Dormitory", 25, 35);
        graph.addNode("Bookstore", 14, 24);
        graph.addNode("Admin Office", 8, 18);
        graph.addNode("Science Lab", 18, 28);

        // Now add edges using names + distance + time
        graph.addEdge("Library", "Cafeteria", 0.1, 2);
        graph.addEdge("Library", "Lecture Hall", 0.2, 5);
        graph.addEdge("Cafeteria", "Gym", 0.3, 7);
        graph.addEdge("Lecture Hall", "Gym", 0.4, 10);

        graph.addEdge("Library", "Parking Lot", 0.5, 12);
        graph.addEdge("Parking Lot", "Cafeteria", 0.2, 5);
        graph.addEdge("Gym", "Parking Lot", 0.3, 8);

        graph.addEdge("Dormitory", "Library", 0.6, 15);
        graph.addEdge("Dormitory", "Cafeteria", 0.3, 6);
        graph.addEdge("Bookstore", "Lecture Hall", 0.2, 4);
        graph.addEdge("Bookstore", "Library", 0.1, 2);

        return graph;
    }
}
