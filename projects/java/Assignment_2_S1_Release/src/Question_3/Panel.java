package Question_3;

import java.awt.*;
import javax.swing.*;
import java.util.List;
import java.util.ArrayList;

public class Panel extends JPanel {

    public Node[] nodes;
    public List<Node> highlightedNodes;
    private String printedpathway;
    
    public Panel() {
        setBackground(Color.WHITE);
        highlightedNodes = new ArrayList<>();
        printedpathway = "";
    }

    public void loadMaze(String filePath) {
        FileManager fileManager = new FileManager(filePath);
        fileManager.readFile(filePath);
        String[] lines = fileManager.lineData;
        nodes = new Node[lines.length - 1]; // Exclude the header line
        for (int i = 1; i < lines.length; i++) {
            String[] parts = lines[i].split(",");
            String name = parts[0];
            int x = Integer.parseInt(parts[1]);
            int y = Integer.parseInt(parts[2]);
            String next1 = parts[3];
            String next2 = parts[4];
            nodes[i - 1] = new Node(name, x, y, next1, next2);

            System.out.println("Node loaded: " + nodes[i - 1].getName() + " at (" + x + ", " + y + ")");
        }

        // Find start and end nodes
        Node startNode = findStartNode();
        Node endNode = findEndNode();

        Path path = new Path(startNode, endNode, this);
        path.buildPathTree(startNode);

        // Create a new Maze instance and start traversing
        Maze maze = new Maze(this, startNode, endNode);
        maze.startMazeTraversal();
    }

    public void highlightNode(Node node) {
        highlightedNodes.add(node);
        repaint();
    }

    public void unhighlightNode(Node node) {
         // Prevent unhighlighting if exit is reached
            highlightedNodes.remove(node);
            repaint();
        
    }

    public void highlightPath(List<Node> path) {
        
        highlightedNodes.addAll(path);
        repaint();
    }
    
    public void printingPath(String a)
    {
        printedpathway = a;
        repaint();
    }

@Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (nodes != null) {
        for (Node node : nodes) {
            // Draw the nodes and paths
            g.drawRect(node.getX() * 100, node.getY() * 100, 20, 20);
            g.drawString(node.getName(), node.getX() * 100 + 25, node.getY() * 100 + 25);

            // Highlight the current node if it's in the highlighted list
            if (highlightedNodes.contains(node)) {
                g.setColor(Color.MAGENTA);
                g.fillOval(node.getX() * 100, node.getY() * 100, 20, 20);
                

                // Highlight connection lines to other highlighted nodes
                g.setColor(Color.GREEN); // Set the color for highlighted connections

                for(int i = 0; i < highlightedNodes.size(); i++) {
                       Node nowNode = highlightedNodes.get(i);
                        if (highlightedNodes.contains(getNode(nowNode.getNext1()))) {
                            g.drawLine(nowNode.getX() * 100 + 10, nowNode.getY() * 100 + 10,
                                    getNode(nowNode.getNext1()).getX() * 100 + 10, getNode(nowNode.getNext1()).getY() * 100 + 10);
                        } else if (highlightedNodes.contains(getNode(nowNode.getNext2())))  {
                            g.drawLine(nowNode.getX() * 100 + 10, nowNode.getY() * 100 + 10,
                                    getNode(nowNode.getNext2()).getX() * 100 + 10, getNode(nowNode.getNext2()).getY() * 100 + 10);
                        } else if(nowNode.getNext1().equals("W") || nowNode.getNext2().equals("W"))
                        {
                            g.drawLine(nowNode.getX() * 100 + 10, nowNode.getY() * 100 + 10,
                                    getNode("EXIT").getX() * 100 + 10, getNode("EXIT").getY() * 100 + 10);
                        }
                    
                }
            }

            // Draw paths to next nodes
            drawPaths(g, node);
        }
    }
    // Draw the pathway string at the bottom of the panel
    
        g.setColor(Color.BLACK);
        g.drawString("PATH: " + printedpathway, 400, getHeight() - 10);
        
}


    private void drawPaths(Graphics g, Node node) {
        if (!node.getNext1().equals("A")) {
            if (node.getNext1().equals("W")) {
                // Draw a line to the exit
                g.setColor(Color.BLUE);
                g.drawLine(node.getX() * 100 + 10, node.getY() * 100 + 10,
                        getNode("EXIT").getX() * 100 + 10, getNode("EXIT").getY() * 100 + 10);
            } else {
                Node nextNode1 = getNode(node.getNext1());
                if (nextNode1 != null) {
                    g.setColor(Color.RED);
                    g.drawLine(node.getX() * 100 + 10, node.getY() * 100 + 10,
                            nextNode1.getX() * 100 + 10, nextNode1.getY() * 100 + 10);
                }
            }
        }

        if (!node.getNext2().equals("A")) {
            if (node.getNext2().equals("W")) {
                // Draw a line to the exit
                g.setColor(Color.BLUE);
                g.drawLine(node.getX() * 100 + 10, node.getY() * 100 + 10,
                        getNode("EXIT").getX() * 100 + 10, getNode("EXIT").getY() * 100 + 10);
            } else {
                Node nextNode2 = getNode(node.getNext2());
                if (nextNode2 != null) {
                    g.setColor(Color.BLUE);
                    g.drawLine(node.getX() * 100 + 10, node.getY() * 100 + 10,
                            nextNode2.getX() * 100 + 10, nextNode2.getY() * 100 + 10);
                }
            }
        }
    }

    public Node getNode(String name) {
        for (Node node : nodes) {
            if (node.getName().equals(name)) {
                return node;
            }
        }
        return null;
    }

    public Node findStartNode() {
        for (Node node : nodes) {
            if (node.getName().equals("START")) {
                return node;
            }
        }
        return null;
    }

    public Node findEndNode() {
        for (Node node : nodes) {
            if (node.getName().equals("EXIT")) {
                return node;
            }
        }
        return null;
    }
}
