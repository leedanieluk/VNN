import VNN.Matrix;
import VNN.Vector;
import org.junit.Assert;
import org.junit.Test;

public class VectorTest {
    @Test
    public void vectorAddition() {
        float[] vectorValues = {1, -2};
        Vector vector = new Vector(vectorValues);
        float[] vectorValues2 = {2, 1};
        Vector vector2 = new Vector(vectorValues2);
        Vector result = vector.add(vector2);
        Assert.assertEquals(3, (int) result.getValues()[0]);
        Assert.assertEquals(-1, (int) result.getValues()[1]);
    }

    @Test
    public void vectorSubtraction() {
        float[] vectorValues = {3, -1};
        Vector vector = new Vector(vectorValues);
        float[] vectorValues2 = {0, 2};
        Vector vector2 = new Vector(vectorValues2);
        Vector result = vector.subtract(vector2);
        Assert.assertEquals(3, (int) result.getValues()[0]);
        Assert.assertEquals(-3, (int) result.getValues()[1]);
    }

    @Test
    public void vectorHadamard() {
        float[] vectorValues = {3, -1, 2};
        Vector vector = new Vector(vectorValues);
        float[] vectorValues2 = {0, 2, 3};
        Vector vector2 = new Vector(vectorValues2);
        Vector result = vector.hadamard(vector2);
        Assert.assertEquals(0, (int) result.getValues()[0]);
        Assert.assertEquals(-2, (int) result.getValues()[1]);
        Assert.assertEquals(6, (int) result.getValues()[2]);
    }

    @Test
    public void vectorMultiplication() {
        float[] vectorValues = {3, -1, 2};
        Vector vector = new Vector(vectorValues);
        float[] vectorValues2 = {0, 2, 3};
        Vector vector2 = new Vector(vectorValues2);
        Matrix result = vector.multiply(vector2);
        Assert.assertEquals(0, (int) result.getValues()[0][0]);
        Assert.assertEquals(6, (int) result.getValues()[0][1]);
        Assert.assertEquals(9, (int) result.getValues()[0][2]);
        Assert.assertEquals(0, (int) result.getValues()[1][0]);
        Assert.assertEquals(-2, (int) result.getValues()[1][1]);
        Assert.assertEquals(-3, (int) result.getValues()[1][2]);
        Assert.assertEquals(0, (int) result.getValues()[2][0]);
        Assert.assertEquals(4, (int) result.getValues()[2][1]);
        Assert.assertEquals(6, (int) result.getValues()[2][2]);
    }

    @Test
    public void vectorMultiplicationNonSquare() {
        float[] vectorValues = {3, -1, 2};
        Vector vector = new Vector(vectorValues);
        float[] vectorValues2 = {0, 2};
        Vector vector2 = new Vector(vectorValues2);
        Matrix result = vector.multiply(vector2);
        Assert.assertEquals(0, (int) result.getValues()[0][0]);
        Assert.assertEquals(6, (int) result.getValues()[0][1]);
        Assert.assertEquals(0, (int) result.getValues()[1][0]);
        Assert.assertEquals(-2, (int) result.getValues()[1][1]);
        Assert.assertEquals(0, (int) result.getValues()[2][0]);
        Assert.assertEquals(4, (int) result.getValues()[2][1]);
    }
}
