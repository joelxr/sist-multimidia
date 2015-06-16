package br.edu.ifce.data;

public class Node implements Comparable<Node> {

    private char value;
    private String trace;
    private int frequency;
    private double probability;
    private Node left;
    private Node right;
    private Node root;
    private int key;

    public Node(char data) {
        this.value = data;
        this.trace = "" + data;
        this.frequency = 1;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public String getTrace() {
        return trace;
    }

    public void setTrace(String trace) {
        this.trace = trace;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int compareTo(Node node) {
        int result;

        result = compareDouble(this.getProbability(), node.getProbability());
        if (result != 0) return result;
        
        result = compareInt(this.getValue(), node.getValue());
        return result;
    }

    private static int compareInt(int i1, int i2) {
        if (i1 < i2) {
            return -1;
        } else if (i1 > i2) {
            return +1;
        } else {
            return 0;
        }
    }

    private static int compareDouble(double d1, double d2) {
        if (d1 < d2) {
            return -1;
        } else if (d1 > d2) {
            return +1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return this.compareTo(node) == 0;
    }

    @Override
    public int hashCode() {
        return (int) getValue();
    }

    @Override
    public String toString() {
        return value + "{trace=" + trace + ",frequency=" + frequency + ",probability=" + probability + "}";
    }
}
