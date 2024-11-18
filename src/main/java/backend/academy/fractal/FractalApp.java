package backend.academy.fractal;

import backend.academy.fractal.structs.Point;
import backend.academy.fractal.transformations.Transformations;
import java.awt.Color;

public class FractalApp {
    int width = 400;
    int height = 800;
    Color[][] colorMap;
    int[][] densityMap;
    int iterations = 1_000_000;
    Transformations transformations = new Transformations();

    public FractalApp(){
        Fractal fractal = new Fractal(width, height);
        Point point = fractal.getRandomPoint();
        for(int iter = 0; iter<iterations;iter++){
            Point newPoint = transformations.applyTransformation(point);

        }
    }
}
