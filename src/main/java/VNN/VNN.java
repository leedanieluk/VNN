package VNN;

import java.util.Arrays;

import static VNN.Matrix.buildMatrix;
import static VNN.Matrix.buildRandomMatrix;
import static VNN.Vector.buildVector;

/**
 * Vectorized Neural Network
 *
 * Equations based on: http://neuralnetworksanddeeplearning.com/chap2.html
 */
public class VNN {
    private final int layersSize;
    private final int[] layers;
    private final Matrix[] wMatrices;
    private final Vector[] bVectors;
    private final Vector[] zVectors;
    private final Vector[] aVectors;
    private final Vector[] dVectors;
    private Vector[][] dVectorsCache;
    private Vector[][] aVectorsCache;
    private int inputNumber;
    private int inputSize;

    public VNN(int inputs, int... layers) {
        validateVNNInputs(inputs, layers);
        Matrix[] wMatrices = new Matrix[layers.length];
        Vector[] bVectors = new Vector[layers.length];
        Vector[] zVectors = new Vector[layers.length];
        Vector[] aVectors = new Vector[layers.length];
        Vector[] dVectors = new Vector[layers.length];
        for(int layer = 0; layer < layers.length; layer++) {
            int previousLayerSize = layer > 0 ? layers[layer - 1] : inputs;
            wMatrices[layer] = buildRandomMatrix(layers[layer], previousLayerSize);
            bVectors[layer] = buildVector(layers[layer]);
            zVectors[layer] = buildVector(layers[layer]);
            aVectors[layer] = buildVector(layers[layer]);
            dVectors[layer] = buildVector(layers[layer]);
        }
        this.layersSize = layers.length;
        this.layers = layers;
        this.wMatrices = wMatrices;
        this.bVectors = bVectors;
        this.zVectors = zVectors;
        this.aVectors = aVectors;
        this.dVectors = dVectors;
    }

    public void train(Vector[] inputs, Vector[] targets, int batchSize, int iterations, float learningRate) {
        validateTrainingInputs(targets);
        this.dVectorsCache = new Vector[inputs.length][layersSize];
        this.aVectorsCache = new Vector[inputs.length][layersSize];
        this.inputSize = inputs.length;
        for(int iteration = 0; iteration < iterations; iteration++) {
            float batchError = 0;
            for(int input = 0; input < inputs.length; input++) {
                this.inputNumber = input;
                Vector prediction = feedForward(inputs[input]);
                backPropagation(targets[input]);
                batchError += calculateError(prediction, targets[input]);
            }
            updateWeightsAndBiases(inputs, learningRate);
            System.out.printf("Iteration %d - Error %f\n", iteration + 1, batchError);
        }
    }

    public Vector predict(Vector input) {
        return feedForward(input);
    }

    private Vector feedForward(Vector inputs) {
        for(int layer = 0; layer < layersSize; layer++) {
            Matrix wMatrix = wMatrices[layer];
            Vector input = layer > 0 ? aVectors[layer - 1] : inputs;
            Vector zVector = wMatrix.multiply(input).add(bVectors[layer]);
            zVectors[layer] = zVector;
            aVectors[layer] = zVector.applyElementWise(ActivationFunctions::sigmoid);
            aVectorsCache[inputNumber][layer] = new Vector(aVectors[layer].getValues().clone());
        }
        return aVectors[layersSize - 1];
    }

    private void backPropagation(Vector target) {
        for(int layer = layersSize - 1; layer >= 0; layer--) {
            dVectors[layer] = layer < layersSize - 1 ?
                    (wMatrices[layer + 1].transpose().multiply(dVectors[layer + 1])).hadamard(zVectors[layer].applyElementWise(ActivationFunctions::sigmoidDerivative)) :
                    (new Vector(aVectors[layer].getValues().clone()).subtract(target)).hadamard(zVectors[layer].applyElementWise(ActivationFunctions::sigmoidDerivative));
            dVectorsCache[inputNumber][layer] = dVectors[layer];
        }
    }

    private void updateWeightsAndBiases(Vector[] inputs, float learningRate) {
        for(int layer = 0; layer < layersSize; layer++) {
            Matrix dwMatrix = buildMatrix(wMatrices[layer].getValues().length, wMatrices[layer].getValues()[0].length);
            Vector dbVector = buildVector(bVectors[layer].getValues().length);
            for (int input = 0; input < inputs.length; input++) {
                if(layer > 0) {
                    dwMatrix.add(dVectorsCache[input][layer].multiply(aVectorsCache[input][layer - 1]));
                } else {
                    dwMatrix.add(dVectorsCache[input][layer].multiply(inputs[input]));
                }
                dbVector.add(dVectorsCache[input][layer]);
            }
            wMatrices[layer].subtract(dwMatrix.scalar(learningRate / inputSize));
            bVectors[layer].subtract(dbVector.scalar(learningRate / inputSize));
        }
    }

    private float calculateError(Vector prediction, Vector target) {
        Vector predictionClone = new Vector(prediction.getValues().clone());
        Vector result = predictionClone.subtract(target);
        return 0.5f * (float) Math.pow(result.sumOfElements(), 2);
    }

    private void validateVNNInputs(int inputs, int... layers) {
        if(inputs < 1) {
            throw new IllegalArgumentException("Input size should be bigger than 0.");
        }
        if(layers.length < 1) {
            throw new IllegalArgumentException("Layers size should be bigger than 1");
        }
    }

    private void validateTrainingInputs(Vector[] targets) {
        if(targets[0].getValues().length != layers[layersSize - 1]) {
            throw new IllegalArgumentException("Output size should match final layer size.");
        }
    }

    public Matrix[] getWMatrices() {
        return wMatrices;
    }

    public Vector[] getBVectors() {
        return bVectors;
    }

    public Vector[] getZVectors() {
        return zVectors;
    }

    public Vector[] getAVectors() {
        return aVectors;
    }

    @Override
    public String toString() {
        return "VNN{" +
                "layersSize=" + layersSize +
                ",\nwMatrices=" + Arrays.deepToString(wMatrices) +
                ",\nbVectors=" + Arrays.toString(bVectors) +
                '}';
    }
}
