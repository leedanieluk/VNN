import VNN.VNN;
import VNN.Vector;
import VNN.Matrix;

public class App {
    public static void main(String[] args) {
        VNN vnn = new VNN(2, 2, 2);
        float[][] weightValuesLayer1 = {{0.15f, 0.20f}, {0.25f, 0.30f}};
        float[][] weightValuesLayer2 = {{0.40f,0.45f}, {0.50f, 0.55f}};
        Matrix[] weightsMatrix = {new Matrix(weightValuesLayer1), new Matrix(weightValuesLayer2)};
        vnn.setWMatrices(weightsMatrix);
        float[] biasVectorLayer1 = {0.35f, 0.35f};
        float[] biasVectorLayer2 = {0.60f, 0.60f};
        Vector[] biasVectors = {new Vector(biasVectorLayer1), new Vector(biasVectorLayer2)};
        vnn.setBVectors(biasVectors);

        float[] singleInput = {0.05f, 0.1f};
        float[] singleTarget = {0.01f, 0.99f};
        Vector input = new Vector(singleInput);
        Vector target = new Vector(singleTarget);
        Vector[] inputs = {input};
        Vector[] targets = {target};

        vnn.train(inputs, targets, 1, 10000,  0.5f);
        System.out.println(vnn);
        System.out.println(vnn.predict(input));
    }
}
