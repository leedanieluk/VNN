import VNN.Matrix;
import VNN.VNN;
import VNN.Vector;
import org.junit.Assert;
import org.junit.Test;

public class VNNTest {

    @Test
    public void wMatricesSizes() {
        VNN vnn = new VNN(4, 5, 5, 3);
        Matrix[] matrices = vnn.getWMatrices();
        Assert.assertEquals(3, matrices.length);
        Assert.assertEquals(matrices[0].getValues().length, 5);
        Assert.assertEquals(matrices[0].getValues()[0].length, 4);
        Assert.assertEquals(matrices[1].getValues().length, 5);
        Assert.assertEquals(matrices[1].getValues()[0].length, 5);
        Assert.assertEquals(matrices[2].getValues().length, 3);
        Assert.assertEquals(matrices[2].getValues()[0].length, 5);
    }

    @Test
    public void bVectorsSizes() {
        VNN vnn = new VNN(1, 3, 2);
        Vector[] vectors = vnn.getBVectors();
        Assert.assertEquals(2, vectors.length);
        Assert.assertEquals(3, vectors[0].getValues().length);
        Assert.assertEquals(2, vectors[1].getValues().length);
    }

    @Test
    public void zVectorSize() {
        VNN vnn = new VNN(4, 3, 4);
        Assert.assertEquals(2, vnn.getZVectors().length);
    }

    @Test
    public void aVectorSize() {
        VNN vnn = new VNN(4, 3, 2, 1, 3);
        Assert.assertEquals(4, vnn.getAVectors().length);
    }

    @Test
    public void zeroInputsThrowsException() {
        Assert.assertThrows(IllegalArgumentException.class, () -> new VNN(0, 4, 4, 2));
    }

    @Test
    public void zeroLayersThrowsException() {
        Assert.assertThrows(IllegalArgumentException.class, () -> new VNN(0));
    }

//    @Test
//    public void validateFeedForwardResult() {
//        VNN vnn = new VNN(2, 2, 3);
//        float[][] weightValuesLayer1 = {{0, 1}, {2, 3}};
//        float[][] weightValuesLayer2 = {{4, 5}, {6, 7}, {8, 9}};
//        Matrix[] weightsMatrix = {new Matrix(weightValuesLayer1), new Matrix(weightValuesLayer2)};
//        vnn.setWMatrices(weightsMatrix);
//        float[] biasVectorLayer1 = {1, 1};
//        float[] biasVectorLayer2 = {2, 2, 2};
//        Vector[] biasVectors = {new Vector(biasVectorLayer1), new Vector(biasVectorLayer2)};
//        vnn.setBVectors(biasVectors);
//        float[] inputs = {-1, 1};
//        vnn.feedForward(new Vector(inputs));
//        Assert.assertEquals((int) vnn.getZVectors()[0].getValues()[0], 2);
//        Assert.assertEquals((int) vnn.getZVectors()[0].getValues()[1], 2);
//        Assert.assertTrue(vnn.getZVectors()[1].getValues()[0] - (sigmoid(2) * 4 + sigmoid(2) * 5 + 2) < 0.0001f);
//        Assert.assertTrue(vnn.getZVectors()[1].getValues()[1] - (sigmoid(2) * 6 + sigmoid(2) * 7 + 2) < 0.0001f);
//        Assert.assertTrue(vnn.getZVectors()[1].getValues()[2] - (sigmoid(2) * 8 + sigmoid(2) * 9 + 2) < 0.0001f);
//    }

    private float sigmoid(float x) {
        return 1f / (1f + (float) Math.pow(Math.E, -x));
    }
}
