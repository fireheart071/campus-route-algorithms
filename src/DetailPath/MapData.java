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

        return graph;
    }
}
