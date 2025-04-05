package com.learnJava;


import java.util.*;
import java.nio.file.*;
import java.io.IOException;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;

    public WordFrequencies() {
        myWords = new ArrayList<>();
        myFreqs = new ArrayList<>();
    }

    public void findUnique() {
        myWords.clear();
        myFreqs.clear();
        try {
            List<String> lines = Files.readAllLines(Paths.get("C:\\Users\\10703401\\OneDrive - LTIMindtree\\Desktop\\Sandeep\\JAVA LEARNING\\errors.txt"));
            for (String line : lines) {
                String[] words = line.toLowerCase().split("\\s+");
                for (String word : words) {
                    if (!word.isEmpty()) {
                        int index = myWords.indexOf(word);
                        if (index == -1) {
                            myWords.add(word);
                            myFreqs.add(1);
                        } else {
                                myFreqs.set(index, myFreqs.get(index) + 1);
                            }

                        }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void tester() {
        findUnique();
        System.out.println("Number of unique words: " + myWords.size());
        for (int i = 0; i < myWords.size(); i++) {
            System.out.println(myFreqs.get(i) + " " + myWords.get(i));
        }
        int maxIndex = findIndexOfMax();
        System.out.println("The word that occurs most often and its count are: " + myWords.get(maxIndex) + " " + myFreqs.get(maxIndex));
    }

    public int findIndexOfMax() {
        int maxIndex = 0;
        for (int i = 1; i < myFreqs.size(); i++) {
            if (myFreqs.get(i) > myFreqs.get(maxIndex)) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public static void main(String[] args) {
        WordFrequencies wf = new WordFrequencies();
        wf.tester();
    }
}
