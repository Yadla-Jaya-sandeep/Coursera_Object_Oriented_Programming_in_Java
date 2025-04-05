package com.learnJava;

public class CaesarCipherTwoKeyEncrypt {
    public static String encrypt(String text, int key1, int key2) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            int key = (i % 2 == 0) ? key1 : key2; // Alternate keys for even and odd indices

            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                result.append((char) ((c - base + key) % 26 + base));
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        String text = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        int key1 = 21;  // First key
        int key2 = 8; // Second key

        String encryptedText = encrypt(text, key1, key2);
        System.out.println("Encrypted Text: " + encryptedText);
    }
}