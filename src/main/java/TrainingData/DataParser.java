package TrainingData;

import VNN.Vector;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class DataParser {
    private final String filename;
    public DataParser(String filename) {
        this.filename = filename;
    }

    public Vector getSingleSample() throws IOException, CsvException {
        Vector sample;
        try (CSVReader reader = new CSVReader(new FileReader("/Users/daniellee/Desktop/VNN/src/main/java/TrainingData/testSmiley.csv"))) {
            List<String[]> dataSet = reader.readAll();
            int featureSize = dataSet.get(0).length;
            float[] data = new float[featureSize];
            for(int feature = 0; feature < featureSize; feature++) {
                data[feature] = Float.parseFloat(dataSet.get(0)[feature]);
            }
            sample = new Vector(data);
        }
        return sample;
    }

    public Vector[] getTrainingData() throws IOException, CsvException {
        Vector[] trainingData;
        try (CSVReader reader = new CSVReader(new FileReader(filename))) {
            List<String[]> dataSet = reader.readAll();
            trainingData = new Vector[dataSet.size()];
            int featureSize = dataSet.get(0).length - 1;
            for(int dataRow = 0; dataRow < dataSet.size(); dataRow++) {
                String[] row = dataSet.get(dataRow);
                float[] data = new float[featureSize];
                for(int feature = 0; feature < featureSize; feature++) {
                    data[feature] = Float.parseFloat(row[feature]);
                }
                trainingData[dataRow] = new Vector(data);
            }
        }
        return trainingData;
    }

    public Vector[] getTargets() throws IOException, CsvException {
        Vector[] targets;
        try (CSVReader reader = new CSVReader(new FileReader(filename))) {
            List<String[]> dataSet = reader.readAll();
            targets = new Vector[dataSet.size()];
            int featureSize = dataSet.get(0).length;
            for(int dataRow = 0; dataRow < dataSet.size(); dataRow++) {
                String[] row = dataSet.get(dataRow);
                float[] data = new float[1];
                data[0] = Float.parseFloat(row[featureSize - 1]);
                targets[dataRow] = new Vector(data);
            }
        }
        return targets;
    }

}