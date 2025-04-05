package com.learnJava;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class TwoKeyDecryption {

    // Decrypt a single character with a given key
    public static char decryptChar(char ch, int key) {
        if (Character.isLetter(ch)) {
            char base = Character.isUpperCase(ch) ? 'A' : 'a';
            return (char) (base + (ch - base - key + 26) % 26);
        }
        return ch; // Return unchanged if not a letter
    }

    // Find the most frequent letter in the given text
    public static char mostFrequentLetter(String text) {
        Map<Character, Integer> frequencyMap = new HashMap<>();
        char mostCommon = 'e'; // Default assumption
        int maxCount = 0;

        for (char ch : text.toCharArray()) {
            if (Character.isLetter(ch)) {
                ch = Character.toLowerCase(ch);
                frequencyMap.put(ch, frequencyMap.getOrDefault(ch, 0) + 1);
                if (frequencyMap.get(ch) > maxCount) {
                    maxCount = frequencyMap.get(ch);
                    mostCommon = ch;
                }
            }
        }
        return mostCommon;
    }

    // Determine the decryption key based on frequency analysis
    public static int findKey(char mostFrequent) {
        int key = mostFrequent - 'e';
        if (key < 0) key += 26;
        return key;
    }

    // Find the two keys used in encryption
    public static int[] findTwoKeys(String encrypted) {
        StringBuilder evenChars = new StringBuilder();
        StringBuilder oddChars = new StringBuilder();

        for (int i = 0; i < encrypted.length(); i++) {
            if (i % 2 == 0) {
                evenChars.append(encrypted.charAt(i));
            } else {
                oddChars.append(encrypted.charAt(i));
            }
        }

        char mostCommonEven = mostFrequentLetter(evenChars.toString());
        char mostCommonOdd = mostFrequentLetter(oddChars.toString());

        int key1 = findKey(mostCommonEven);
        int key2 = findKey(mostCommonOdd);

        return new int[]{key1, key2};
    }

    // Decrypt the full message using two keys
    public static String decryptTwoKeys(String encrypted, int key1, int key2) {
        StringBuilder decrypted = new StringBuilder(encrypted.length());

        for (int i = 0; i < encrypted.length(); i++) {
            char ch = encrypted.charAt(i);
            int key = (i % 2 == 0) ? key1 : key2;
            decrypted.append(decryptChar(ch, key));
        }

        return decrypted.toString();
    }

    public static void main(String[] args) {
        try {
            // Read the encrypted file
            String encryptedText = new String(Files.readAllBytes(Paths.get("C:\\Users\\10703401\\OneDrive - LTIMindtree\\Desktop\\Sandeep\\JAVA LEARNING\\TwoKeyDecryption2.txt")));

            // Find the decryption keys
            int[] keys = findTwoKeys(encryptedText);
            System.out.println("Found Keys: Key1 = " + keys[0] + ", Key2 = " + keys[1]);

            // Decrypt the message
            String decryptedText = decryptTwoKeys(encryptedText, keys[0], keys[1]);

            // Split into words and print the first five
            String[] words = decryptedText.trim().split("\\s+"); // Ensure splitting works correctly
//            int wordCount = Math.min(5, words.length); // In case there are fewer than five words
            Arrays.stream(words).collect(Collectors.joining(" ")).toString();
            System.out.println(Arrays.stream(words).collect(Collectors.joining(" ")));
            System.out.println("First Five Words: " + words);

        } catch (IOException e) {
            System.err.println("Error reading the file.");
        }
    }
}