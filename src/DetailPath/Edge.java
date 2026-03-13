package DetailPath;

public class Edge {
    private double distance;
    private double time;

    public Edge(double distance, double time) {
        this.distance = distance;
        this.time = time;
    }

    public double getTime() {
        return time;
    }

    public double getDistance() {
        return distance;
    }
}
