import org.eclipse.jdt.annotation.NonNull;

public class Linear {

    private static boolean dimError(Vector A, Vector B) {
        return A.getDim() != B.getDim();
    }

    private static boolean dimError(Matrix A, Matrix B) {
        return A.getDimRow() != B.getDimRow() || A.getDimCol() != B.getDimCol();
    }

    public static @NonNull Vector addition(Vector A, Vector B) {
        if (dimError(A, B)) {
            throw new RuntimeException("Error in Dimensions");
        }
        double[] Carr = new double[A.getDim()];
        for (int i = 0; i < A.getDim(); i++) {
            Carr[i] = A.getIntAt(i) + B.getIntAt(i);
        }
        return new Vector(Carr);
    }

    public static @NonNull Vector subtract(Vector A, Vector B) {
        if (dimError(A, B)) {
            throw new RuntimeException("Error in Dimensions");
        }
        double[] Carr = new double[A.getDim()];
        for (int i = 0; i < A.getDim(); i++) {
            Carr[i] = A.getIntAt(i) - B.getIntAt(i);
        }
        return new Vector(Carr);
    }

    public static double product(Vector A, Vector B) {
        if (dimError(A, B)) {
            throw new RuntimeException("Error in Dimensions");
        }
        double product = 0;
        for (int i = 0; i < A.getDim(); i++) {
            product += A.getIntAt(i) * B.getIntAt(i);
        }
        return product;
    }

    public static double lenOfVector(Vector A) {
        return Math.sqrt(product(A, A));
    }

    public static double distance(Vector A, Vector B) {
        return lenOfVector(subtract(A, B));
    }

    public static @NonNull Vector scalarMultiplication(Vector A, double n) {
        double[] R = new double[A.getDim()];
        for (int i = 0; i < A.getDim(); i++) {
            R[i] = A.getIntAt(i) * n;
        }
        return new Vector(R);
    }

    public static Vector projection(Vector A, Vector B) {
        return scalarMultiplication(B, product(A, B) / (lenOfVector(B) * lenOfVector(B)));
    }

    public static Double cosOfDotProduct(Vector A, Vector B) {
        return product(A, B) / (lenOfVector(A) * lenOfVector(B));
    }

    public static Double angleOfDotProduct(Vector A, Vector B) {
        return Math.acos(cosOfDotProduct(A, B));

    }

    //MATRIX
    public static Matrix addition(Matrix A, Matrix B) {
        if (dimError(A, B)) {
            throw new RuntimeException("Error in Dimensions");
        }
        double[][] Carr = new double[A.getDimRow()][A.getDimCol()];
        for (int i = 0; i < A.getDimRow(); i++) {
            for (int j = 0; j < A.getDimCol(); j++) {
                Carr[i][j] = A.getIntAt(i, j) + B.getIntAt(i, j);
            }
        }
        return new Matrix(Carr);
    }

    public static Matrix subtract(Matrix A, Matrix B) {
        if (dimError(A, B)) {
            throw new RuntimeException("Error in Dimensions");
        }
        double[][] Carr = new double[A.getDimRow()][A.getDimCol()];
        for (int i = 0; i < A.getDimRow(); i++) {
            for (int j = 0; j < A.getDimCol(); j++) {
                Carr[i][j] = A.getIntAt(i, j) - B.getIntAt(i, j);
            }
        }
        return new Matrix(Carr);
    }

    public static @NonNull Matrix scalarMultiplication(Matrix A, double n) {
        double[][] R = new double[A.getDimRow()][A.getDimCol()];
        for (int i = 0; i < A.getDimRow(); i++) {
            for (int j = 0; j < A.getDimCol(); j++) {
                R[i][j] = A.getIntAt(i, j) * n;
            }

        }
        return new Matrix(R);
    }

    public static Matrix transpose(Matrix A) {
        double[][] mat = new double[A.getDimCol()][A.getDimRow()];
        for (int i = 0; i < A.getDimRow(); i++) {
            for (int j = 0; j < A.getDimCol(); j++) {
                mat[j][i] = A.getIntAt(i, j);
            }
        }
        return new Matrix(mat);
    }

    public static double[][] vectorTo2DArray(Vector A) {
        double[][] mat = new double[A.getDim()][1];
        for (int i = 0; i < A.getDim(); i++) {
            mat[i][0] = A.getIntAt(i);
        }
        return mat;
    }

    public static Matrix vectorToMatrix(Vector A) {

        return new Matrix(vectorTo2DArray(A));
    }

    public static Matrix product(Matrix A, Matrix B) {
        if (A.getDimCol() != B.getDimRow()) throw new RuntimeException("Error in Dimensions");
        double[][] product = new double[A.getDimRow()][B.getDimCol()];
        for (int i = 0; i < A.getDimRow(); i++) {
            Vector a = A.getVectorRow(i);
            for (int j = 0; j < B.getDimCol(); j++) {
                Vector b = B.getVectorCol(j);
                product[i][j] = product(a, b);
            }
        }
        return new Matrix(product);
    }

