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

    public int getTransformationIndex(){
        return rnd.nextInt(transformations.length);
    }

    public int applyTransformation(Point point){
        int index = getTransformationIndex();
        return applyTransformation(point, index);
    }

    public int applyTransformation(Point point, int transformationIndex){
        double[] transformation = transformations[transformationIndex];
        double newX = transformation[0] * point.x() + transformation[1] * point.y() + transformation[4];
        double newY = transformation[2] * point.x() + transformation[3] * point.y() + transformation[5];
        point.x(newX); point.y(newY);
        return transformationIndex;
    }

    public int getMaxCount(){return  transformations.length;}

}
