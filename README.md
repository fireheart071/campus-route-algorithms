# Campus Route Finder

A Java application that implements and visualizes shortest-path algorithms on a university campus graph.

## Features

- **Three pathfinding algorithms**: Dijkstra, A\* (Euclidean heuristic), and Floyd-Warshall (all-pairs)
- **Console mode**: Run all algorithms from the terminal
- **JavaFX GUI**: Interactive visual map with building shapes, edge labels, and route highlighting
- **Hover tooltips**: View node coordinates on mouse hover

## Algorithms

| Algorithm | Approach | Use Case |
|-----------|----------|----------|
| **Dijkstra** | Greedy BFS with priority queue | Single-source shortest path |
| **A\*** | Dijkstra + Euclidean heuristic | Faster single-source with goal direction |
| **Floyd-Warshall** | Dynamic programming (O(n³)) | All-pairs shortest paths (precomputed) |

## Getting Started

### Console
```sh
javac -d bin src/DetailPath/*.java src/RouteAlgo/*.java src/App.java
java -cp bin App
```

### JavaFX GUI
```sh
javac -d bin src/DetailPath/*.java src/RouteAlgo/*.java src/app/MapRouteAppFX.java
java -cp bin app.MapRouteAppFX
```

## Campus Locations

| Location | Coordinates |
|----------|------------|
| Library | (3, 5) |
| Cafeteria | (10, 20) |
| Lecture Hall | (15, 25) |
| Gym | (20, 30) |
| Parking Lot | (5, 10) |
| Dormitory | (25, 35) |
| Bookstore | (14, 24) |
| Admin Office | (8, 18) |
| Science Lab | (18, 28) |

## Folder Structure

```
.
├── src/
│   ├── App.java                    # Console entry point
│   ├── app/
│   │   └── MapRouteAppFX.java      # JavaFX GUI application
│   ├── DetailPath/
│   │   ├── Edge.java               # Edge with distance and time
│   │   ├── Graph.java              # Graph with adjacency list
│   │   ├── MapData.java            # University campus graph data
│   │   ├── Node.java               # Node with coordinates
│   │   └── Route.java              # Route result with path and metrics
│   └── RouteAlgo/
│       ├── AStar.java              # A* algorithm
│       ├── Dijkstra.java           # Dijkstra's algorithm
│       └── FloydWarshall.java      # Floyd-Warshall algorithm
├── lib/                            # Dependencies
└── README.md
```
