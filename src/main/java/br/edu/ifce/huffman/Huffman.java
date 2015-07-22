package br.edu.ifce.huffman;

import br.edu.ifce.util.Util;

import java.util.HashMap;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * @author Joel Rocha
 * @author Yuri Oliveira
 */
public class Huffman {

    private TreeSet<Node> nodes;
    private double occurrences;
    private Map<Character, String> dictionary;

    public Huffman() {
        nodes = new TreeSet<Node>();
    }


    public static void main(String[] args) {
        Huffman huffman = new Huffman();
        String data = "abcaab";
        String encodedString = huffman.encode(data);
        String decodedString = huffman.decode(encodedString);

        System.out.println((data.equals(decodedString)));
    }

    public String encode(String data) {
        String result = "";

        this.createDictionary(data);
        this.generateTree();
        this.generateDictionary();

        for (Character c : data.toCharArray()) {
            Map<Character, String> dic = this.getDictionary();
            result += dic.get(c);
        }

        System.out.println(this.getDictionary());
        System.out.println(result);
        return result;
    }

    public String decode(String encodedString) {
        String result = "";
        int start = 0;
        int end = 0;

        while (end < encodedString.length()) {
            String current = Util.getContent(encodedString, start, end);

            for (Map.Entry<Character, String> entry : this.getDictionary().entrySet()) {
                Character key = entry.getKey();
                String value = entry.getValue();

                if (current.equals(value)) {
                    result += key;
                    start += value.length();
                    break;
                }
            }

            end++;
        }

        return result;
    }

    public void createDictionary(String data) {
        this.occurrences = data.toCharArray().length;
        calculateFrequency(data);
        calculateProbabilities();
        calculateEntropy();
    }

    public void calculateFrequency(String data) {
        for (char c : data.toCharArray()) {
            Node node = new Node(c);

            if (nodes.contains(node)) {
                Node aux = nodes.headSet(node, true).last();
                int count = aux.getFrequency();
                aux.setFrequency(++count);
            } else {
                nodes.add(node);
            }
        }
    }

    public void calculateProbabilities() {
        TreeSet<Node> nodes = new TreeSet<Node>();

        for (Node node : this.nodes) {
            double probability = node.getFrequency() / occurrences;
            node.setProbability(probability);
            nodes.add(node);
        }

        this.nodes = nodes;
    }

    public double calculateEntropy() {
        double entropy = 0.0;

        for (Node node : nodes.stream().filter(n -> n.getValue().length() == 1).collect(Collectors.toList())) {
            double aux = (Math.log(node.getProbability()) / Math.log(2));
            entropy += node.getProbability() * aux;
        }

        return -entropy;
    }

    public void generateTree() {
        NavigableSet<Node> navigableSet = this.nodes.descendingSet();
        TreeSet<Node> tmpTree = new TreeSet<Node>();

        Node last = null;
        Node sndLast = null;

        while (navigableSet.first().getProbability() < 1) {
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

        this.nodes = tmpTree;
    }

    public void generateDictionary() {
        Map<Character, String> map = new HashMap<Character, String>();
        NavigableSet<Node> navigableSet = this.nodes.descendingSet();
        String result = "";
        Node node = navigableSet.first();
        String value = node.getValue();

        for (char c : value.toCharArray()) {

            while (node.getRight() != null && node.getLeft() != null) {

                if (node.getLeft().getValue().contains(String.valueOf(c))) {
                    result += 0;
                    node = node.getLeft();
                } else {
                    result += 1;
                    node = node.getRight();
                }
            }

            map.put(c, result);
            result = "";
            node = navigableSet.first();
        }

        this.dictionary = map;
    }

    public String translate(String data) {
        String result = "";

        for (char c : data.toCharArray()) {
            result += dictionary.get(c);
        }

        return result;
    }

    public double getAverageLength() {

        NavigableSet<Node> navigableSet = nodes.descendingSet();
        double sum = 0.0;

        for (Map.Entry<Character, String> entry : this.dictionary.entrySet()) {
            char c = entry.getKey();
            double prob = 0;

            for (Node n : navigableSet) {
                String cValue = String.valueOf(c);
                if (n.getValue().equals(cValue)) {
                    prob = n.getProbability();
                    break;
                }
            }

            double result = this.dictionary.get(c).length() * prob;
            sum += result;
        }

        return sum;
    }

    public Node join(Node node1, Node node2) {
        Node newNode = new Node(node1.getValue() + node2.getValue());
        newNode.setFrequency(node1.getFrequency() + node2.getFrequency());
        newNode.setProbability(node1.getProbability() + node2.getProbability());
        newNode.setLeft(node1);
        newNode.setRight(node2);
        return newNode;
    }

    public TreeSet<Node> getNodes() {
        return nodes;
    }

    public Map<Character, String> getDictionary() {
        return dictionary;
    }
}
