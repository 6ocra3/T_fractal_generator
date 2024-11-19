package backend.academy.fractal;

import backend.academy.fractal.structs.ImagePoint;
import backend.academy.fractal.structs.Point;
import backend.academy.fractal.transformations.Transformations;
import backend.academy.fractal.variations.Variations;
import java.awt.Color;

public class FractalApp {
    int width = 1000;
    int height = 1000;
    Color[][] colorMap;
    int[][] densityMap;
    int iterations = 1_000_000;
    Transformations transformations = new Transformations();
    Variations variations = new Variations();

    public FractalApp(){
        Fractal fractal = new Fractal(width, height);
        FractalImage image = new FractalImage(width, height, transformations.getMaxCount(), variations.getMaxCount());
        Point point = fractal.getRandomPoint();
        for(int iter = 0; iter<iterations;iter++){
            int transformationInd = transformations.applyTransformation(point);
            int variationInd = variations.applyVariation(point);

            ImagePoint imagePoint = fractal.convertToImageCoords(point);
            image.addPoint(imagePoint, transformationInd, variationInd);
        }
        image.saveImage("fractal.png");
    }
}
