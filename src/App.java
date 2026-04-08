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
        Route floydWarshallRoute = fW.floydWarshallPath("Gym", "Cafeteria");
        Route floydWarshallRoute2 = fW.floydWarshallPath("Cafeteria", "Parking Lot");

        System.out.println("Dijkstra shortest path: " + route.getPath());
        System.out.println("Dijkstra shortest distance: " + route.getTotalDistance());
        System.out.println("Dijkstra total time: " + route.getTotalTime());

        System.out.println(" ");

        System.out.println("A* path: " + aStarRoute.getPath());
        System.out.println("A* distance: " + aStarRoute.getTotalDistance());
        System.out.println("A* time: " + aStarRoute.getTotalTime());

        System.out.println(" ");

        System.out.println("Floyd-Warshall path: " + floydWarshallRoute.getPath());
        System.out.println("Floyd-Warshall distance: " + floydWarshallRoute.getTotalDistance());
        System.out.println("Floyd-Warshall time: " + floydWarshallRoute.getTotalTime());

        System.out.println("Floyd-Warshall path: " + floydWarshallRoute2.getPath());
        System.out.println("Floyd-Warshall distance: " + floydWarshallRoute2.getTotalDistance());
        System.out.println("Floyd-Warshall time: " + floydWarshallRoute2.getTotalTime());
    }
}
