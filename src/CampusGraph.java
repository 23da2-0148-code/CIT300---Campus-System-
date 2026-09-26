import java.util.*;

/**
 * CampusGraph.java
 *
 * Models the university campus as an undirected graph using an
 * adjacency list. Each campus location is a vertex, and each road
 * or walking path between two locations is an edge.
 *
 * This class satisfies Requirements #7, #8, #9, #10, and #11 —
 * adding/removing locations, adding/removing connections between
 * them, and traversing the network using BFS and DFS.
 */
public class CampusGraph {

    // Maps each location name to the set of locations it's directly connected to
    private Map<String, LinkedHashSet<String>> adjList;

    /** Creates an empty campus graph with no locations yet. */
    public CampusGraph() {
        adjList = new LinkedHashMap<>();
    }

    /**
     * Adds a new campus location (vertex) to the graph.
     * Returns false if the location already exists, so duplicates
     * aren't accidentally created.
     */
    public boolean addLocation(String location) {
        if (adjList.containsKey(location)) return false;
        adjList.put(location, new LinkedHashSet<>());
        return true;
    }

    /**
     * Removes a location from the graph, along with any connections
     * other locations had to it (so no dangling references remain).
     * Returns false if the location doesn't exist.
     */
    public boolean removeLocation(String location) {
        if (!adjList.containsKey(location)) return false;
        adjList.remove(location);
        for (Set<String> neighbours : adjList.values()) {
            neighbours.remove(location); // clean up references from other locations
        }
        return true;
    }

    /**
     * Adds an undirected road/path between two locations, meaning
     * you can walk from either one to the other.
     */
    public boolean addConnection(String from, String to) {
        if (!adjList.containsKey(from) || !adjList.containsKey(to)) {
            return false; // one or both locations don't exist
        }
        adjList.get(from).add(to);
        adjList.get(to).add(from);
        return true;
    }

    /** Removes the road/path connecting two locations, if one exists. */
    public boolean removeConnection(String from, String to) {
        if (!adjList.containsKey(from) || !adjList.containsKey(to)) {
            return false;
        }
        adjList.get(from).remove(to);
        adjList.get(to).remove(from);
        return true;
    }

    /** Prints every location on campus along with its direct connections. */
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

    /**
     * Performs a Breadth-First Search starting from the given location,
     * visiting nearby locations first before moving further out —
     * useful for finding the shortest path in terms of number of stops.
     */
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

    /**
     * Performs a Depth-First Search starting from the given location,
     * fully exploring one path before backtracking to try another.
     */
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

    /** Helper method that recursively visits each unvisited neighbour. */
    private void dfsRec(String current, Set<String> visited) {
        visited.add(current);
        System.out.print(current + " ");
        for (String neighbour : adjList.get(current)) {
            if (!visited.contains(neighbour)) {
                dfsRec(neighbour, visited);
            }
        }
    }

    /** Returns true if the given location exists in the graph. */
    public boolean hasLocation(String location) {
        return adjList.containsKey(location);
    }

    /** Returns the total number of locations currently in the campus graph. */
    public int getLocationCount() {
        return adjList.size();
    }
}