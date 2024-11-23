package backend.academy.fractal.variations.implementations;

import backend.academy.fractal.structs.Point;
import backend.academy.fractal.variations.Variation;

public class BubbleVariation implements Variation {
    private static final int SCALE_R = 4;

    @Override
    public String getName() {
        return "bubble";
    }

    @Override
    public void apply(Point point) {
        double x = point.x();
        double y = point.y();
        double r = x * x + y * y;
        double scale = SCALE_R / (r + SCALE_R);
        point.updateCoords(scale * x, scale * y);
    }
}
