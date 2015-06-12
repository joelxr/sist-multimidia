package br.edu.ifce.data;

import java.util.Arrays;
import java.util.Comparator;

public class TreeNode implements Comparable<TreeNode> {

    private int key;
    private char[] data;
    private double probability;
    private TreeNode left;
    private TreeNode right;
    private TreeNode root;

    public char[] getData() {
        return data;
    }

    public void setData(char[] data) {
        this.data = data;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int compareTo(TreeNode o) {
        if (this.getProbability() <= o.getProbability()) return -1;
        if (this.getProbability() > o.getProbability()) return 1;
        return -1;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "key=" + key +
                ", data=" + Arrays.toString(data) +
                ", probability=" + probability +
                ", left=" + left +
                ", right=" + right +
                ", root=" + root +
                '}';
    }
}
