package backend.academy.fractal;

import backend.academy.fractal.structs.Point;
import java.util.Random;

public class Fractal {
    double ratio;
    Random rnd = new Random();

    public Fractal(int width, int height){
        ratio = (double) width / height;
    }

    public Point getRandomPoint(){
        double xMulti = ratio * 2;
        double xDiff = xMulti / 2;
        double x = rnd.nextDouble() * (xMulti) - xDiff;
        double y = rnd.nextDouble() * 2 - 1;
        return new Point(x, y);
    }
}
