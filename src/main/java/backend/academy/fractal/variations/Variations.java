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
    double[] chances = new double[] {0.35, 0.5, 0.2, 0.7};

    public int getVariation() {
        double sum = 0;
        for (double chance : chances) {
            sum += chance;
        }
        for (int i = 0; i < chances.length; i++) {
            chances[i] /= sum;
        }
        int multiplicator = 10000;
        int randInt = rnd.nextInt(multiplicator);
        int prev = 0;
        for (int i = 0; i < variations.size(); i++) {
            if (prev < randInt && randInt <= prev + chances[i] * multiplicator) {
                return i;
            }
            prev += (int) (chances[i] * multiplicator);
        }
        return 0;
    }

    public int applyVariation(Point point) {
        int variationIndex = getVariation();
        return applyVariation(point, variationIndex);
    }

    public int applyVariation(Point point, int variationIndex) {
        variations.get(variationIndex).apply(point);
        return variationIndex;
    }

    public int getMaxCount() {
        return variations.size();
    }
}
