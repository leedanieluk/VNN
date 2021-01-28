package VNN;

public class ActivationFunctions {
    public static Vector sigmoid(Vector vector) {
        for(int element = 0; element < vector.getValues().length; element++) {
            vector.getValues()[element] = computeSigmoid(vector.getValues()[element]);
        }
        return vector;
    }

    public static Vector sigmoidDerivative(Vector vector) {
        for(int element = 0; element < vector.getValues().length; element++) {
            vector.getValues()[element] = computeSigmoid(vector.getValues()[element]) * (1 - computeSigmoid(vector.getValues()[element]));
        }
        return vector;
    }

    private static float computeSigmoid(float x) {
        return 1f / (1f + (float) Math.pow(Math.E, -x));
    }
}
