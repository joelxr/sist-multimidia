package br.edu.ifce.data;

public class Node implements Comparable<Node> {

    private String value;
    private int frequency;
    private double probability;
    private Node left;
    private Node right;
    private Node root;

    public Node(String value) {
        this.value = value;
        this.frequency = 1;
    }

    public Node(char c) {
        this(String.valueOf(c));
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public String getValue() {
        return value;
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

    public int compareTo(Node node) {
        int result;

        if (this.getProbability() < node.getProbability()) {
            result = -1;
        } else if (this.getProbability() > node.getProbability()) {
            result = 1;
        } else {
            result = 0;
        }

        if (result != 0) {
            return result;
        }

        return this.getValue().compareTo(node.getValue());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return this.compareTo(node) == 0;
    }

    @Override
    public String toString() {
        return value + "{frequency=" + frequency + ",probability=" + probability + "}";
    }
}
