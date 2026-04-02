package RouteAlgo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import DetailPath.Edge;
import DetailPath.Graph;
import DetailPath.Route;

public class AStar {

    private double heuristic(double[] a, double[] b) {
        double dx = a[0] - b[0];
        double dy = a[1] - b[1];
        return Math.sqrt(dx * dx + dy * dy);
    }

    public Route AStarPath(Graph graph, String source, String destination) {
        Map<String, Double> gScore = new HashMap<>();
        Map<String, Double> timeScore = new HashMap<>();
        Map<String, Double> fScore = new HashMap<>();
        Map<String, String> previous = new HashMap<>();

        for (String node : graph.getNodes()) {
            gScore.put(node, Double.MAX_VALUE);
            timeScore.put(node, Double.MAX_VALUE);
            fScore.put(node, Double.MAX_VALUE);
        }
        double[] startCoords = graph.getNode(source).getCoordinates();
        double[] goalCoords = graph.getNode(destination).getCoordinates();
        gScore.put(source, 0.0);
        timeScore.put(source, 0.0);
        fScore.put(source, heuristic(startCoords, goalCoords));

        PriorityQueue<String> openSet = new PriorityQueue<>(
                Comparator.comparingDouble(node -> fScore.getOrDefault(node, Double.POSITIVE_INFINITY)));
        openSet.add(source);

        while (!openSet.isEmpty()) {
            String current = openSet.poll();

            if (current.equals(destination))
                break;

            for (Map.Entry<String, Edge> entry : graph.getEdges(current).entrySet()) {
                String neighbour = entry.getKey();
                double tentative = gScore.get(current) + entry.getValue().getDistance();
                double timeTentative = timeScore.get(current) + entry.getValue().getTime();

                if (tentative < gScore.getOrDefault(neighbour, Double.POSITIVE_INFINITY)) {
                    previous.put(neighbour, current);
                    gScore.put(neighbour, tentative);
                    timeScore.put(neighbour, timeTentative);
                    fScore.put(neighbour, tentative + heuristic(graph.getNode(neighbour).getCoordinates(), goalCoords));
                    openSet.add(neighbour);
                }
            }
        }

        List<String> path = new ArrayList<>();
        String step = destination;

        while (step != null) {
            path.add(step);
            step = previous.get(step);
        }

        Collections.reverse(path);
        return new Route(path, gScore.get(destination), timeScore.get(destination));
    }
}
