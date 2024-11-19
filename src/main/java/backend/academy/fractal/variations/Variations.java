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

    public int getVariation() {
        return rnd.nextInt(variations.size());
    }

    public int applyVariation(Point point) {
        int variationIndex = getVariation();
        return applyVariation(point, variationIndex);
    }

    public int applyVariation(Point point, int variationIndex) {
        variations.get(variationIndex).apply(point);
        return variationIndex;
    }

    public int getMaxCount(){return variations.size();}

}
