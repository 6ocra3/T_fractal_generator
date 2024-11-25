package backend.academy.fractal;

import backend.academy.fractal.config.FractalConfig;
import backend.academy.fractal.structs.ImagePoint;
import backend.academy.fractal.structs.Point;
import java.util.Random;

public class FractalMath {
    double ratio;
    FractalConfig config;
    Random rnd = new Random();

    public FractalMath(FractalConfig config) {
        this.config = config;
        ratio = (double) config.getWidth() / config.getHeight();
    }

    public Point getRandomPoint() {
        double xMulti = ratio * 2;
        double xDiff = xMulti / 2;
        double x = rnd.nextDouble() * (xMulti) - xDiff;
        double y = rnd.nextDouble() * 2 - 1;
        return new Point(x, y);
    }

    public ImagePoint convertToImageCoords(Point point) {
        int x = (int) ((point.x() + ratio) * (config.getWidth() - 1) / (ratio * 2));
        int y = (int) ((point.y() + 1) * (config.getHeight() - 1) / 2);
        return new ImagePoint(x, y);
    }
}
