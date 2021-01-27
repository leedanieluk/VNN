package VNN;

import java.util.Arrays;

public class Matrix {
    private final float[][] values;
    public Matrix(float[][] values) {
        this.values = values;
    }

    public float[][] getValues() {
        return values;
    }

    public Matrix transpose() {
        float[][] values = new float[this.values[0].length][this.values.length];
        for (int row = 0; row < values.length; row++)
            for (int column = 0; column < values[0].length; column++)
                values[row][column] = this.values[column][row];
        return new Matrix(values);
    }

    public void add(Matrix input) {
        for(int row = 0; row < values.length; row++) {
            for(int col = 0; col < values[0].length; col++) {
                this.values[row][col] += input.getValues()[row][col];
            }
        }
    }

    public void subtract(Matrix input) {
        for(int row = 0; row < values.length; row++) {
            for(int col = 0; col < values[0].length; col++) {
                this.values[row][col] -= input.getValues()[row][col];
            }
        }
    }

    public Matrix scalar(float scalar) {
        for(int row = 0; row < values.length; row++) {
            for(int col = 0; col < values[0].length; col++) {
                this.values[row][col] *= scalar;
            }
        }
        return this;
    }

    public Vector multiply(Vector input) {
        float[] initValues = new float[values.length];
        Vector result = new Vector(initValues);
        for(int row = 0; row < values.length; row++) {
            float vectorElement = 0;
            for(int col = 0; col < input.getValues().length; col++) {
                vectorElement += values[row][col] * input.getValues()[col];
            }
            result.getValues()[row] = vectorElement;
        }
        return result;
    }

    @Override
    public String toString() {
        return "Matrix{" +
                "values=" + Arrays.deepToString(values) +
                '}';
    }
}
