package backend.academy.fractal;

import backend.academy.fractal.config.Config;
import backend.academy.fractal.config.ConfigLoader;
import backend.academy.fractal.structs.ImagePoint;
import backend.academy.fractal.structs.Point;
import backend.academy.fractal.transformations.Transformations;
import backend.academy.fractal.variations.Variations;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FractalApp {
    Config config;
    Fractal fractal;
    FractalImage image;
    Transformations transformations;
    Variations variations;

    public FractalApp() {
        setupApp();
        Point point = fractal.getRandomPoint();
        for (int iter = 0; iter < config.getFractal().getIterations(); iter++) {
            int transformationInd = transformations.applyTransformation(point);
            int variationInd = variations.applyVariation(point);

            ImagePoint imagePoint = fractal.convertToImageCoords(point);
            image.addPoint(imagePoint, transformationInd, variationInd);
        }
        image.saveImage("fractal.png");
    }

    private void setupApp() {
        try {
            config = ConfigLoader.loadConfig("config.yaml");
        } catch (Exception e) {
            log.error(String.valueOf(e));
        }
        fractal = new Fractal(config.getFractal());
        image = new FractalImage(config);
        transformations = new Transformations(config.getTransformations());
        variations = new Variations(config.getVariations());

    }
}
