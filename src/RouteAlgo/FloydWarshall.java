package RouteAlgo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DetailPath.Edge;
import DetailPath.Graph;
import DetailPath.Route;

public class FloydWarshall {
    private List<String> nodes;
    private Map<String, Integer> indexMap;
    private double[][] distance;
    private double[][] time;
    private int[][] next;

    public void floydWarshallPath(Graph graph) {
        nodes = new ArrayList<>(graph.getNodes());
        int n = nodes.size();

        indexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            indexMap.put(nodes.get(i), i);
        }

        distance = new double[n][n];
        time = new double[n][n];
        next = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    distance[i][j] = 0;
                    time[i][j] = 0;
                } else {
                    distance[i][j] = Double.MAX_VALUE;
                    time[i][j] = Double.MAX_VALUE;
                }
                next[i][j] = -1;
            }
        }

        for (String node : nodes) {
            Map<String, Edge> edges = graph.getEdges(node);
            if (edges != null) {
                for (Map.Entry<String, Edge> edgeEntry : edges.entrySet()) {
                    String dest = edgeEntry.getKey();
                    double dist = edgeEntry.getValue().getDistance();
                    distance[indexMap.get(node)][indexMap.get(dest)] = dist;
                    time[indexMap.get(node)][indexMap.get(dest)] = edgeEntry.getValue().getTime();
                    next[indexMap.get(node)][indexMap.get(dest)] = indexMap.get(dest);
                }
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (distance[i][k] + distance[k][j] < distance[i][j]) {
                        distance[i][j] = distance[i][k] + distance[k][j];
                        time[i][j] = time[i][k] + time[k][j];
                        next[i][j] = next[i][k];
                    }
                }
            }
        }
    }

    public Route floydWarshallPath(String source, String destination) {
        if (indexMap == null || distance == null) {
            throw new IllegalStateException("Run floydWarshallPath(Graph) first!");
        }

        int src = indexMap.get(source), dst = indexMap.get(destination);
        if (next[src][dst] == -1)
            return null;

        List<String> path = new ArrayList<>();
        int u = src;
        while (u != dst) {
            path.add(nodes.get(u));
            u = next[u][dst];
        }
        path.add(nodes.get(dst));

        return new Route(path, distance[src][dst], time[src][dst]);
    }
}
