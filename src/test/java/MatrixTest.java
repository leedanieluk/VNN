import VNN.Matrix;
import VNN.Vector;
import org.junit.Assert;
import org.junit.Test;

public class MatrixTest {
    @Test
    public void testAddition() {
        float[][] matrixValues1 = {{0, 1}, {1, 2}};
        Matrix matrix1 = new Matrix(matrixValues1);
        float[][] matrixValues2 = {{2, -1}, {3, 4}};
        Matrix matrix2 = new Matrix(matrixValues2);
        matrix1.add(matrix2);
        Assert.assertEquals(2, (int) matrix1.getValues()[0][0]);
        Assert.assertEquals(0, (int) matrix1.getValues()[0][1]);
        Assert.assertEquals(4, (int) matrix1.getValues()[1][0]);
        Assert.assertEquals(6, (int) matrix1.getValues()[1][1]);
    }

    @Test
    public void testAdditionNonSquareMatrix() {
        float[][] matrixValues1 = {{0, 1}, {1, 2}, {3, -1}};
        Matrix matrix1 = new Matrix(matrixValues1);
        float[][] matrixValues2 = {{2, -1}, {3, 4}, {0, 1}};
        Matrix matrix2 = new Matrix(matrixValues2);
        matrix1.add(matrix2);
        Assert.assertEquals(2, (int) matrix1.getValues()[0][0]);
        Assert.assertEquals(0, (int) matrix1.getValues()[0][1]);
        Assert.assertEquals(4, (int) matrix1.getValues()[1][0]);
        Assert.assertEquals(6, (int) matrix1.getValues()[1][1]);
        Assert.assertEquals(3, (int) matrix1.getValues()[2][0]);
        Assert.assertEquals(0, (int) matrix1.getValues()[2][1]);
    }

    @Test
    public void testSubtract() {
        float[][] matrixValues1 = {{0, 1}, {1, 2}};
        Matrix matrix1 = new Matrix(matrixValues1);
        float[][] matrixValues2 = {{2, -1}, {3, 4}};
        Matrix matrix2 = new Matrix(matrixValues2);
        matrix1.subtract(matrix2);
        Assert.assertEquals(-2, (int) matrix1.getValues()[0][0]);
        Assert.assertEquals(2, (int) matrix1.getValues()[0][1]);
        Assert.assertEquals(-2, (int) matrix1.getValues()[1][0]);
        Assert.assertEquals(-2, (int) matrix1.getValues()[1][1]);
    }


    @Test
    public void testMultiplicationWithSquareMatrix() {
        float[][] matrixValues = {{0, 1}, {1, 2}};
        Matrix matrix = new Matrix(matrixValues);
        float[] vectorValues = {-2, 3};
        Vector vector = new Vector(vectorValues);
        Vector result = matrix.multiply(vector);
        Assert.assertEquals(2, result.getValues().length);
        Assert.assertEquals(3, (int) result.getValues()[0]);
        Assert.assertEquals(4, (int) result.getValues()[1]);
    }

    @Test
    public void testMultiplicationWithRectangularMatrix() {
        float[][] matrixValues = {{0, 1}, {1, 2}, {-1, 2}};
        Matrix matrix = new Matrix(matrixValues);
        float[] vectorValues = {1, -3};
        Vector vector = new Vector(vectorValues);
        Vector result = matrix.multiply(vector);
        Assert.assertEquals(3, result.getValues().length);
        Assert.assertEquals(-3, (int) result.getValues()[0]);
        Assert.assertEquals(-5, (int) result.getValues()[1]);
        Assert.assertEquals(-7, (int) result.getValues()[2]);
    }

    @Test
    public void testTranspose() {
        float[][] matrixValues = {{0, 1}, {1, 2}, {-1, 2}};
        Matrix matrix = new Matrix(matrixValues);
        Matrix tMatrix = matrix.transpose();
        Assert.assertEquals(0, (int) tMatrix.getValues()[0][0]);
        Assert.assertEquals(1, (int) tMatrix.getValues()[0][1]);
        Assert.assertEquals(-1, (int) tMatrix.getValues()[0][2]);
        Assert.assertEquals(1, (int) tMatrix.getValues()[1][0]);
        Assert.assertEquals(2, (int) tMatrix.getValues()[1][1]);
        Assert.assertEquals(2, (int) tMatrix.getValues()[1][2]);
        Assert.assertEquals(0, (int) matrix.getValues()[0][0]);
        Assert.assertEquals(1, (int) matrix.getValues()[0][1]);
        Assert.assertEquals(1, (int) matrix.getValues()[1][0]);
        Assert.assertEquals(2, (int) matrix.getValues()[1][1]);
        Assert.assertEquals(-1, (int) matrix.getValues()[2][0]);
        Assert.assertEquals(2, (int) matrix.getValues()[2][1]);
    }

    @Test
    public void testTransposeSquareMatrix() {
        float[][] matrixValues = {{3, 2}, {5, 2}};
        Matrix matrix = new Matrix(matrixValues);
        Matrix tMatrix = matrix.transpose();
        Assert.assertEquals(3, (int) tMatrix.getValues()[0][0]);
        Assert.assertEquals(5, (int) tMatrix.getValues()[0][1]);
        Assert.assertEquals(2, (int) tMatrix.getValues()[1][0]);
        Assert.assertEquals(2, (int) tMatrix.getValues()[1][1]);
        Assert.assertEquals(3, (int) matrix.getValues()[0][0]);
        Assert.assertEquals(2, (int) matrix.getValues()[0][1]);
        Assert.assertEquals(5, (int) matrix.getValues()[1][0]);
        Assert.assertEquals(2, (int) matrix.getValues()[1][1]);
    }
}
