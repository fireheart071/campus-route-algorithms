package app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        // Top bar
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
        topBar.getChildren().addAll(new Label("Algorithm:"), algoCombo, new Label("Start:"), startCombo,
                new Label("End:"), endCombo, findRouteBtn);
        root.setTop(topBar);

        // Center graph pane with scrolling
        graphPane = new Pane();
        graphPane.setPrefSize(800, 600);
        ScrollPane scrollPane = new ScrollPane(graphPane);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        root.setCenter(scrollPane);

        // Bottom bar
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
    }

    private void findAndShowRoute() {
    }

    private void highlightRoute(List<String> path) {
    }

    public static void main(String[] args) {
        launch(args);
    }
}
