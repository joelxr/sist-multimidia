package br.edu.ifce.calculation;

import br.edu.ifce.data.Node;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
import java.util.TreeSet;

/**
 * @author Joel Rocha
 * @author Yuri Oliveira
 */
public class Huffman {

    private TreeSet<Node> dictionary;

    public Huffman() {
        dictionary = new TreeSet<Node>();
    }

    public static void main(String[] args) {
        Huffman huffman = new Huffman();
        huffman.readData();
        System.out.println("Entropy: " + huffman.calculateEntropy());
        System.out.println("Occurrences: " + huffman.getDictionary());

        System.out.println( huffman.getDictionary().first() );
        System.out.println( huffman.getDictionary().ceiling( huffman.getDictionary().first() ) );
    }

    public void readData() {
        List<String> data = null;
        String content = "";

        try {
            data = readFile("data.txt");

            for (String s : data) {
                content += s;
            }

            for (char c : content.toCharArray()) {
                Node node = new Node(c);

                if (dictionary.contains(node)) {
                    Node aux = dictionary.headSet(node, true).last();
                    int count = aux.getFrequency();
                    aux.setFrequency(++count);
                } else {
                    dictionary.add(node);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<String> readFile(String filename) throws IOException {
        List<String> records = new ArrayList<String>();
        BufferedReader reader = null;
        String line;

        try {
            reader = new BufferedReader(new FileReader(filename));

            while ((line = reader.readLine()) != null) {
                records.add(line);
            }
        } finally {
            if (reader != null)
                reader.close();
        }

        return records;
    }

    public double calculateEntropy() {
        double totalFrequency = getTotalFrequency();
        double entropy = 0.0;

        TreeSet<Node> tempTree = new TreeSet<Node>();
        
        for (Node node : dictionary) {
            int frequency = node.getFrequency();
            double probability = frequency / totalFrequency;
            node.setProbability(probability);
            entropy += probability * Math.log(probability) / Math.log(2);
            tempTree.add(node);
        }
        
        this.dictionary = tempTree;
        
        entropy = -entropy;
        return entropy;
    }

    private double getTotalFrequency() {
        double totalTimes = 0;

        for (Node node : dictionary) {
            totalTimes += node.getFrequency();
        }

        return totalTimes;
    }

    public TreeSet<Node> getDictionary() {
        return dictionary;
    }

    public void setDictionary(TreeSet<Node> dictionary) {
        this.dictionary = dictionary;
    }
}
