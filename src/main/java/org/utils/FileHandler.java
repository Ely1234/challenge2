package org.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

public class FileHandler {

    public static File getFileFromURL(String path) {
        URL url = FileHandler.class.getClassLoader().getResource(path);
        File file = null;
        try {
            file = new File(url.toURI());
        } catch (URISyntaxException e) {
            file = new File(url.getPath());
        } finally {
            return file;
        }
    }

    // Write the output data to a new CSV file
    public static void writeOutputFile(String absPath, String filename, List<String[]> sampledData, List<String[]> predictions) {
        FileWriter outputFile = null;
        String outputFileName;

        boolean mkdir = (new File(absPath + "\\out")).mkdir();
        outputFileName = absPath + "\\out\\predicted_" + filename;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(String.valueOf(outputFile)))) {
            // Write sampled data
            for (String[] dataRow : sampledData) {
                writer.write(String.join(",", dataRow));
                writer.newLine();
                System.out.println(String.join(",", dataRow) + "\n");
            }

            // Write predicted data
            for (String[] predictedRow : predictions) {
                writer.write(String.join(",", predictedRow));
                writer.newLine();
                System.out.println(String.join(",", predictedRow) + "\n");
            }

            System.out.println("Output written to: " + outputFileName);
        } catch (IOException e) {
            System.err.println("Error writing to output file: " + filename);
            e.printStackTrace();
        }
    }

    public static boolean checkFiles(File[] files) {
        // Check if any CSV files are present
        if (files == null || files.length == 0) {
            System.err.println("No CSV files found in the folder.");
            return true;
        }
        return false;
    }
}
