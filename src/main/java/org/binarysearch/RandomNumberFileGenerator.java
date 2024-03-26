package org.binarysearch;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class RandomNumberFileGenerator {
    public static void main(String[] args) {
        String fileName = "10000.txt";
        int numberOfNumbers = 10000;
        generateRandomNumbersToFile(fileName, numberOfNumbers);
        System.out.println("Random numbers generated and saved to file: " + fileName);
    }

    public static void generateRandomNumbersToFile(String fileName, int numberOfNumbers) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            Random random = new Random();
            for (int i = 0; i < numberOfNumbers; i++) {
                int randomNumber = random.nextInt(2001) - 1000; // Generate random number between -1000 and 1000
                writer.write(Integer.toString(randomNumber));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
