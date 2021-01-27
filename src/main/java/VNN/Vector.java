package VNN;

import java.util.Arrays;
import java.util.function.Function;

public class Vector {
    private final float[] values;

    public Vector(float... values) {
        this.values = values;
    }

    public Matrix multiply(Vector vector) {
        float[] inputValues = vector.getValues();
        float[][] outputValues = new float[this.values.length][inputValues.length];
        for(int row = 0; row < this.values.length; row++) {
            for(int col = 0; col < inputValues.length; col++) {
                outputValues[row][col] = this.values[row] * inputValues[col];
            }
        }
        return new Matrix(outputValues);
    }

    public Vector add(Vector vector) {
        float[] inputValues = vector.getValues();
        for(int value = 0; value < values.length; value++) {
            values[value] += inputValues[value];
        }
        return this;
    }

    public Vector subtract(Vector vector) {
        float[] inputValues = vector.getValues();
        for(int value = 0; value < values.length; value++) {
            values[value] -= inputValues[value];
        }
        return this;
    }

    public Vector scalar(float scalar) {
        for(int value = 0; value < values.length; value++) {
            values[value] *= scalar;
        }
        return this;
    }

    public Vector hadamard(Vector vector) {
        float[] inputValues = vector.getValues();
        for(int value = 0; value < values.length; value++) {
            values[value] *= inputValues[value];
        }
        return this;
    }

    public float sumOfElements() {
        float sum = 0;
        for (float value : values) {
            sum += value;
        }
        return sum;
    }

    public Vector applyElementWise(Function<Vector, Vector> function) {
        return function.apply(this);
    }

    public static Vector buildVector(int values) {
        float[] vector = new float[values];
        for(int value = 0; value < values; value++) {
            vector[value] = 0f;
        }
        return new Vector(vector);
    }

    public float[] getValues() {
        return values;
    }

    @Override
    public String toString() {
        return "Vector{" +
                "values=" + Arrays.toString(values) +
                '}';
    }
}
