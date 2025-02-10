package Question_3;

import java.util.ArrayList;
import java.util.List;

public class Maze extends Thread {

    private Panel panel;
    private Node startNode;
    private Node endNode;
    public List<Node> path;
    public String pathway;
    public String highlightway; 
    private volatile boolean exitReached; // Ensure the flag is volatile for thread safety

    public Maze() {
    }

    public Maze(Panel panel, Node startNode, Node endNode) {
        this.panel = panel;
        this.startNode = startNode;
        this.endNode = endNode;
        this.path = new ArrayList<>();
        this.exitReached = false;
        this.pathway = "";
        this.highlightway = ""; 
    
    }

    public void startMazeTraversal() {
        start();
    }

    @Override
    public void run() {
        traverseMaze(startNode);
        // Ensure the path is highlighted after traversal
        highlightPath();
        printingPath();
        
    }

    private void traverseMaze(Node currentNode) {
    if (currentNode == null || currentNode.visited || exitReached) {
        return;
    }

    currentNode.visited = true;

    // Print debug statement to track the current node being visited
    System.out.println("Visiting node: " + currentNode.getName());

    path.add(currentNode);
    panel.highlightNode(currentNode);
    panel.repaint();
    
        if (currentNode == endNode) {
            
        for(int i = 0; i < path.size(); i++)
        {
            pathway += path.get(i).getName() + " ";
        }
        for(int i = 0; i < panel.highlightedNodes.size(); i++)
        {
            highlightway += panel.highlightedNodes.get(i).getName() + " ";
        }
        
        System.out.println(pathway);
        System.out.println(highlightway);
        
        highlightPath();
            exitReached = true; // Set the flag when the exit node is reached
            System.out.println("Exit node reached!");
            return;
        }
    try {
        Thread.sleep(300); // Adjust the delay as needed for animation speed
    } catch (InterruptedException ex) {
        ex.printStackTrace();
    }

    // Traverse to the next nodes recursively
    
        if (currentNode.left != null) {
            traverseMaze(currentNode.left);
        }
        
        if (currentNode.right != null) {
            traverseMaze(currentNode.right);
        }
    

    // If none of the paths from the current node lead to the end, backtrack
    
        path.remove(currentNode);
        currentNode.visited = false;
        panel.unhighlightNode(currentNode); // Unhighlight the node only if exit is not reached
        panel.repaint();
    
}
    public void highlightPath() {
        // Highlight the path from start to exit on the panel
        panel.highlightPath(this.path);
    }
    
    public void printingPath()
    {
        panel.printingPath(this.highlightway);
    }
}
