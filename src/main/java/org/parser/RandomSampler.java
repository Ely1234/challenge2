package org.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomSampler {

    public static List<String[]> sampler(String filename, int numberOfPoints) {
        List<String[]> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                data.add(line.split(","));
            }

            // Check if there is enough data
            if (data.size() < numberOfPoints) {
                throw new IllegalArgumentException("Not enough data points in file: " + filename);
            }

            // Randomly select a starting point
            Random rand = new Random();
            int startIndex = rand.nextInt(data.size() - numberOfPoints);
            return data.subList(startIndex, startIndex + numberOfPoints);

        } catch (IOException e) {
            System.err.println("Error reading file: " + filename);
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}