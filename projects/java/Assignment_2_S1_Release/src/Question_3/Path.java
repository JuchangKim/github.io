package Question_3;

import java.util.HashSet;

public class Path {
    private Node start;
    private Node end;
    private Panel panel;

    public Path(Node start, Node end, Panel panel) {
        this.start = start;
        this.end = end;
        this.panel = panel;
    }

    public void buildPathTree(Node currentNode) {
        buildPathTree(currentNode, new HashSet<>());
    }

    private void buildPathTree(Node currentNode, HashSet<Node> visited) {
    if (currentNode == null || !visited.add(currentNode)) {
        return;
    }

    String next1Name = currentNode.getNext1();
    String next2Name = currentNode.getNext2();

    if (next1Name != null && !next1Name.equals("A")) {
        Node next1Node;
        if (next1Name.equals("W")) {
            next1Node = panel.getNode("EXIT");
        } else {
            next1Node = panel.getNode(next1Name);
        }
        currentNode.left = next1Node;
        buildPathTree(currentNode.left, visited);
    }

    if (next2Name != null && !next2Name.equals("A")) {
        Node next2Node;
        if (next2Name.equals("W")) {
            next2Node = panel.getNode("EXIT");
        } else {
            next2Node = panel.getNode(next2Name);
        }
        currentNode.right = next2Node;
        buildPathTree(currentNode.right, visited);
    }
}
    public Node getStart() {
        return start;
    }

    public void setStart(Node start) {
        this.start = start;
    }

    public Node getEnd() {
        return end;
    }

    public void setEnd(Node end) {
        this.end = end;
    }
}
