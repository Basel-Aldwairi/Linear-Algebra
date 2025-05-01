import com.sun.jdi.connect.Connector;

import javax.sound.sampled.Line;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        double[][] p = {{0,1,-5},
                        {-1,3,9},
                        {5,-9,1}};
        double[][] q = {{0,-1,0},
                        {1,0,0},
                        {0,0,-1}};
        double[][] c = {{1,0},
                        {0,1}};
        Matrix P = new Matrix(p);;
        Matrix Q = new Matrix(q);
        Matrix g = Linear.product(Q,Linear.transpose(Q));
        Matrix s = Linear.product(Linear.transpose(Q),Q);

       // System.out.println(Linear.isSymmetric(Q));
        Matrix C = new Matrix(c);
        System.out.println(Linear.isNormal(C));

        System.out.println(Linear.isOrthogonal(C));
        //System.out.println(Linear.areEqual(C,Q));
        Linear.transpose(C).print();
        Linear.inverse(C).print();
        System.out.println(Linear.inverse(C).getIntAt(1,0));
        System.out.println(Linear.areEqual(Linear.transpose(C),Linear.inverse(C)));
        System.out.println(Linear.isOrthogonal(C));

    }
}