package backend.academy.fractal.variations.implementations;

import backend.academy.fractal.structs.Point;
import backend.academy.fractal.variations.Variation;

public class RectangularVariation implements Variation {
    @Override
    public String getName() {
        return "rectangular";
    }

    @Override
    public void apply(Point point) {
        double x = point.x();
        double y = point.y();
        point.updateCoords(Math.sin(x), Math.sin(y));
    }
}
