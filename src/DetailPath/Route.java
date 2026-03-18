package DetailPath;

import java.util.List;

public class Route {
    private List<String> path;
    private double totalDistance;
    private double totalTime;

    public Route(List<String> path, double totalDistance, double totalTime) {
        this.path = path;
        this.totalDistance = totalDistance;
        this.totalTime = totalTime;
    }

    public List<String> getPath() {
        return path;
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    public double getTotalTime() {
        return totalTime;
    }
}
