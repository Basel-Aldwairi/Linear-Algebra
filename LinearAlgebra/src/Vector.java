
public class Vector {
    private final double[] vector;
    private int dimension;

    public Vector(double... n) {
        vector = n;
        dimension = vector.length;
    }

    public int getDim() {
        return dimension;
    }

    public void setDimension(int dim) {
        dimension = dim;
    }

    //@Override
    public void printVector() {
        System.out.print("[");
        for (int i = 0; i < dimension; i++) {
            System.out.printf("%,.2f, ", getIntAt(i));
        }
        System.out.println("\b\b]");
    }

    public double[] getVector() {
        return vector;
    }

    public double getIntAt(int n) {

        if(n < dimension)  return vector[n];
        return 0;
    }


}
