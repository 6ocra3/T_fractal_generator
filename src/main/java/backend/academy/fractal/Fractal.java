package backend.academy.fractal;

import backend.academy.fractal.structs.ImagePoint;
import backend.academy.fractal.structs.Pixel;
import backend.academy.fractal.structs.Point;
import java.util.Random;

public class Fractal {
    double ratio;
    int imageWidth;
    int imageHeight;
    Random rnd = new Random();

    public Fractal(int width, int height){
        imageWidth = width;
        imageHeight = height;
        ratio = (double) width / height;
    }

    public Point getRandomPoint(){
        double xMulti = ratio * 2;
        double xDiff = xMulti / 2;
        double x = rnd.nextDouble() * (xMulti) - xDiff;
        double y = rnd.nextDouble() * 2 - 1;
        return new Point(x, y);
    }

    public ImagePoint convertToImageCoords(Point point){
        int x = (int) ((point.x() + ratio) * (imageWidth - 1) / (ratio*2));
        int y = (int) ((point.y() + 1) * (imageHeight - 1) / 2);
        return new ImagePoint(x, y);
    }
}
