package server;

import VNN.VNN;
import VNN.Vector;

public class App {
    public static void main(String[] args) {
        VNN vnn = new VNN(2, 3,2);
        float[] inputValues = {0.2f, 0.7f};
        Vector input = new Vector(inputValues);
        Vector[] inputs = {input};
        float[] outputValues = {0.01f, 0.99f};
        Vector output = new Vector(outputValues);
        Vector[] outputs = {output};
        vnn.train(inputs, outputs, 1, 1, 20f);
        vnn.predict(input);
    }
}
