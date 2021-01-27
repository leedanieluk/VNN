package VNN;

public class ActivationFunctions {
    public static Vector sigmoid(Vector vector) {
        float[] resultValues = new float[vector.getValues().length];
        for(int element = 0; element < vector.getValues().length; element++) {
            resultValues[element] = computeSigmoid(vector.getValues()[element]);
        }
        return new Vector(resultValues);
    }

    public static Vector sigmoidDerivative(Vector vector) {
        float[] resultValues = new float[vector.getValues().length];
        for(int element = 0; element < vector.getValues().length; element++) {
            resultValues[element] = computeSigmoid(vector.getValues()[element]) * (1 - computeSigmoid(vector.getValues()[element]));
        }
        return new Vector(resultValues);
    }

    private static float computeSigmoid(float x) {
        return 1f / (1f + (float) Math.pow(Math.E, -x));
    }
}
