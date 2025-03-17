//package Assignment_06_Shortest_Paths;
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
    
    private Map<String, Map<String, Integer>> weights;
    private Map<String, Integer> dist;
    private Map<String, String> pred;

    // iInitialize an empty digraph
    public Digraph() {
        adjacencyList = new HashMap<>();
        color = new HashMap<>();
        discoverTime = new HashMap<>();
        finishTime = new HashMap<>();
        vertexCount = 0;
        edgeCount = 0;
        time = 0;
        
        // For weighted edges (Project 3)
        weights = new HashMap<>();
        dist = new HashMap<>();
        pred = new HashMap<>();


    }
    
    // add a vertex if it doesn't already exist
    public void addVertex(String label) {
        if (!adjacencyList.containsKey(label)) {
            adjacencyList.put(label, new ArrayList<>());
            color.put(label, Color.WHITE);
            discoverTime.put(label, 0);
            finishTime.put(label, 0);
            vertexCount++;
    
            // For P3, also initialize an empty weights row:
            weights.put(label, new HashMap<>()); 
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
    
    /**
     * Overloaded addEdge for weighted edges. 
     */
    public void addEdge(String from, String to, int weight) {
        addVertex(from);
        addVertex(to);

        // add adjacency link if not already there
        List<String> neighbors = adjacencyList.get(from);
        if (!neighbors.contains(to)) {
            neighbors.add(to);
            edgeCount++;
        }

        // store the weight in the weights structure
        weights.get(from).put(to, weight);
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public int getEdgeCount() {
        return edgeCount;
    }

    // for retrieving dist/pred in your main:
    public Map<String, Integer> getDist() {
        return dist;
    }
    public Map<String, String> getPred() {
        return pred;
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

    public void ShortestPaths(String start) {
        // initialize all dist[v] = Infinity, pred[v] = null
        dist.clear();
        pred.clear();

        for (String v : adjacencyList.keySet()) {
            dist.put(v, Integer.MAX_VALUE);
            pred.put(v, null);
        }
        
        // distance to start from itself is 0
        dist.put(start, 0);
        // add all vertices to a min-priority queue based on dist[v]
        PriorityQueue<String> pq = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return dist.get(a).compareTo(dist.get(b));
            }
        });
        pq.addAll(adjacencyList.keySet());

        // repeatedly pop the vertex with the smallest dist
        while (!pq.isEmpty()) {
            String u = pq.poll();

            // if dist[u] is Infinity, the remainder are unreachable
            if (dist.get(u) == Integer.MAX_VALUE) {
                break;
            }
        
            // for each neighbor v of u, check if we can relax (u->v)
            for (String v : adjacencyList.get(u)) {
                // get the weight of u to v
                int w = weights.get(u).get(v);

                int alt = dist.get(u) + w;
                if (alt < dist.get(v)) {
                    dist.put(v, alt);
                    pred.put(v, u);

                    // decrease key in PriorityQueue by removing & re-adding
                    pq.remove(v);
                    pq.add(v);
                }
            }
        }
    }
}
