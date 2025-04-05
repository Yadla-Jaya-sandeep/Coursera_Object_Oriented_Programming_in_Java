package com.learnJava;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CharactersInPlay {
    private ArrayList<String> characterNames;
    private ArrayList<Integer> characterCounts;

    public CharactersInPlay() {
        characterNames = new ArrayList<>();
        characterCounts = new ArrayList<>();
    }

    public void update(String person) {
        int index = characterNames.indexOf(person);
        if (index == -1) {
            characterNames.add(person);
            characterCounts.add(1);
        } else {
            characterCounts.set(index, characterCounts.get(index) + 1);
        }
    }

    public void findAllCharacters() {
        characterNames.clear();
        characterCounts.clear();
        try {
            List<String> lines = Files.readAllLines(Paths.get("C:\\Users\\10703401\\OneDrive - LTIMindtree\\Desktop\\Sandeep\\JAVA LEARNING\\errors.txt"));
            for (String line : lines) {
                int periodIndex = line.indexOf('.');
                if (periodIndex != -1) {
                    String character = line.substring(0, periodIndex).trim();
                    update(character);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void tester() {
        findAllCharacters();
        for (int i = 0; i < characterNames.size(); i++) {
            if (characterCounts.get(i) > 1) {
                System.out.println(characterNames.get(i) + " " + characterCounts.get(i));
            }
        }
    }

    public void charactersWithNumParts(int num1, int num2) {
        for (int i = 0; i < characterNames.size(); i++) {
            int count = characterCounts.get(i);
            if (count >= num1 && count <= num2) {
                System.out.println(characterNames.get(i) + " " + count);
            }
        }
    }
    public void printMaxSpeaker(){
        int max=0;
        for(int i=0;i<characterCounts.size();i++){
            if(characterCounts.get(i)>characterCounts.get(max)){
                max=i;
            }
        }
        System.out.println("max character :"+characterNames.get(max)+"---"+characterCounts.get(max));
    }


    public static void main(String[] args) {
        CharactersInPlay cip = new CharactersInPlay();
        cip.tester();
        System.out.println("-------------------------------------------------------------");
        cip.charactersWithNumParts(10, 15);
        System.out.println("-------------------------------------------------------------");
        cip.printMaxSpeaker();

    }
}
