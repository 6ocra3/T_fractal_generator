package backend.academy.fractal.variations;

import backend.academy.fractal.structs.Point;

public interface Variation {
    String getName();
    void apply(Point point);
}
