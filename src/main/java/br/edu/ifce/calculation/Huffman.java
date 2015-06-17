package br.edu.ifce.calculation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.NavigableSet;
import java.util.TreeSet;

import br.edu.ifce.data.Node;

/**
 * @author Joel Rocha
 * @author Yuri Oliveira
 */
public class Huffman {

    private TreeSet<Node> dictionary;
    private String fileContent;
    private double occurrences;

    public Huffman() {
        dictionary = new TreeSet<Node>();
    }

    public static void main(String[] args) {
        Huffman huffman = new Huffman();

        try {
            huffman.createDictionaryFromFile("data.txt");

//            for (Node n : huffman.getDictionary()) {
//                System.out.println(n);
//            }

            huffman.generateTree();

            System.out.print("\n\n");

            for (Node n : huffman.getDictionary()) {
                System.out.println(n);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createDictionaryFromFile(String filename) throws IOException {
        this.fileContent = readFileContent(filename);
        this.occurrences = fileContent.toCharArray().length;
        calculateFrequency();
        calculateProbabilities();
        calculateEntropy();
    }

    public String readFileContent(String filename) throws IOException {
        StringBuilder content = new StringBuilder();
        BufferedReader reader = null;
        String line;

        try {
            reader = new BufferedReader(new FileReader(filename));

            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
        }

        return content.toString();
    }

    public void calculateFrequency() {
        for (char c : fileContent.toCharArray()) {
            Node node = new Node(c);

            if (dictionary.contains(node)) {
                Node aux = dictionary.headSet(node, true).last();
                int count = aux.getFrequency();
                aux.setFrequency(++count);
            } else {
                dictionary.add(node);
            }
        }
    }

    public void calculateProbabilities() {
        TreeSet<Node> nodes = new TreeSet<Node>();

        for (Node node : dictionary) {
            double probability = node.getFrequency() / occurrences;
            node.setProbability(probability);

            nodes.add(node);
        }

        this.dictionary = nodes;
    }

    public double calculateEntropy() {
        double entropy = 0.0;

        for (Node node : dictionary) {
            entropy += node.getProbability() * Math.log(node.getProbability()) / Math.log(2);
        }

        return -entropy;
    }

    public void generateTree() {
        NavigableSet<Node> navigableSet = this.dictionary.descendingSet();
        TreeSet<Node> tmpTree = new TreeSet<Node>();
        
        Node last = null;
        Node sndLast = null;
        
        while(navigableSet.first().getProbability() < 1) {
        	last = navigableSet.pollLast();
        	sndLast = navigableSet.pollLast();
        	
        	Node node = join(last, sndLast);
        	last.setRoot(node);
        	sndLast.setRoot(node);
        	
        	tmpTree.add(last);
        	tmpTree.add(sndLast);
        	tmpTree.add(node);
        	navigableSet.add(node);
        }

        this.dictionary = tmpTree;
    }

    public NavigableSet<Node> generateTree(NavigableSet<Node> navigableSet) {

        return null;
    }

    public Node join(Node node1, Node node2) {
        Node newNode = new Node(node1.getValue() + node2.getValue());
        newNode.setFrequency(node1.getFrequency() + node2.getFrequency());
        newNode.setProbability(node1.getProbability() + node2.getProbability());
        newNode.setLeft(node1);
        newNode.setRight(node2);
        return newNode;
    }

    public TreeSet<Node> getDictionary() {
        return dictionary;
    }
}
