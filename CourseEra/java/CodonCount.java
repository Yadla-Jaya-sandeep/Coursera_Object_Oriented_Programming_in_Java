package com.learnJava;

import java.util.*;
import java.util.stream.Collectors;

public class CodonCount {
    private Map<String, Integer> codonMap;

    public CodonCount() {
        codonMap = new HashMap<>();
    }

    public void buildCodonMap(int start, String dna) {
        codonMap.clear();
        int length = dna.length();
        for (int i = start; i <= length - 3; i += 3) {
            String codon = dna.substring(i, i + 3);
            codonMap.put(codon, codonMap.getOrDefault(codon, 0) + 1);
        }
    }

    public String getMostCommonCodon() {
        return codonMap.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("");
    }

    public void printCodonCounts(int start, int end) {
        codonMap.entrySet()
                .stream()
                .filter(entry -> entry.getValue() >= start && entry.getValue() <= end)
                .forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue()));
    }

    public void tester(String dna) {
        dna = dna.toUpperCase();
        for (int i = 0; i < 3; i++) {
            buildCodonMap(i, dna);
            System.out.println("Reading frame starting with " + i + " results in " + codonMap.size() + " unique codons");
            String mostCommonCodon = getMostCommonCodon();
            System.out.println("and most common codon is " + mostCommonCodon + " with count " + codonMap.get(mostCommonCodon));
            System.out.println("Counts of codons between 1 and 5 inclusive are:");
            printCodonCounts(0, 10);
            System.out.println("-----------------------------------------------------------");
        }
    }

    public static void main(String[] args) {
        CodonCount cc = new CodonCount();
        String dna = "CAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCACCAGCCCAGAATCAACTGCATAACATACAAACTTTAAAAGGAAGAAATCTAACATACAACCTTTAAAAGGAAGAAATCGCACCAGCCCAGAATCAACTGCATAACATACAAACTTTAAAAGGAAGAAATCCAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCACCAGCCCAGAATCAACTGCATAACATACAAACTTTAAAAGGAAGAAATC"; // Example DNA strand
        cc.tester(dna);
    }
}

