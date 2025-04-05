package com.learnJava;

import java.io.*;
import java.util.*;

public class MostCommonWordLength {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\10703401\\OneDrive - LTIMindtree\\Desktop\\Sandeep\\JAVA LEARNING\\commonwordlenght1.txt"; // Replace with your actual file path
        List<String> words = readWordsFromFile(filePath);

        if (words == null || words.isEmpty()) {
            System.out.println("No words found in the file.");
            return;
        }

        int[] counts = new int[31]; // Assuming max word length is 30
        countWordLengths(words, counts);
        int mostCommonLength = findMostCommonLength(counts);

        System.out.println("Most common word length: " + mostCommonLength);
    }

    private static List<String> readWordsFromFile(String filePath) {
        List<String> words = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                words.addAll(Arrays.asList(line.split("\\s+"))); // Split by spaces
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
            return null;
        }
        return words;
    }

    private static void countWordLengths(List<String> words, int[] counts) {
        for (String word : words) {
            String cleanWord = removeEdgePunctuation(word);
            int length = cleanWord.length();
            if (length > 0) {
                length = Math.min(length, counts.length - 1); // Cap max length
                counts[length]++;
            }
        }
    }

    private static String removeEdgePunctuation(String word) {
        return word.replaceAll("^[^a-zA-Z0-9]+|[^a-zA-Z0-9]+$", ""); // Removes first & last punctuation
    }

    private static int findMostCommonLength(int[] counts) {
        int maxCount = 0, mostCommonLength = 0;
        for (int i = 1; i < counts.length; i++) {
            if (counts[i] > maxCount) {
                maxCount = counts[i];
                mostCommonLength = i;
            }
        }
        return mostCommonLength;
    }

    public static class CesarCipherOneKeyEncrypt {
            public static String encrypt(String text, int key) {
                StringBuilder result = new StringBuilder();

                for (char c : text.toCharArray()) {
                    if (Character.isLetter(c)) {
                        char base = Character.isUpperCase(c) ? 'A' : 'a';
                        result.append((char) ((c - base + key) % 26 + base));
                    } else {
                        result.append(c);
                    }
                }

                return result.toString();
            }

//            public static void main(String[] args) {
//                String text = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
//                int key = 15;
//
//                String encryptedText = encrypt(text, key);
//                System.out.println("Encrypted Text: " + encryptedText);
//            }
    }
}