    public static boolean isSquare(Matrix A) {
        return A.getDimCol() == A.getDimRow();
    }

    public static double determinant(Matrix A) {
        if (isSquare(A)) return determinant(A.getMatrix(), A.getDimRow(), A.getDimCol());
        throw new RuntimeException("Matrix isn't Square");
    }

    public static double determinant(double[][] A, int rows, int cols) {
        double det = 0;
        if (rows == 2) return A[0][0] * A[1][1] - A[0][1] * A[1][0];
        for (int j = 0; j < cols; j++) {
            int sign;
            if (j % 2 == 0) sign = 1;
            else sign = -1;
            double[][] B = new double[rows - 1][cols - 1];
            int i1 = 0;
            int j1;
            for (int i0 = 1; i0 < rows; i0++) {
                j1 = 0;
                for (int j0 = 0; j0 < cols; j0++) {
                    if (j != j0) {
                        B[i1][j1] = A[i0][j0];
                        j1++;
                    }
                }
                i1++;
            }
            double temp = A[0][j] * determinant(B, rows - 1, cols - 1);
            if (temp != 0) {
                temp *= sign;
            } else temp = 0;
            det = det + temp;
        }
        return det;
    }

    public static Matrix adjoint(Matrix A) {
        if (!isSquare(A)) throw new RuntimeException("Matrix isn't Square");
        double[][] mat = new double[A.getDimRow()][A.getDimCol()];
        if (A.getDimRow() == 2) {
            mat[0][0] = A.getIntAt(1, 1);
            mat[0][1] = A.getIntAt(0, 1) * -1;
            mat[1][0] = A.getIntAt(1, 0) * -1;
            mat[1][1] = A.getIntAt(0, 0);
            return new Matrix(mat).antiZero();
        }
        for (int i = 0; i < A.getDimRow(); i++) {
            for (int j = 0; j < A.getDimCol(); j++) {
                int sign;
                if ((i + j) % 2 == 0) sign = 1;
                else sign = -1;
                double[][] B = new double[A.getDimRow() - 1][A.getDimCol() - 1];
                int i1 = 0;
                int j1;
                for (int i0 = 0; i0 < A.getDimRow(); i0++) {
                    j1 = 0;
                    if (i != i0) {
                        for (int j0 = 0; j0 < A.getDimCol(); j0++) {
                            if (j != j0) {
                                B[i1][j1] = A.getMatrix()[i0][j0];
                                j1++;
                            }
                        }
                        i1++;
                    }
                }

                mat[i][j] = sign * determinant(B, A.getDimRow() - 1, A.getDimCol() - 1);
            }
        }
        return Linear.transpose(new Matrix(mat).antiZero());
    }

    public static boolean isSingular(Matrix A) {
        return determinant(A) == 0;
    }

    public static Matrix inverse(Matrix A) {
        if (isSingular(A)) throw new RuntimeException("Singular Matrix");
        return scalarMultiplication(adjoint(A), Math.pow(determinant(A), -1)).antiZero();
    }

    public static Matrix projectionMatrix(Matrix A) {
        return product(product(A, inverse(product(transpose(A), A))), transpose(A));
    }

    public static Matrix projection(Vector Y, Matrix A) {
        return product(projectionMatrix(A), Linear.vectorToMatrix(Y));
    }

    public static double trace(Matrix A) {
        if (isSquare(A)) {
            double trace = 0;
            for (int i = 0; i < A.getDimRow(); i++) {
                trace += A.getIntAt(i, i);
            }
            return trace;
        }
        return 0;
    }

    public static boolean areEqual(Matrix A, Matrix B) {
        if (dimError(A, B)) return false;
        for (int i = 0; i < A.getDimRow(); i++) {
            for (int j = 0; j < A.getDimCol(); j++) {
                if (A.getIntAt(i, j) != B.getIntAt(i, j)) return false;
            }
        }
        return true;
    }

    public static boolean isSymmetric(Matrix A) {
        if (isSquare(A)) {
            for (int i = 0; i < A.getDimRow(); i++) {
                for (int j = 0; j < A.getDimCol(); j++) {
                    if (A.getIntAt(i, j) != A.getIntAt(j, i)) return false;
                }
            }
            return true;
        }
        return false;
    }

    public static boolean isSkewSymmetric(Matrix A) {
        if (isSquare(A)) {
            for (int i = 0; i < A.getDimRow(); i++) {
                for (int j = 0; j < A.getDimCol(); j++) {
                    if (A.getIntAt(i, j) != -1 * A.getIntAt(j, i)) return false;
                }
            }
            return true;
        }
        return false;
    }

    public static boolean isOrthogonal(Matrix A) {
        return areEqual(transpose(A), inverse(A));
    }

    public static boolean isNormal(Matrix A) {
        return areEqual(product(A, transpose(A)), (product(transpose(A), A)));
    }


}
