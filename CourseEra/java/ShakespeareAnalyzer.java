package com.learnJava;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ShakespeareAnalyzer {
    private static final String[] FILE_PATHS = {
            "C:\\Users\\10703401\\OneDrive - LTIMindtree\\Desktop\\Sandeep\\JAVA LEARNING\\java-8\\java-8\\src\\com\\learnJava\\caesar.txt",
            "C:\\Users\\10703401\\OneDrive - LTIMindtree\\Desktop\\Sandeep\\JAVA LEARNING\\java-8\\java-8\\src\\com\\learnJava\\hamlet.txt",
            "C:\\Users\\10703401\\OneDrive - LTIMindtree\\Desktop\\Sandeep\\JAVA LEARNING\\java-8\\java-8\\src\\com\\learnJava\\likeit.txt",
            "C:\\Users\\10703401\\OneDrive - LTIMindtree\\Desktop\\Sandeep\\JAVA LEARNING\\java-8\\java-8\\src\\com\\learnJava\\macbeth.txt",
            "C:\\Users\\10703401\\OneDrive - LTIMindtree\\Desktop\\Sandeep\\JAVA LEARNING\\java-8\\java-8\\src\\com\\learnJava\\romeo.txt"
    };

    public static void main(String[] args) {
        Map<String, Set<String>> wordFilesMap = new HashMap<>();

        // Read each file and update the word occurrences
        for (String filePath : FILE_PATHS) {
            Set<String> words = readWords(filePath);
            for (String word : words) {
                wordFilesMap.computeIfAbsent(word, k -> new HashSet<>()).add(filePath);
            }
        }

        // Find words that occur in all five files
        List<String> wordsInAllFiles = new ArrayList<>();
        // Find words that occur in four of the five files
        List<String> wordsInFourFiles = new ArrayList<>();

        for (Map.Entry<String, Set<String>> entry : wordFilesMap.entrySet()) {
            if (entry.getValue().size() == 5) {
                wordsInAllFiles.add(entry.getKey());
            } else if (entry.getValue().size() == 4) {
                wordsInFourFiles.add(entry.getKey());
            }
        }

        // Find the files where the word "sad" does not appear
        List<String> filesWithoutSad = new ArrayList<>();
        for (String filePath : FILE_PATHS) {
            if (!readWords(filePath).contains("sad")) {
                filesWithoutSad.add(filePath);
            }
        }

        // Find the files where the word "red" appears
        List<String> filesWithRed = new ArrayList<>();
        for (String filePath : FILE_PATHS) {
            if (readWords(filePath).contains("red")) {
                filesWithRed.add(filePath);
            }
        }

        // Print the results
        System.out.println("Words that occur in all five files: " + wordsInAllFiles.size());
        System.out.println("Words that occur in four of the five files: " + wordsInFourFiles.size());
        System.out.println("Files where the word 'sad' does not appear: ");
        filesWithoutSad.stream().map(s->s.replace("C:\\Users\\10703401\\OneDrive - LTIMindtree\\Desktop\\Sandeep\\JAVA LEARNING\\java-8\\java-8\\src\\com\\learnJava\\","")).forEach(System.out::println);
        System.out.println("Files where the word 'red' appears: " );
        filesWithRed.stream().map(s->s.replace("C:\\Users\\10703401\\OneDrive - LTIMindtree\\Desktop\\Sandeep\\JAVA LEARNING\\java-8\\java-8\\src\\com\\learnJava\\","")).forEach(System.out::println);
    }

    private static Set<String> readWords(String filePath) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            return new HashSet<>(Arrays.asList(content.toLowerCase().split("\\W+")));
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptySet();
        }
    }
}

