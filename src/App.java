import DetailPath.Graph;
import DetailPath.MapData;
import DetailPath.Route;
import RouteAlgo.AStar;
import RouteAlgo.Dijkstra;
import RouteAlgo.FloydWarshall;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        Dijkstra dijkstra = new Dijkstra();
        AStar aStar = new AStar();
        FloydWarshall fW = new FloydWarshall();
        Graph UGgraph = MapData.loadGraph();
        fW.floydWarshallPath(UGgraph);

        Route route = dijkstra.dijkstraPath(UGgraph, "Gym", "Cafeteria");
        Route aStarRoute = aStar.AStarPath(UGgraph, "Gym", "Cafeteria");

        System.out.println("Dijkstra shortest path: " + route.getPath());
        System.out.println("Dijkstra shortest distance: " + route.getTotalDistance());
        System.out.println("Dijkstra total time: " + route.getTotalTime());
    }
}
