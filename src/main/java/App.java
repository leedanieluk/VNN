import TrainingData.TrainingData;
import VNN.VNN;
import VNN.Vector;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException, CsvException {
        VNN vnn = new VNN(4, 10, 3);
        Vector[] inputs = TrainingData.getInputs();
        Vector[] targets = TrainingData.getTargets();
        vnn.train(inputs, targets, 1, 10000,  0.5f);
        System.out.println();
        System.out.println("[NEURAL NETWORK]");
        System.out.println(vnn);
        System.out.println();
        System.out.println("[INPUTS]");
        System.out.println(vnn.predict(inputs[0]));
        System.out.println(vnn.predict(inputs[1]));
        System.out.println(vnn.predict(inputs[2]));
        System.out.println(vnn.predict(inputs[3]));
        System.out.println();
        System.out.println("[TARGETS]");
        System.out.println(targets[0]);
        System.out.println(targets[1]);
        System.out.println(targets[2]);
        System.out.println(targets[3]);
    }
}
