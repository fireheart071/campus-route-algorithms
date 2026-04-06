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
    }
}
