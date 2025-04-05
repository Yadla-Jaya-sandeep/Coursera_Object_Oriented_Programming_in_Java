package com.learnJava;

public class TwoKeyCaesarCipherDecrypt {

    // Method to decrypt a single character using Caesar Cipher
    public static char decryptChar(char ch, int key) {
        if (Character.isLetter(ch)) {
            char base = Character.isUpperCase(ch) ? 'A' : 'a';
            return (char) (base + (ch - base - key + 26) % 26); // Shift backward
        }
        return ch; // Return unchanged if not a letter
    }

    // Method to decrypt a message encrypted with two keys
    public static String decryptTwoKeys(String encrypted, int key1, int key2) {
        StringBuilder decrypted = new StringBuilder(encrypted.length());

        for (int i = 0; i < encrypted.length(); i++) {
            char ch = encrypted.charAt(i);
            // Use key1 for even indexes, key2 for odd indexes
            int key = (i % 2 == 0) ? key1 : key2;
            decrypted.append(decryptChar(ch, key));
        }

        return decrypted.toString();
    }

    public static void main(String[] args) {
        // Given encrypted message
        String encryptedMessage = "Hfs cpwewloj loks cd Hoto kyg Cyy.";
        int key1 = 14;
        int key2 = 24;

        // Decrypt the message
        String decryptedMessage = decryptTwoKeys(encryptedMessage, key1, key2);
        System.out.println("Decrypted Message: " + decryptedMessage);
    }
}