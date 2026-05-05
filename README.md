# Campus Route Finder

A Java application that implements and visualizes shortest-path algorithms on a university campus graph.

## Features

- **Three pathfinding algorithms**: Dijkstra, A\* (Euclidean heuristic), and Floyd-Warshall (all-pairs)
- **Console mode**: Run all algorithms from the terminal
- **JavaFX GUI**: Interactive visual map with building shapes, edge labels, and route highlighting
- **Hover tooltips**: View node coordinates on mouse hover

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
