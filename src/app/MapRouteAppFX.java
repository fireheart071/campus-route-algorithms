package app;

import DetailPath.Graph;
import DetailPath.MapData;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MapRouteAppFX extends Application {
    private Graph graph;

    @Override
    public void start(Stage primaryStage) {
        graph = MapData.loadGraph();
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Campus Route Finder");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
