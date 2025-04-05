package com.learnJava;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class WordsInFiles {
    private Map<String, List<String>> wordFileMap;

    public WordsInFiles() {
        wordFileMap = new HashMap<>();
    }

    private void addWordsFromFile(File file) throws IOException {
        List<String> lines = Files.readAllLines(file.toPath());
        for (String line : lines) {
            String[] words = line.split("\\W+");
            for (String word : words) {
                wordFileMap.computeIfAbsent(word, k -> new ArrayList<>()).add(file.getName());
            }
        }
    }

    public void buildWordFileMap() throws IOException {
        wordFileMap.clear();
        File[] files = new File("C:\\Users\\10703401\\OneDrive - LTIMindtree\\Desktop\\Sandeep\\JAVA LEARNING\\WordsInFiles").listFiles();
        System.err.println(files.length);
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    addWordsFromFile(file);
                }
            }
        }
    }

    public int maxNumber() {
        return wordFileMap.values()
                .stream()
                .mapToInt(List::size)
                .max()
                .orElse(0);
    }

    public List<String> wordsInNumFiles(int number) {
        return wordFileMap.entrySet()
                .stream()
                .filter(entry -> entry.getValue().size() == number)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public void printFilesIn(String word) {
        List<String> files = wordFileMap.get(word);
        if (files != null) {
            files.forEach(System.out::println);
        }
    }

    public void tester() throws IOException {
        buildWordFileMap();
        int maxFiles = maxNumber();
        System.out.println("Maximum number of files any word is in: " + maxFiles);
        List<String> words = wordsInNumFiles(maxFiles);
        System.out.println("Words in " + maxFiles + " files: " + words);
        words.forEach(word -> {
            System.out.println("Files containing the word \"" + word + "\":");
            printFilesIn(word);
        });
    }

    public static void main(String[] args) throws IOException {
        WordsInFiles wif = new WordsInFiles();
        wif.tester();
    }
}

