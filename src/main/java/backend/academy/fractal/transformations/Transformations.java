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

    public Point applyTransformation(Point point){
        double[] transformation = getTransformation();
        return applyTransformation(point, transformation);
    }

    public Point applyTransformation(Point point, double[] transformation){
        double newX = transformation[0] * point.x() + transformation[1] * point.y() + transformation[4];
        double newY = transformation[2] * point.x() + transformation[3] * point.y() + transformation[5];
        return new Point(newX, newY);
    }

}
