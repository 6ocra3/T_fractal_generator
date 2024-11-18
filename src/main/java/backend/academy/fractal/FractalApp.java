package backend.academy.fractal;

import backend.academy.fractal.structs.Point;
import java.awt.Color;

public class FractalApp {
    int width = 400;
    int height = 800;
    Color[][] colorMap;
    int[][] densityMap;
    int iterations = 1_000_000;

    public FractalApp(){
        Fractal fractal = new Fractal(width, height);
        Point point = fractal.getRandomPoint();
        for(int iter = 0; iter<iterations;iter++){

        }

    }
}
