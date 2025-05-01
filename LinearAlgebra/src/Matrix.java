public class Matrix {
    private final double[][] matrix;

    private int dimRow;
    private int dimCol;

    public Matrix(double[]... mat) {

        dimRow = mat.length;
        dimCol = mat[0].length;
        for (int i = 0; i < dimRow; i++) {
            if (mat[i].length > dimCol) dimCol = mat[i].length;
        }
        matrix = new double[dimRow][dimCol];
        for (int i = 0; i < dimRow; i++) {
            for (int j = 0; j < dimCol; j++) {
                try {
                    matrix[i][j] = mat[i][j];
                } catch (ArrayIndexOutOfBoundsException e) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    public Matrix(Vector... n) {
        dimCol = n.length;
        dimRow = n[0].getDim();
        for (int i = 0; i < n.length; i++) {
            if (dimRow < n[i].getDim()) dimRow = n[i].getDim();
        }
        matrix = new double[dimRow][dimCol];
        for (int i = 0; i < dimRow; i++) {
            for (int j = 0; j < dimCol; j++) {
                matrix[i][j] = n[j].getIntAt(i);
            }
        }
    }

    public int getDimCol() {
        return dimCol;
    }

    public int getDimRow() {
        return dimRow;
    }

    public void print() {
        for (int i = 0; i < dimRow; i++) {
            for (int j = 0; j < dimCol; j++) {
                System.out.printf("%,.2f ", matrix[i][j]);
            }
            System.out.println();
        }
    }

    public double getIntAt(int i, int j) {
        return matrix[i][j];
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public Vector getVectorRow(int r) {
        double[] vec = new double[dimCol];
        System.arraycopy(matrix[r], 0, vec, 0, dimCol);
        return new Vector(vec);
    }

    public Vector getVectorCol(int c) {
        double[] vec = new double[dimRow];
        for (int i = 0; i < dimRow; i++) {
            vec[i] = matrix[i][c];
        }
        return new Vector(vec);
    }

    public static Matrix diagonal(double... n) {
        double[][] matrix = new double[n.length][n.length];
        for (int i = 0; i < n.length; i++) {
            matrix[i][i] = n[i];
        }
        return new Matrix(matrix);
    }

    public Matrix antiZero(){
        for(int i = 0; i < dimRow; i++){
            for(int j = 0; j< dimCol; j++){
                if(matrix[i][j] == 0){
                    matrix[i][j] = 0;
                }
            }
        }
        return new Matrix(matrix);
    }
}
