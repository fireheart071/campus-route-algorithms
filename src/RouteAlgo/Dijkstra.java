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

public class Dijkstra {

    public Route dijkstraPath(Graph graph, String source, String destination) {
        Map<String, Double> distances = new HashMap<>();
        Map<String, Double> times = new HashMap<>();
        Map<String, String> previous = new HashMap<>();
        PriorityQueue<String> queue = new PriorityQueue<>(Comparator.comparingDouble(distances::get));

        for (String node : graph.getNodes()) {
            distances.put(node, Double.MAX_VALUE);
            times.put(node, Double.MAX_VALUE);
            previous.put(node, null);
        }

        distances.put(source, 0.0);
        times.put(source, 0.0);
        queue.add(source);

        while (!queue.isEmpty()) {
            String current = queue.poll();

            if (current.equals(destination))
                break;

            for (Map.Entry<String, Edge> entry : graph.getEdges(current).entrySet()) {
                String neighbour = entry.getKey();
                Edge edge = entry.getValue();
                double newDist = distances.get(current) + edge.getDistance();
                double newTime = times.get(current) + edge.getTime();

                if (!distances.containsKey(neighbour)) {
                    distances.put(neighbour, (double) Integer.MAX_VALUE);
                }

                if (newDist < distances.get(neighbour)) {
                    distances.put(neighbour, newDist);
                    times.put(neighbour, newTime);
                    previous.put(neighbour, current);
                    queue.add(neighbour);
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
        return new Route(path, distances.get(destination), times.get(destination));
    }
}
