package com.learnJava;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class WordsInFilesStrict {
    private HashMap<String, HashSet<String>> wordToFileMap;

    public WordsInFilesStrict() {
        wordToFileMap = new HashMap<>();
    }

    private void addWordsFromFile(File file) {
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String word = scanner.next();
                // Match only exact lowercase words like "sea", not "Sea", "sea.", etc.
                if (word.matches("[a-z]+")) {
                    if (!wordToFileMap.containsKey(word)) {
                        wordToFileMap.put(word, new HashSet<>());
                    }
                    wordToFileMap.get(word).add(file.getName());
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + file.getName());
        }
    }

    public void buildWordFileMap(String folderPath) {
        wordToFileMap.clear();
        File folder = new File(folderPath);
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".txt"));
        if (files != null) {
            for (File f : files) {
                addWordsFromFile(f);
            }
        }
    }

    public int countWordsInAllFiles(int fileCount) {
        int count = 0;
        for (Set<String> fileSet : wordToFileMap.values()) {
            if (fileSet.size() == fileCount) {
                count++;
            }
        }
        return count;
    }

    public List<String> getFilesContainingWord(String word) {
        return new ArrayList<>(wordToFileMap.getOrDefault(word, new HashSet<>()));
    }

    public void analyze(String folderPath) {
        buildWordFileMap(folderPath);

        int totalFiles = 7;
        System.out.println("Words appearing in all 7 files: " + countWordsInAllFiles(7));
        System.out.println("Words appearing in exactly 4 files: " + countWordsInAllFiles(4));

        String word1 = "sea";
        String word2 = "tree";

        List<String> allFileNames = Arrays.asList(
                "caesar.txt", "confucius.txt", "errors.txt",
                "hamlet.txt", "likeit.txt", "macbeth.txt", "romeo.txt"
        );

        List<String> seaFiles = getFilesContainingWord(word1);
        System.out.println("\nFiles containing the word 'sea': " + seaFiles);

        for (String file : allFileNames) {
            if (!seaFiles.contains(file)) {
                System.out.println("The word 'sea' does NOT appear in: " + file);
            }
        }

        List<String> treeFiles = getFilesContainingWord(word2);
        System.out.println("\nFiles containing the word 'tree': " + treeFiles);
    }

    public static void main(String[] args) {
        WordsInFilesStrict analyzer = new WordsInFilesStrict();
        analyzer.analyze("C:\\Users\\10703401\\OneDrive - LTIMindtree\\Desktop\\Sandeep\\JAVA LEARNING\\GladLib\\Assignment"); // Put your 7 text files in a folder called "data"
    }
}
