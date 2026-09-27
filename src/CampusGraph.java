import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class CampusGraph {

    private HashMap<String, ArrayList<String>> adjList;

    public CampusGraph() {
        adjList = new HashMap<>();
    }

    public boolean addLocation(String location) {
        if (adjList.containsKey(location)) {
            return false;
        }
        adjList.put(location, new ArrayList<>());
        return true;
    }

    public boolean removeLocation(String location) {
        if (!adjList.containsKey(location)) {
            return false;
        }
        adjList.remove(location);
        for (ArrayList<String> neighbours : adjList.values()) {
            neighbours.remove(location);
        }
        return true;
    }

    public boolean addConnection(String from, String to) {
        if (!adjList.containsKey(from) || !adjList.containsKey(to)) {
            return false;
        }
        if (!adjList.get(from).contains(to)) {
            adjList.get(from).add(to);
        }
        if (!adjList.get(to).contains(from)) {
            adjList.get(to).add(from);
        }
        return true;
    }

    public boolean removeConnection(String from, String to) {
        if (!adjList.containsKey(from) || !adjList.containsKey(to)) {
            return false;
        }
        adjList.get(from).remove(to);
        adjList.get(to).remove(from);
        return true;
    }

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

    public void bfs(String start) {
        if (!adjList.containsKey(start)) {
            System.out.println("Location not found: " + start);
            return;
        }

        ArrayList<String> visited = new ArrayList<>();
        LinkedList<String> queue = new LinkedList<>();
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

    public void dfs(String start) {
        if (!adjList.containsKey(start)) {
            System.out.println("Location not found: " + start);
            return;
        }

        ArrayList<String> visited = new ArrayList<>();
        System.out.print("DFS from " + start + ": ");
        dfsRec(start, visited);
        System.out.println();
    }

    private void dfsRec(String current, ArrayList<String> visited) {
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

    public int getLocationCount() {
        return adjList.size();
    }
}
