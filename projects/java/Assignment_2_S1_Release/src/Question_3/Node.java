package Question_3;

public class Node {
    private String name;
    private int x;
    private int y;
    public String next1;
    public String next2;
    public Node left;
    public Node right;
    public boolean visited;

    public Node(String name, int x, int y, String next1, String next2) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.next1 = next1;
        this.next2 = next2;
        this.visited = false;
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getNext1() {
        return next1;
    }

    public String getNext2() {
        return next2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        return name.equals(node.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
