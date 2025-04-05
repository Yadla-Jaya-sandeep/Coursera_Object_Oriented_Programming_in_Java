package com.learnJava;

import java.util.HashMap;
import java.util.Map;

public class TwoKeyCaesarCipherBreaker {

    // Method to decrypt a character with a given key
    public static char decryptChar(char ch, int key) {
        if (Character.isLetter(ch)) {
            char base = Character.isUpperCase(ch) ? 'A' : 'a';
            return (char) (base + (ch - base - key + 26) % 26); // Shift backward
        }
        return ch; // Return unchanged if not a letter
    }

    // Method to find the most frequent letter in a given text
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

    // Method to determine the decryption key from the most common letter
    public static int findKey(char mostFrequent) {
        int key = mostFrequent - 'e';
        if (key < 0) key += 26; // Ensure positive key
        return key;
    }

    // Method to split the encrypted text and find the keys
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

    // Method to decrypt using the found keys
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
        String encryptedMessage = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!"; // Given encrypted text

        // Find keys
        int[] keys = findTwoKeys(encryptedMessage);
        System.out.println("Found Keys: Key1 = " + keys[0] + ", Key2 = " + keys[1]);

        // Decrypt the message using the found keys
        String decryptedMessage = decryptTwoKeys(encryptedMessage, keys[0], keys[1]);
        System.out.println("Decrypted Message: " + decryptedMessage);
    }
}