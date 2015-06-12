package br.edu.ifce.calculation;

import br.edu.ifce.data.Tree;
import br.edu.ifce.data.TreeNode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

/**
 * @author Joel Rocha
 * @author Yuri Oliveira
 */
public class HuffmanEntropy extends Entropy {

    private Map<Character, Integer> dictionary;
    private Map<Character, Double> dictionaryFrequency;
    private Tree tree;

    public HuffmanEntropy() {
        dictionary = new HashMap<Character, Integer>();
        dictionaryFrequency = new TreeMap<Character, Double>();
    }

    public static void main(String[] args) {
        HuffmanEntropy e = new HuffmanEntropy();

        e.readData();

        System.out.println("Occurrences: " + e.getDictionary());
        System.out.println("Entropy: " + e.calculateEntropy());
        System.out.println("Probabilities: " + e.getDictionaryFrequency());

        SortedSet<TreeNode> set = new TreeSet<TreeNode> ();

        for (Map.Entry<Character, Double> entry : e.getDictionaryFrequency().entrySet()) {
            TreeNode node = new TreeNode();
            node.setProbability(entry.getValue());
            char[] array = {entry.getKey()};
            node.setData( array);
            set.add(node);
        }

        System.out.println(set);
    }

    public void readData() {

        List<String> data = readFile("data.txt");
        String dataString = "";

        if (data != null) {
            for (String s : data) {
                dataString += s;
            }

            for (char c : dataString.toCharArray()) {

                Integer count = dictionary.get(c);

                dictionaryFrequency.put(c, 0.0);

                if (count == null) {
                    dictionary.put(c, 1);
                } else {
                    count++;
                    dictionary.put(c, count);
                }
            }
        }
    }

    /**
     * Open and read a file, and return the lines in the file as a list
     * of Strings.
     * (Demonstrates Java FileReader, BufferedReader, and Java5.)
     */
    private List<String> readFile(String filename) {
        List<String> records = new ArrayList<String>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                records.add(line);
            }
            reader.close();
            return records;
        } catch (Exception e) {
            System.err.format("Exception occurred trying to read '%s'.", filename);
            e.printStackTrace();
            return null;
        }
    }

    public double log(double x, int base) {
        return Math.log(x) / Math.log(base);
    }

    public double calculateEntropy() {

        double totalTimes = 0;

        for (int times : dictionary.values()) {
            totalTimes += times;
        }

        Double entropy = 0.0;

        for (Map.Entry<Character, Integer> entry : dictionary.entrySet()) {
            Character key = entry.getKey();
            int times = entry.getValue();
            double probability = times / totalTimes;
            double aux = probability * log(probability, 2);
            entropy += aux;

            dictionaryFrequency.put(key, probability);
        }

        entropy = -entropy;
        return entropy;
    }

    public void generateTree (Map<Character, Double> dictionary) {

        Character primeiroMenor = (Character) dictionary.keySet().toArray()[0];
        Double primeiroMenorProb = (Double) dictionary.values().toArray()[0];

        // pega o 1o. menor
        for (Map.Entry<Character, Double> entry : dictionary.entrySet()) {
            Character character = entry.getKey();
            Double probality = entry.getValue();

            if (probality <= primeiroMenorProb) {
                primeiroMenor = character;
                primeiroMenorProb = probality;
            }
        }



        // pega o 2o. menor
        for (Map.Entry<Character, Double> entry : dictionary.entrySet()) {
            Character character = entry.getKey();
            Double probality = entry.getValue();

        }
    }

    public Map<Character, Integer> getDictionary() {
        return this.dictionary;
    }

    public Map<Character, Double> getDictionaryFrequency() {
        return this.dictionaryFrequency;
    }
}
