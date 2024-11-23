package backend.academy.fractal.variations.implementations;

import backend.academy.fractal.structs.Point;
import backend.academy.fractal.variations.Variation;

public class SphericalVariation implements Variation {
    private static final double R_MIN = 1e-6;

    @Override
    public String getName() {
        return "spherical";
    }

    @Override
    public void apply(Point point) {
        double x = point.x();
        double y = point.y();
        double r = x * x + y * y;
        if (r == 0) {
            r = R_MIN;
        }
        point.updateCoords(x / r, y / r);
    }
}
