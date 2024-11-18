package backend.academy.fractal.variations.implementations;

import backend.academy.fractal.structs.Point;
import backend.academy.fractal.variations.Variation;

public class HeartVariation implements Variation {
    @Override
    public String getName() {
        return "heart variation";
    }

    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        double r = Math.sqrt(x * x + y * y);
        double theta = Math.atan2(y, x);
        return new Point(
            r * Math.sin(theta * r),
            -r * Math.cos(theta * r)
        );
    }
}
