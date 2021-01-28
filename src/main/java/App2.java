import TrainingData.DataParser;
import VNN.VNN;
import VNN.Vector;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;

public class App2 {
    public static void main(String[] args) throws IOException, CsvException {
        VNN vnn = new VNN(100, 66, 66, 1);
        DataParser dataParser = new DataParser("/Users/daniellee/Desktop/VNN/src/main/java/TrainingData/smiley.txt");
        vnn.train(dataParser.getTrainingData(), dataParser.getTargets(), 1, 1000, 10f);
        Vector prediction = vnn.predict(dataParser.getSingleSample());
        if(prediction.getValues()[0] > 0.5) {
            System.out.println("YOUR SMILEY IS HAPPY! :)");
        } else {
            System.out.println("YOUR SMILEY IS SAD... :(");
        }
    }
}
