package br.edu.ifce.calculation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Joel Rocha
 * @author Yuri Oliveira
 */
public class Entropy {

    private Map<Character, Integer> dictionary;

    public Entropy() {
        dictionary = new HashMap<Character, Integer>();
    }

    public static void main(String[] args) {
        Entropy e = new Entropy();
        e.readData(args[0]);

        System.out.println("Occurrences: " + e.getDictionary());
        System.out.println("Entropy: " + e.calculateEntropy());
    }

    public void readData(String path) {
        List<String> data = readFile(path);
        String dataString = "";

        if (data != null) {
            for (String s : data) {
                dataString += s;
            }

            for (char c : dataString.toCharArray()) {

                Integer count = dictionary.get(c);

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

        double totalTimes = 0.0;
        double entropy = 0.0;

        for (int times : dictionary.values()) {
            totalTimes += times;
        }

        for (int times : dictionary.values()) {
            double freq = times / totalTimes;
            double aux = freq * log(freq, 2);
            entropy += aux;
        }

        return -entropy;
    }

    public Map<Character, Integer> getDictionary() {
        return this.dictionary;
    }
}