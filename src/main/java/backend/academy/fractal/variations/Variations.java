package backend.academy.fractal.variations;

import backend.academy.fractal.structs.Point;
import backend.academy.fractal.variations.implementations.BubbleVariation;
import backend.academy.fractal.variations.implementations.HeartVariation;
import backend.academy.fractal.variations.implementations.SphericalVariation;
import backend.academy.fractal.variations.implementations.SwirlVariation;
import java.util.List;
import java.util.Random;

public class Variations {
    Random rnd = new Random();
    List<Variation> variations =
        List.of(new SphericalVariation(), new BubbleVariation(), new HeartVariation(), new SwirlVariation());

    public Variation getVariation() {
        int t = rnd.nextInt(variations.size());
        return variations.get(t);
    }

    public Point applyVariation(Point point) {
        Variation variation = getVariation();
        return applyVariation(point, variation);
    }

    public Point applyVariation(Point point, Variation variation) {
        return variation.apply(point);
    }

}
