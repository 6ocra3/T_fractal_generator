package backend.academy.fractal.variations;

import backend.academy.fractal.config.VariationConfig;
import backend.academy.fractal.structs.Point;
import backend.academy.fractal.utils.GetRandom;
import backend.academy.fractal.variations.implementations.BubbleVariation;
import backend.academy.fractal.variations.implementations.HeartVariation;
import backend.academy.fractal.variations.implementations.SphericalVariation;
import backend.academy.fractal.variations.implementations.SwirlVariation;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Variations {
    public static final List<Variation> VARIATIONS =
        List.of(new SphericalVariation(), new BubbleVariation(), new HeartVariation(), new SwirlVariation());
    List<VariationConfig> config;
    List<Variation> variations;
    List<Double> chances;

    public Variations(List<VariationConfig> config) {
        this.config = config;
        this.variations = new ArrayList<>();
        this.chances = new ArrayList<>();

        for(VariationConfig variationConfig : config){
            for(Variation variation : VARIATIONS){
                if(variation.getName().equals(variationConfig.getName())){
                    variations.add(variation);
                    chances.add(variationConfig.getChance());
                }
            }
        }
    }

    public int applyVariation(Point point) {
        int variationIndex = GetRandom.getRandomWithChances(chances);
        return applyVariation(point, variationIndex);
    }

    public int applyVariation(Point point, int variationIndex) {
        variations.get(variationIndex).apply(point);
        return variationIndex;
    }
}
