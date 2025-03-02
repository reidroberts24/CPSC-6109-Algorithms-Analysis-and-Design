import java.util.*;


/** A simple directed graph (digraph) that supports DFS-based topological sorting.
 * @author Reid Roberts 
 */
public class Digraph {
    
    //adjacency list: each vertex -> list of vertices it points to
    private Map<String, List<String>> adjacencyList;
    
    // track DFS color states: WHITE (unvisited), GRAY (in progress), BLACK (done)
    private Map<String, Color> color;
    
    private int vertexCount;
    private int edgeCount;
    private int time;  
    
    // stores discovery/finish times (optional)
    private Map<String, Integer> discoverTime;
    private Map<String, Integer> finishTime;

    // possible color states during DFS
    private enum Color {
        WHITE, GRAY, BLACK
    }
    
    // iInitialize an empty digraph
    public Digraph() {
        adjacencyList = new HashMap<>();
        color = new HashMap<>();
        discoverTime = new HashMap<>();
        finishTime = new HashMap<>();
        vertexCount = 0;
        edgeCount = 0;
        time = 0;
    }
    
    // add a vertex if it doesn't already exist
    public void addVertex(String label) {
        if (!adjacencyList.containsKey(label)) {
            adjacencyList.put(label, new ArrayList<>());
            color.put(label, Color.WHITE);
            discoverTime.put(label, 0);
            finishTime.put(label, 0);
            vertexCount++;
        }
    }
    
    // add a directed edge from 'from' to 'to'
    public void addEdge(String from, String to) {
        addVertex(from);
        addVertex(to);
        List<String> neighbors = adjacencyList.get(from);
        if (!neighbors.contains(to)) {
            neighbors.add(to);
            edgeCount++;
        }
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public int getEdgeCount() {
        return edgeCount;
    }

    
    // check the vertex info
    public void printVertexInfo(String vertex) {
        if (!adjacencyList.containsKey(vertex)) {
            System.out.println("Vertex \"" + vertex + "\" isn't in the graph.");
            return;
        }
        System.out.println("Vertex: " + vertex);
        System.out.println("Adjacency: " + adjacencyList.get(vertex));
    }
    

    // returns a topological ordering if no cycle; otherwise returns null
    public List<String> topologicalSort() {
        for (String v : adjacencyList.keySet()) {
            color.put(v, Color.WHITE);
        }
        time = 0;
        LinkedList<String> topoOrder = new LinkedList<>();
        for (String vertex : adjacencyList.keySet()) {
            if (color.get(vertex) == Color.WHITE) {
                if (!dfsVisit(vertex, topoOrder)) {
                    return null; // cycle found
                }
            }
        }
        return topoOrder;
    }

    // DFS helper: visits 'u' and returns false if a cycle is detected
    private boolean dfsVisit(String u, LinkedList<String> topoOrder) {
        color.put(u, Color.GRAY);
        time++;
        discoverTime.put(u, time);
        for (String v : adjacencyList.get(u)) {
            if (color.get(v) == Color.GRAY) {
                return false; // cycle
            }
            if (color.get(v) == Color.WHITE) {
                if (!dfsVisit(v, topoOrder)) {
                    return false; 
                }
            }
        }
        color.put(u, Color.BLACK);
        time++;
        finishTime.put(u, time);
        topoOrder.addFirst(u);
        return true;
    }
}
