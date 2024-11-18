package backend.academy.fractal.variations.implementations;

import backend.academy.fractal.structs.Point;
import backend.academy.fractal.variations.Variation;

public class SphericalVariation implements Variation {
    @Override
    public String getName() {
        return "spherical variation";
    }

    @Override
    public Point apply(Point point) {
        double x = point.x(); double y = point.y();
        double r = x*x + y*y;
        if (r == 0) r = 1e-6;
        return new Point(x/r, y/r);
    }
}
