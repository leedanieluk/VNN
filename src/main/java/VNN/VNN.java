package VNN;

import java.util.Arrays;

/**
 * Vectorized Neural Network
 */
public class VNN {
    private final int layers;
    private Matrix[] wMatrices;
    private Vector[] bVectors;
    private final Vector[] zVectors;
    private final Vector[] aVectors;
    private final Vector[] dVectors;
    private Vector[][] dVectorsCache;
    private Vector[][] aVectorsCache;
    private int inputNumber;
    private int inputSize;

    public VNN(int inputs, int... layers) {
        validateInputs(inputs, layers);
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
        this.layers = layers.length;
        this.wMatrices = wMatrices;
        this.bVectors = bVectors;
        this.zVectors = zVectors;
        this.aVectors = aVectors;
        this.dVectors = dVectors;
    }

    public void train(Vector[] inputs, Vector[] targets, int batchSize, int iterations, float learningRate) {
        this.dVectorsCache = new Vector[inputs.length][layers];
        this.aVectorsCache = new Vector[inputs.length][layers];
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
            System.out.printf("Iteration %d - Error %f\n", iteration, batchError);
        }
    }

    public Vector predict(Vector input) {
        return feedForward(input);
    }

    private Vector feedForward(Vector inputs) {
        for(int layer = 0; layer < layers; layer++) {
            Matrix wMatrix = wMatrices[layer];
            Vector input = layer > 0 ? aVectors[layer - 1] : inputs;
            Vector zVector = wMatrix.multiply(input).add(bVectors[layer]);
            zVectors[layer] = zVector;
            aVectors[layer] = zVector.applyElementWise(ActivationFunctions::sigmoid);
            aVectorsCache[inputNumber][layer] = new Vector(aVectors[layer].getValues().clone());
        }
        return aVectors[layers - 1];
    }

    private void backPropagation(Vector target) {
        for(int layer = layers - 1; layer >= 0; layer--) {
            dVectors[layer] = layer < layers - 1 ?
                    (wMatrices[layer + 1].transpose().multiply(dVectors[layer + 1])).hadamard(zVectors[layer].applyElementWise(ActivationFunctions::sigmoidDerivative)) :
                    (new Vector(aVectors[layer].getValues().clone()).subtract(target)).hadamard(zVectors[layer].applyElementWise(ActivationFunctions::sigmoidDerivative));
            dVectorsCache[inputNumber][layer] = dVectors[layer];
        }
    }

    public void updateWeightsAndBiases(Vector[] inputs, float learningRate) {
        for(int layer = 0; layer < layers; layer++) {
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

    private Matrix buildRandomMatrix(int rows, int columns) {
        float[][] values = new float[rows][columns];
        for(int row = 0; row < rows; row++) {
            for(int column = 0; column < columns; column++) {
                values[row][column] = (float) Math.random();
            }
        }
        return new Matrix(values);
    }

    private Matrix buildMatrix(int rows, int columns) {
        float[][] values = new float[rows][columns];
        for(int row = 0; row < rows; row++) {
            for(int column = 0; column < columns; column++) {
                values[row][column] = 0f;
            }
        }
        return new Matrix(values);
    }

    private Vector buildVector(int values) {
        float[] vector = new float[values];
        for(int value = 0; value < values; value++) {
            vector[value] = 0f;
        }
        return new Vector(vector);
    }

    private float calculateError(Vector prediction, Vector target) {
        Vector predictionClone = new Vector(prediction.getValues().clone());
        Vector result = predictionClone.subtract(target);
        return 0.5f * (float) Math.pow(result.sumOfElements(), 2);
    }

    private void validateInputs(int inputs, int... layers) {
        if(inputs < 1) {
            throw new IllegalArgumentException("Input size should be bigger than 0.");
        }

        if(layers.length < 1) {
            throw new IllegalArgumentException("Layers size should be bigger than 1");
        }
    }

    public Matrix[] getWMatrices() {
        return wMatrices;
    }

    public void setWMatrices(Matrix[] wMatrices) {
        this.wMatrices = wMatrices;
    }

    public Vector[] getBVectors() {
        return bVectors;
    }

    public void setBVectors(Vector[] bVectors) {
        this.bVectors = bVectors;
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
                "layers=" + layers +
                ",\nwMatrices=" + Arrays.deepToString(wMatrices) +
                ",\nbVectors=" + Arrays.toString(bVectors) +
                ",\nzVectors=" + Arrays.toString(zVectors) +
                ",\naVectors=" + Arrays.toString(aVectors) +
                ",\ndVectors=" + Arrays.toString(dVectors) +
                ",\ndVectorsCache=" + Arrays.deepToString(dVectorsCache) +
                ",\naVectorsCache=" + Arrays.deepToString(aVectorsCache) +
                ",\ninputNumber=" + inputNumber +
                ",\ninputSize=" + inputSize +
                '}';
    }
}