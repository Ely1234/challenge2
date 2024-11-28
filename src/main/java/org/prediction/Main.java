package org.prediction;

import org.parser.PredictionData;
import org.parser.RandomSampler;

import java.io.File;
import java.util.*;

import static org.utils.FileHandler.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {

        String outAbsPathFile = "C:\\Users\\Eleonora\\IdeaProjects\\Challenge\\src\\main\\resources";

        // Folder containing the input CSV files
        int filesToProcess = 2;  // Default number of files to process
        File[] folders = getFileFromURL("in").listFiles();

        checkFiles(folders);

        assert folders != null;
        List<File> files = new ArrayList<>(Collections.emptyList());

        for(File folder : folders){
            files.addAll(Arrays.asList(Objects.requireNonNull(folder.listFiles((dir, name) -> name.endsWith(".csv")))));
        }

        // Process the specified number of files (or all available files)
        int filesToProcessCount = Math.min(filesToProcess, files.toArray().length);
        for (int i = 0; i < filesToProcessCount; i++) {
            File file = files.get(i);
            System.out.println("Processing file: " + file.getName());

            // Step 1: Sample data from file
            List<String[]> sampledData = RandomSampler.sampler(file.getPath(), 10);
            if (sampledData.isEmpty()) {
                System.err.println("No data sampled from file: " + file.getName());
                continue;
            }

            // Step 2: Predict next 3 points
            List<String[]> predictions = PredictionData.predictNextPoints(sampledData);

            // Step 3: Write output to new CSV file
            writeOutputFile(outAbsPathFile, file.getName(), sampledData, predictions);
        }
    }
}