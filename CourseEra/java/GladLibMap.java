package com.learnJava;

import java.util.*;
import java.nio.file.*;
import java.io.IOException;

public class GladLibMap {
    private Map<String, List<String>> myMap;
    private List<String> usedWords;
    private Random myRandom;

    public GladLibMap() {
//        System.out.println("Constructor called : ");
        myMap = new HashMap<>();
        usedWords = new ArrayList<>();
        myRandom = new Random();
        initializeFromSource("C:\\Users\\10703401\\OneDrive - LTIMindtree\\Desktop\\Sandeep\\JAVA LEARNING\\GladLib");
    }

    private void initializeFromSource(String source) {
        String[] categories = {"adjective", "noun", "color", "country", "name", "animal", "time", "verb", "fruit"};
        for (String category : categories) {
            List<String> words = readIt(source + "/" + category + ".txt");
            myMap.put(category, words);
//           System.out.println("Loaded " + words.size() + " words for category: " + category);
        }
    }

    private List<String> readIt(String source) {
        try {
            return Files.readAllLines(Paths.get(source));
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private String randomFrom(List<String> source) {
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }

    private String getSubstitute(String label) {
//        System.err.println(label);
        List<String> words = myMap.getOrDefault(label, new ArrayList<>());
        String word;
        do {
            word = randomFrom(words);
        } while (usedWords.contains(word));
        usedWords.add(word);
        return word;
    }

    private String processWord(String word) {
        int first = word.indexOf("<");
        int last = word.indexOf(">", first);
        if (first == -1 || last == -1) {
            return word;
        }
        String prefix = word.substring(0, first);
        String suffix = word.substring(last + 1);
        String sub = getSubstitute(word.substring(first + 1, last));
//        System.out.println("Replacing " + word + " with " + sub);
        return prefix + sub + suffix;
    }

    public String fromTemplate(String source) {
        StringBuilder story = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(source));
//            System.out.println("Template lines: " + lines);
            for (String line : lines) {
                String[] words = line.split("\\s+");
                for (String word : words) {
//                    System.out.println("Processing word: " + word);
                    story.append(processWord(word)).append(" ");
                }
                story.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return story.toString();
    }

    public int totalWordsInMap() {
        return myMap.values().stream().mapToInt(List::size).sum();
    }

    public int totalWordsConsidered() {
        return usedWords.stream().map(word -> myMap.entrySet().stream().filter(entry -> entry.getValue().contains(word)).map(Map.Entry::getKey).findFirst().orElse("")).distinct().mapToInt(category -> myMap.getOrDefault(category, new ArrayList<>()).size()).sum();
    }

    public static void main(String[] args) {
        GladLibMap gladLib = new GladLibMap();
        String story = gladLib.fromTemplate("C:\\Users\\10703401\\OneDrive - LTIMindtree\\Desktop\\Sandeep\\JAVA LEARNING\\GladLib\\Template.txt");
        System.out.println(story);
        System.out.println("Total words in map: " + gladLib.totalWordsInMap());
        System.out.println("Total words considered: " + gladLib.totalWordsConsidered());
    }
}

