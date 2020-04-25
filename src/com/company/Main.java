package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

  public static void main(String[] args) throws IOException {
    Path textPath = Paths.get("./src/dataFolder/messages.txt");
    //Reading lines into String Stream for future use
    Stream<String> messageLines = Files.lines(textPath).filter(line -> !line.isEmpty());
    //Add a split and flatMap Arrays
    List<String> messageWords = messageLines.map(String::toLowerCase).map(line -> line.split(" "))
        .flatMap(Arrays::stream).collect(Collectors.toList());
    messageWords.forEach(System.out::println);
    Map<Set<String>, Integer> biGrams = new HashMap<>();
    for (int i = 1; i < messageWords.size(); i++) {
      biGrams.merge(new HashSet<>(Arrays.asList(messageWords.get(i - 1), messageWords.get(i))), 1,
          Integer::sum);
    }
    //biGrams.forEach((key, value) -> System.out.println(key + ", " + value)); //Printing test

    //Confidence and Support loop unable to be accessed.

    /*for (HashSet<String> setOfFeatures : validResults.keySet()) {
      List<String> listOfFeatures = setOfFeatures.stream().collect(Collectors.toList());

      double confidence =
          (double) fullResults.get(listOfFeatures.get(0)) / validResults.get(setOfFeatures);
      double support = (double) validResults.get(setOfFeatures) / transactionTotal;

      System.out.printf("We show a confidence of %f that a person that "
              + "purchased %s will also buy %s%n       and a support of %f that "
              + "a person will purchase these items together at all.%n", confidence,
          listOfFeatures.get(0),
          listOfFeatures.get(1), support); //This will be used to test if the loop works correctly.
    }*/
    System.out.println("Please type a word");
    Scanner scan = new Scanner(System.in);
    String userWord = scan.nextLine();
    double support = 0.0; //Placeholder until actual support calculation works.
    //System.out.println(userWord); //Testing to see if input is correct.
    String[] wordList = new String[3]; //Array to hold the three words
    while (wordList[2].isEmpty()) {
      int increm = 0;
      for (int i = 0; i < 3; i++) {
        while (biGrams.size() > increm) {
          //String word = biGrams.getKey();
          String word = "placeholder";
          if (support > 0.65) {
            wordList[i] = word;
          } else {
            wordList[i] = "the";
            wordList[i+1] = "this";
            wordList[i+2] = "of";
          }
        }
        increm++;
      }
    }
    for(int i = 0; i < 3; i ++){
      System.out.println("Your next word might be " + wordList[i] + ".\n");
    }
  }
}

