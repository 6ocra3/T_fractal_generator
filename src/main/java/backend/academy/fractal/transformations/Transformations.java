package backend.academy.fractal.transformations;

import backend.academy.fractal.config.TransformationConfig;
import backend.academy.fractal.structs.Point;
import backend.academy.fractal.utils.GetRandom;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("MagicNumber")
public class Transformations {
    @Getter
    private final List<List<Double>> transformations;
    private final List<Double> chances;

    public Transformations(List<TransformationConfig> config) {
        this.transformations = new ArrayList<>();
        this.chances = new ArrayList<>();
        for (TransformationConfig transformation : config) {
            transformations.add(transformation.getTransformation());
            chances.add(transformation.getChance());
        }
    }

    public int applyTransformation(Point point) {
        int index = GetRandom.getRandomWithChances(chances);
        return applyTransformation(point, index);
    }

    public int applyTransformation(Point point, int transformationIndex) {
        List<Double> transformation = transformations.get(transformationIndex);
        double newX = transformation.get(0) * point.x() + transformation.get(1) * point.y() + transformation.get(4);
        double newY = transformation.get(2) * point.x() + transformation.get(3) * point.y() + transformation.get(5);
        point.x(newX);
        point.y(newY);
        return transformationIndex;
    }

}
