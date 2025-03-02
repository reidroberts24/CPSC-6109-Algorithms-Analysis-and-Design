import java.util.*;
import java.io.*;


/** prompts for a filename, builds Digraph, and outputs a topological order
 * @author Reid Roberts
 */

public class TopologicalSort {

    //uses default files if user presses Enter; otherwise uses the user’s filename
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println("Please enter the prereqs filename (or simply press return to run all of the test cases)");
        String filename = console.nextLine().trim();

        if (filename.isEmpty()) {
            String[] testFiles = { 
                "project2A.tab", 
                "project2B.tab",
                "project2C.tab",
                "project2E.tab"
            };
            for (String file : testFiles) {
                runSingleFile(file);
                System.out.println();
            }
        } else {
            runSingleFile(filename);
        }
    }
    

    //Reads the file, builds graph, prints vertex/edge info, then shows a valid order or cycle error.

    private static void runSingleFile(String filename) {
        File f = new File(filename);
        if (!f.exists()) {
            System.out.println("File not found: " + filename);
            return;
        }
        
        Digraph graph = new Digraph();
        
        //use try-catch block in case user enters a filename that doesn't exist or typo
        try (Scanner fileScan = new Scanner(f)) {
            while (fileScan.hasNextLine()) {
                String line = fileScan.nextLine().trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split("\\t");
                if (parts.length == 2) {
                    String first = parts[0];
                    String second = parts[1];
                    if (first.equalsIgnoreCase("NONE")) {
                        graph.addVertex(second);
                    } else {
                        graph.addEdge(first, second);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }
        
        System.out.println("Found " + graph.getVertexCount() + " vertices and " + graph.getEdgeCount() + " edges in " + filename);
        
        List<String> topoOrder = graph.topologicalSort();
        if (topoOrder == null) {
            System.out.println("Cycle detected. No valid topological ordering.");
        } else {
            System.out.println("Course order:");
            for (String course : topoOrder) {
                System.out.println(course);
            }
        }
    }
}
