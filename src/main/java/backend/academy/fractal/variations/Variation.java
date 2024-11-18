package backend.academy.fractal.variations;

import backend.academy.fractal.structs.Point;

public interface Variation {
    String getName();
    Point apply(Point point);
}
