package org.parser;

import java.text.SimpleDateFormat;
import java.util.*;

public class PredictionData {

    public static List<String[]> predictNextPoints(List<String[]> sampledData) {
        List<String[]> predictedData = new ArrayList<>();

        // Convert the sampled data to stock prices
        List<Double> prices = new ArrayList<>();
        for (String[] row : sampledData) {
            prices.add(Double.parseDouble(row[2])); // Stock price is at the last index
        }

        // Apply the prediction logic
        double n1 = getSecondHighest(prices);  // Second highest value in the 10 points
        double n2 = n1- (prices.get(prices.size() - 1) - n1) / 2;  // Half the difference between n and n+1
        double n3 = n2 - (n2 - n1) / 4;  // 1/4th the difference between n+1 and n+2

        // Get the timestamp of the last data point
        String lastTimestamp = sampledData.get(sampledData.size() - 1)[1];

        // Generate the predicted data rows
        predictedData.add(new String[]{sampledData.get(0)[0], lastTimestamp, String.valueOf(n1)});
        predictedData.add(new String[]{sampledData.get(0)[0], getNextTimestamp(lastTimestamp), String.valueOf(n2)});
        predictedData.add(new String[]{sampledData.get(0)[0], getNextTimestamp(getNextTimestamp(lastTimestamp)), String.valueOf(n3)});

        return predictedData;
    }

    // Helper method to get the second highest value
    private static double getSecondHighest(List<Double> prices) {
        List<Double> sortedPrices = new ArrayList<>(prices);
        Collections.sort(sortedPrices, Collections.reverseOrder());
        return sortedPrices.get(1);
    }

    // Helper method to generate the next timestamp (one day ahead)
    private static String getNextTimestamp(String currentTimestamp) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date date = sdf.parse(currentTimestamp);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DATE, 1);  // Add one day to the timestamp
            return sdf.format(calendar.getTime());
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid date format: " + currentTimestamp);
        }
    }
}