package backend.academy.fractal.transformations;

import backend.academy.fractal.structs.Point;
import java.util.Random;

public class Transformations {
    Random rnd = new Random();
    public final double[][] transformations = {
        {0.5, 0.0, 0.0, 0.5, -1.0, 1.0},
        {0.5, 0.0, 0.0, 0.5, 1.0, -1.0},
        {0.5, 0.5, -0.5, 0.5, -1.0, -1.0}
    };

    public double[] getTransformation(){
        int t = rnd.nextInt(transformations.length);
        return transformations[t];
    }

}
