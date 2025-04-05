package com.learnJava;

public class CaesarCipherOneKeyEncrypt {
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

    public static void main(String[] args) {
        String text = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        int key = 15;

        String encryptedText = encrypt(text, key);
        System.out.println("Encrypted Text: " + encryptedText);
    }
}