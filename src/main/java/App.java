import TrainingData.TrainingData;
import VNN.VNN;
import VNN.Vector;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.util.Arrays;

public class App {
    public static void main(String[] args) throws IOException, CsvException {
        VNN vnn = new VNN(4, 10, 3);
        Vector[] inputs = TrainingData.getTrainingSet();
        Vector[] targets = TrainingData.getTrainingTargets();
        Vector[] testInputs = TrainingData.getTestingSet();
        Vector[] testTargets = TrainingData.getTestingTargets();
        vnn.train(inputs, targets, 1, 1000,  0.5f);
        int correct = 0;
        for(int input = 0; input < testInputs.length; input++) {
            int target = getIndexOfLargest(testTargets[input].getValues());
            int prediction = getIndexOfLargest(vnn.predict(testInputs[input]).getValues());
            if(target == prediction) {
                correct++;
            } else {
                System.out.println("Incorrect");
                System.out.println(testInputs[input]);
                System.out.println(Arrays.toString(vnn.predict(testInputs[input]).getValues()));
                System.out.println(testTargets[input]);
            }
        }
        System.out.println(correct);
        System.out.printf("Accuracy: %f\n", (float) correct / 50);
    }

    public static int getIndexOfLargest( float[] array )
    {
        if ( array == null || array.length == 0 ) return -1; // null or empty
        int largest = 0;
        for ( int i = 1; i < array.length; i++ )
        {
            if ( array[i] > array[largest] ) largest = i;
        }
        return largest; // position of the first largest found
    }
}
