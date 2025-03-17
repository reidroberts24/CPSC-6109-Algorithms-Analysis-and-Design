//package Assignment_06_Shortest_Paths;
import java.util.*;
import java.io.*;

public class ShortestPaths {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        // prompt user for test inpput
        System.out.println("Please enter the input filename (or simply press return to use ./project3-testA.txt):");
        String input = console.nextLine().trim();
        String filename = input.isEmpty() ? "./project3-testA.txt" : input;
        System.out.println("Importing vertices, edges (and their weights) from " + filename + " . . .");
        System.out.println();
        
        // build the graph from that file
        Digraph graph = new Digraph();
        String firstVertex = null;

        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("File not found: " + filename);
            return;
        }

        // parse the file
        try (Scanner fileScan = new Scanner(file)) {
            while (fileScan.hasNextLine()) {
                String line = fileScan.nextLine().trim();
                if (line.isEmpty()) {
                    // skip blank lines
                    continue;
                }
                // split on TAB
                String[] parts = line.split("\\t");
                // should have 3 parts: from, to, and weight
                if (parts.length == 3) {
                    String from = parts[0];
                    String to = parts[1];
                    int weight = Integer.parseInt(parts[2]);

                    // add edge to the Digraph
                    graph.addEdge(from, to, weight);

                    // save the first 'from' as our "start" vertex
                    if (firstVertex == null) {
                        firstVertex = from;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }

        // handle if the file contained no edges or was malformed
        if (firstVertex == null) {
            System.out.println("No valid edges found in " + filename);
            return;
        }

        System.out.println("Found " + graph.getVertexCount() + " vertices and " + graph.getEdgeCount() + " edges in " + filename);

        // run Dijkstra's algo from the first vertex
        graph.ShortestPaths(firstVertex);

        // get the final dist/pred maps
        Map<String, Integer> dist = graph.getDist();
        Map<String, String> pred = graph.getPred();

        System.out.println("The costs for the shortest path from the first vertex (" + firstVertex + "):\n");
        System.out.println("Vertex\tDist.\tPath");
        System.out.println("------\t-----\t----");

        List<String> allVertices = new ArrayList<>(dist.keySet());
        

        // print each vertex's distance
        for (String v : allVertices) {
            if (v.equals(firstVertex)) {
                // skip printing the source row 
                continue;
            }
            int d = dist.get(v);
            if (d == Integer.MAX_VALUE) {
                // unreachable => Infinity
                System.out.println(v + "\tInfinity");
            } else {
                // reconstruct the path by following predecessors from v -> ... -> source
                List<String> pathChain = new ArrayList<>();
                String cur = v;
                while (cur != null) {
                    pathChain.add(cur);
                    cur = pred.get(cur);
                }
                if (!pathChain.isEmpty()) {
                    pathChain.remove(0);
                }
                
                String pathStr = String.join(", ", pathChain);
                System.out.println(v + "\t" + d + "\t" + pathStr);
            }
        }
    }
}
