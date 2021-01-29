package TrainingData;

import VNN.Vector;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class TrainingData {
    public static void main(String[] args) throws IOException, CsvException {
        getInputs();
    }

    public static Vector[] getTrainingSet() throws IOException, CsvException {
        Vector[] inputs;
        try (CSVReader reader = new CSVReader(new FileReader("/Users/daniellee/Desktop/VNN/src/main/java/TrainingData/iris.csv"))) {
            List<String[]> dataSet = reader.readAll();
            inputs = new Vector[100];
            for(int dataRow = 0; dataRow < 100; dataRow++) {
                float[] input = new float[4];
                String[] row = dataSet.get(dataRow);
                input[0] = Float.parseFloat(row[0]);
                input[1] = Float.parseFloat(row[1]);
                input[2] = Float.parseFloat(row[2]);
                input[3] = Float.parseFloat(row[3]);
                inputs[dataRow] = new Vector(input);
            }
        }
        return inputs;
    }

    public static Vector[] getTrainingTargets() throws IOException, CsvException {
        Vector[] targets;
        try (CSVReader reader = new CSVReader(new FileReader("/Users/daniellee/Desktop/VNN/src/main/java/TrainingData/iris.csv"))) {
            List<String[]> dataSet = reader.readAll();
            targets = new Vector[100];
            for(int dataRow = 0; dataRow < 100; dataRow++) {
                float[] target = new float[3];
                String[] row = dataSet.get(dataRow);
                if(row[4].equals("setosa")) {
                    target[0] = 1f;
                    target[1] = 0f;
                    target[2] = 0f;
                } else if(row[4].equals("versicolor")) {
                    target[0] = 0f;
                    target[1] = 1f;
                    target[2] = 0f;
                } else {
                    target[0] = 0f;
                    target[1] = 0f;
                    target[2] = 1f;
                }
                targets[dataRow] = new Vector(target);
            }
        }
        return targets;
    }

    public static Vector[] getTestingSet() throws IOException, CsvException {
        Vector[] inputs;
        try (CSVReader reader = new CSVReader(new FileReader("/Users/daniellee/Desktop/VNN/src/main/java/TrainingData/iris.csv"))) {
            List<String[]> dataSet = reader.readAll();
            inputs = new Vector[50];
            for(int dataRow = 100; dataRow < dataSet.size(); dataRow++) {
                float[] input = new float[4];
                String[] row = dataSet.get(dataRow);
                input[0] = Float.parseFloat(row[0]);
                input[1] = Float.parseFloat(row[1]);
                input[2] = Float.parseFloat(row[2]);
                input[3] = Float.parseFloat(row[3]);
                inputs[dataRow - 100] = new Vector(input);
            }
        }
        return inputs;
    }

    public static Vector[] getTestingTargets() throws IOException, CsvException {
        Vector[] targets;
        try (CSVReader reader = new CSVReader(new FileReader("/Users/daniellee/Desktop/VNN/src/main/java/TrainingData/iris.csv"))) {
            List<String[]> dataSet = reader.readAll();
            targets = new Vector[50];
            for(int dataRow = 100; dataRow < dataSet.size(); dataRow++) {
                float[] target = new float[3];
                String[] row = dataSet.get(dataRow);
                if(row[4].equals("setosa")) {
                    target[0] = 1f;
                    target[1] = 0f;
                    target[2] = 0f;
                } else if(row[4].equals("versicolor")) {
                    target[0] = 0f;
                    target[1] = 1f;
                    target[2] = 0f;
                } else {
                    target[0] = 0f;
                    target[1] = 0f;
                    target[2] = 1f;
                }
                targets[dataRow - 100] = new Vector(target);
            }
        }
        return targets;
    }

    public static Vector[] getInputs() throws IOException, CsvException {
        Vector[] inputs;
        try (CSVReader reader = new CSVReader(new FileReader("/Users/daniellee/Desktop/NeuralNetwork/src/main/java/dataset/iris.csv"))) {
            List<String[]> dataSet = reader.readAll();
            inputs = new Vector[dataSet.size()];
            for(int dataRow = 0; dataRow < dataSet.size(); dataRow++) {
                float[] input = new float[4];
                String[] row = dataSet.get(dataRow);
                input[0] = Float.parseFloat(row[0]);
                input[1] = Float.parseFloat(row[1]);
                input[2] = Float.parseFloat(row[2]);
                input[3] = Float.parseFloat(row[3]);
                inputs[dataRow] = new Vector(input);
            }
        }
        return inputs;
    }

    public static Vector[] getTargets() throws IOException, CsvException {
        Vector[] targets;
        try (CSVReader reader = new CSVReader(new FileReader("/Users/daniellee/Desktop/NeuralNetwork/src/main/java/dataset/iris.csv"))) {
            List<String[]> dataSet = reader.readAll();
            targets = new Vector[dataSet.size()];
            for(int dataRow = 0; dataRow < dataSet.size(); dataRow++) {
                float[] target = new float[3];
                String[] row = dataSet.get(dataRow);
                if(row[4].equals("setosa")) {
                    target[0] = 1f;
                    target[1] = 0f;
                    target[2] = 0f;
                } else if(row[4].equals("versicolor")) {
                    target[0] = 0f;
                    target[1] = 1f;
                    target[2] = 0f;
                } else {
                    target[0] = 0f;
                    target[1] = 0f;
                    target[2] = 1f;
                }
                targets[dataRow] = new Vector(target);
            }
        }
        return targets;
    }

    private static void shuffleArray(float[][] array) {
        Random rand = new Random();

        for (int i = 0; i < array.length; i++) {
            int randomIndexToSwap = rand.nextInt(array.length);
            float[] temp = array[randomIndexToSwap];
            array[randomIndexToSwap] = array[i];
            array[i] = temp;
        }
    }

    private static void normalizeData(float[][] data) {
        float[] dataMin = {Float.MAX_VALUE, Float.MAX_VALUE, Float.MAX_VALUE, Float.MAX_VALUE};
        float[] dataMax = {Float.MIN_VALUE, Float.MIN_VALUE, Float.MIN_VALUE, Float.MIN_VALUE};
        for(int datarow = 0; datarow < data.length; datarow++) {
            for(int datapoint = 0; datapoint < data[datarow].length; datapoint++) {
                if(data[datarow][datapoint] < dataMin[datapoint]) {
                    dataMin[datapoint] = data[datarow][datapoint];
                }
                if(data[datarow][datapoint] > dataMax[datapoint]) {
                    dataMax[datapoint] = data[datarow][datapoint];
                }
            }
        }
        for(int datarow = 0; datarow < data.length; datarow++) {
            for(int datapoint = 0; datapoint < data[datarow].length; datapoint++) {
                data[datarow][datapoint] = (data[datarow][datapoint] - dataMin[datapoint]) / (dataMax[datapoint] - dataMin[datapoint]);
            }
        }
    }
}