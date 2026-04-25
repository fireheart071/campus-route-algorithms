package app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DetailPath.Edge;
import DetailPath.Graph;
import DetailPath.MapData;
import DetailPath.Route;
import RouteAlgo.AStar;
import RouteAlgo.Dijkstra;
import RouteAlgo.FloydWarshall;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MapRouteAppFX extends Application {
    private Graph graph;
    private Pane graphPane;
    private ComboBox<String> startCombo;
    private ComboBox<String> endCombo;
    private ComboBox<String> algoCombo;
    private Label routeSummary;
    private List<javafx.scene.Node> edgeNodes = new ArrayList<>();
    private List<javafx.scene.Node> nodeNodes = new ArrayList<>();
    private Map<String, javafx.scene.shape.Rectangle> nodeCircles = new HashMap<>();

    @Override
    public void start(Stage primaryStage) {
        graph = MapData.loadGraph();
        BorderPane root = new BorderPane();

        HBox topBar = new HBox(10);
        topBar.setPadding(new Insets(10));
        algoCombo = new ComboBox<>();
        algoCombo.getItems().addAll("Dijkstra", "A*", "Floyd-Warshall");
        algoCombo.setValue("Dijkstra");
        startCombo = new ComboBox<>();
        endCombo = new ComboBox<>();
        startCombo.getItems().addAll(graph.getNodes());
        endCombo.getItems().addAll(graph.getNodes());
        Button findRouteBtn = new Button("Find Route");
        findRouteBtn.setOnAction(_ -> findAndShowRoute());
        topBar.getChildren().addAll(new Label("Algorithm:"), algoCombo, new Label("Start:"), startCombo,
                new Label("End:"), endCombo, findRouteBtn);
        root.setTop(topBar);

        graphPane = new Pane();
        graphPane.setPrefSize(800, 600);
        drawGraph();
        ScrollPane scrollPane = new ScrollPane(graphPane);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        root.setCenter(scrollPane);

        routeSummary = new Label("Select start/end and click Find Route.");
        HBox bottomBar = new HBox(routeSummary);
        bottomBar.setPadding(new Insets(10));
        root.setBottom(bottomBar);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Campus Route Finder");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void drawGraph() {
        graphPane.getChildren().clear();
        edgeNodes.clear();
        nodeNodes.clear();
        nodeCircles.clear();
        double scale = 20;
        for (String node : graph.getNodes()) {
            double[] coords = graph.getNode(node).getCoordinates();
            double x = coords[0] * scale + 50;
            double y = coords[1] * scale + 50;
            javafx.scene.shape.Rectangle building = new javafx.scene.shape.Rectangle(x - 18, y - 18, 36, 36);
            building.setArcWidth(8);
            building.setArcHeight(8);
            building.setFill(Color.BEIGE);
            building.setStroke(Color.SADDLEBROWN);
            building.setStrokeWidth(2);
            building.setUserData(node);
            javafx.scene.shape.Polygon roof = new javafx.scene.shape.Polygon(
                    x - 22, y - 18,
                    x, y - 32,
                    x + 22, y - 18);
            roof.setFill(Color.DARKRED);
            roof.setStroke(Color.SADDLEBROWN);
            roof.setStrokeWidth(2);
            javafx.scene.shape.Rectangle win1 = new javafx.scene.shape.Rectangle(x - 12, y - 8, 8, 12);
            win1.setFill(Color.LIGHTSKYBLUE);
            win1.setStroke(Color.DARKGRAY);
            win1.setStrokeWidth(1);
            javafx.scene.shape.Rectangle win2 = new javafx.scene.shape.Rectangle(x + 4, y - 8, 8, 12);
            win2.setFill(Color.LIGHTSKYBLUE);
            win2.setStroke(Color.DARKGRAY);
            win2.setStrokeWidth(1);
            javafx.scene.shape.Rectangle door = new javafx.scene.shape.Rectangle(x - 2, y + 8, 8, 10);
            door.setFill(Color.SADDLEBROWN);
            door.setStroke(Color.DARKGRAY);
            door.setStrokeWidth(1);
            building.setOnMouseEntered(_ -> showNodeDetails(node, x, y));
            building.setOnMouseExited(_ -> hideNodeDetails());
            roof.setOnMouseEntered(_ -> showNodeDetails(node, x, y));
            roof.setOnMouseExited(_ -> hideNodeDetails());
            win1.setOnMouseEntered(_ -> showNodeDetails(node, x, y));
            win1.setOnMouseExited(_ -> hideNodeDetails());
            win2.setOnMouseEntered(_ -> showNodeDetails(node, x, y));
            win2.setOnMouseExited(_ -> hideNodeDetails());
            door.setOnMouseEntered(_ -> showNodeDetails(node, x, y));
            door.setOnMouseExited(_ -> hideNodeDetails());
            nodeCircles.put(node, building);
            graphPane.getChildren().addAll(roof, building, win1, win2, door);
            nodeNodes.add(roof);
            nodeNodes.add(building);
            nodeNodes.add(win1);
            nodeNodes.add(win2);
            nodeNodes.add(door);
            Text label = new Text(x - 20, y - 32, node);
            graphPane.getChildren().add(label);
            nodeNodes.add(label);
        }
        for (String src : graph.getNodes()) {
            Map<String, Edge> edges = graph.getEdges(src);
            double[] srcCoords = graph.getNode(src).getCoordinates();
            double x1 = srcCoords[0] * scale + 50;
            double y1 = srcCoords[1] * scale + 50;
            for (String dst : edges.keySet()) {
                double[] dstCoords = graph.getNode(dst).getCoordinates();
                double x2 = dstCoords[0] * scale + 50;
                double y2 = dstCoords[1] * scale + 50;
                Line line = new Line(x1, y1, x2, y2);
                line.setStroke(Color.GRAY);
                line.setStrokeWidth(2);
                graphPane.getChildren().add(line);
                edgeNodes.add(line);
                double mx = (x1 + x2) / 2;
                double my = (y1 + y2) / 2;
                String info = String.format("%.1fm, %.0fmin", edges.get(dst).getDistance() * 1000,
                        edges.get(dst).getTime());
                Text edgeLabel = new Text(mx, my, info);
                edgeLabel.setFill(Color.DARKGRAY);
                graphPane.getChildren().add(edgeLabel);
                edgeNodes.add(edgeLabel);
            }
        }
    }

    private void findAndShowRoute() {
        String start = startCombo.getValue();
        String end = endCombo.getValue();
        if (start == null || end == null || start.equals(end)) {
            routeSummary.setText("Please select different start and end nodes.");
            return;
        }
        String algo = algoCombo.getValue();
        Route route = null;
        if ("Dijkstra".equals(algo)) {
            route = new Dijkstra().dijkstraPath(graph, start, end);
        } else if ("A*".equals(algo)) {
            route = new AStar().AStarPath(graph, start, end);
        } else if ("Floyd-Warshall".equals(algo)) {
            FloydWarshall fw = new FloydWarshall();
            fw.floydWarshallPath(graph);
            route = fw.floydWarshallPath(start, end);
        }
        if (route == null || route.getPath().size() < 2) {
            routeSummary.setText("No route found.");
            highlightRoute(null);
            return;
        }
        routeSummary.setText(String.format("Shortest Path: %s | Distance: %.1fm | Time: %.0f min",
                String.join(" \u2192 ", route.getPath()), route.getTotalDistance() * 1000, route.getTotalTime()));
        highlightRoute(route.getPath());
    }

    private void highlightRoute(List<String> path) {
        drawGraph();
        if (path == null || path.size() < 2)
            return;
        double scale = 20;
        for (int i = 0; i < path.size() - 1; i++) {
            String src = path.get(i);
            String dst = path.get(i + 1);
            double[] srcCoords = graph.getNode(src).getCoordinates();
            double[] dstCoords = graph.getNode(dst).getCoordinates();
            double x1 = srcCoords[0] * scale + 50;
            double y1 = srcCoords[1] * scale + 50;
            double x2 = dstCoords[0] * scale + 50;
            double y2 = dstCoords[1] * scale + 50;
            Line line = new Line(x1, y1, x2, y2);
            line.setStroke(Color.RED);
            line.setStrokeWidth(5);
            graphPane.getChildren().add(line);
        }
        for (String node : path) {
            javafx.scene.shape.Rectangle c = nodeCircles.get(node);
            if (c != null) {
                c.setFill(Color.LIMEGREEN);
                c.setStroke(Color.DARKGREEN);
                c.setStrokeWidth(3);
            }
        }
    }

    private Text nodeDetailText = null;

    private void showNodeDetails(String node, double x, double y) {
        if (nodeDetailText != null)
            graphPane.getChildren().remove(nodeDetailText);
        DetailPath.Node n = graph.getNode(node);
        nodeDetailText = new Text(x + 20, y,
                String.format("%s\n(%.2f, %.2f)", node, n.getCoordinates()[0], n.getCoordinates()[1]));
        nodeDetailText.setFill(Color.DARKBLUE);
        nodeDetailText.setStyle("-fx-font-weight: bold; -fx-background-color: white;");
        graphPane.getChildren().add(nodeDetailText);
    }

    private void hideNodeDetails() {
        if (nodeDetailText != null)
            graphPane.getChildren().remove(nodeDetailText);
        nodeDetailText = null;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
