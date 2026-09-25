import java.util.*;

/**
 * CampusGraph.java
 * Models the university campus as an undirected graph using an adjacency
 * list. Locations are vertices; roads/paths are edges.
 * Requirements #7, #8, #9, #10, #11.
 */
public class CampusGraph {

    // Adjacency list: location name -> set of connected location names
    private Map<String, LinkedHashSet<String>> adjList;

    public CampusGraph() {
        adjList = new LinkedHashMap<>();
    }

    /** Adds a new campus location (vertex). Returns false if it already exists. */
    public boolean addLocation(String location) {
        if (adjList.containsKey(location)) return false;
        adjList.put(location, new LinkedHashSet<>());
        return true;
    }

    /** Removes a location and any connections referencing it. Returns false if it doesn't exist. */
    public boolean removeLocation(String location) {
        if (!adjList.containsKey(location)) return false;
        adjList.remove(location);
        for (Set<String> neighbours : adjList.values()) {
            neighbours.remove(location);
        }
        return true;
    }

    /** Adds an undirected connection/road between two locations. */
    public boolean addConnection(String from, String to) {
        if (!adjList.containsKey(from) || !adjList.containsKey(to)) {
            return false; // one or both locations don't exist
        }
        adjList.get(from).add(to);
        adjList.get(to).add(from);
        return true;
    }

    /** Removes the connection/road between two locations. */
    public boolean removeConnection(String from, String to) {
        if (!adjList.containsKey(from) || !adjList.containsKey(to)) {
            return false;
        }
        adjList.get(from).remove(to);
        adjList.get(to).remove(from);
        return true;
    }

    /** Displays every location and its direct connections. */
    public void displayConnections() {
        if (adjList.isEmpty()) {
            System.out.println("No campus locations added yet.");
            return;
        }
        System.out.println("--- Campus Network ---");
        for (String location : adjList.keySet()) {
            System.out.println(location + " -> " + adjList.get(location));
        }
    }

    /** Breadth-First Search traversal starting from a given location. */
    public void bfs(String start) {
        if (!adjList.containsKey(start)) {
            System.out.println("Location not found: " + start);
            return;
        }
        Set<String> visited = new LinkedHashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(start);
        visited.add(start);

        System.out.print("BFS from " + start + ": ");
        while (!queue.isEmpty()) {
            String current = queue.poll();
            System.out.print(current + " ");
            for (String neighbour : adjList.get(current)) {
                if (!visited.contains(neighbour)) {
                    visited.add(neighbour);
                    queue.add(neighbour);
                }
            }
        }
        System.out.println();
    }

    /** Depth-First Search traversal starting from a given location. */
    public void dfs(String start) {
        if (!adjList.containsKey(start)) {
            System.out.println("Location not found: " + start);
            return;
        }
        Set<String> visited = new LinkedHashSet<>();
        System.out.print("DFS from " + start + ": ");
        dfsRec(start, visited);
        System.out.println();
    }

    private void dfsRec(String current, Set<String> visited) {
        visited.add(current);
        System.out.print(current + " ");
        for (String neighbour : adjList.get(current)) {
            if (!visited.contains(neighbour)) {
                dfsRec(neighbour, visited);
            }
        }
    }

    public boolean hasLocation(String location) {
        return adjList.containsKey(location);
    }
}
