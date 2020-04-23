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
      //This is a version of MapReduce where we combine shuffle and reduce in the same step
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
  }
}

