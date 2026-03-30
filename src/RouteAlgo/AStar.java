package RouteAlgo;

import DetailPath.Graph;
import DetailPath.Route;

public class AStar {

    private double heuristic(double[] a, double[] b) {
        double dx = a[0] - b[0];
        double dy = a[1] - b[1];
        return Math.sqrt(dx * dx + dy * dy);
    }

    public Route AStarPath(Graph graph, String source, String destination) {
        return null;
    }
}
