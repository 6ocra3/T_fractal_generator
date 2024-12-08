package backend.academy.fractal;

import backend.academy.fractal.config.Config;
import backend.academy.fractal.config.ConfigLoader;
import backend.academy.fractal.structs.ImagePoint;
import backend.academy.fractal.structs.Point;
import backend.academy.fractal.transformations.Transformations;
import backend.academy.fractal.variations.Variations;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FractalApp {
    private final Config config;
    private final FractalImage image;

    public FractalApp(Config config) {
        this.config = config;
        image = new FractalImage(config);

        int totalThreads = config.getFractal().getThreads();
        int iterationsForWorker = config.getFractal().getIterations() / totalThreads;
        ExecutorService executor = Executors.newFixedThreadPool(totalThreads);

        CompletionService<FractalImage> completionService = new ExecutorCompletionService<>(executor);

        for (int i = 0; i < totalThreads; i++) {
            completionService.submit(() -> startWorker(iterationsForWorker));
        }

        for (int i = 0; i < totalThreads; i++) {
            try {
                Future<FractalImage> partlyImage = completionService.take();
                FractalImage result = partlyImage.get();
                image.blendImages(result);
            } catch (InterruptedException | ExecutionException e) {
                log.error(String.valueOf(e));
            }
        }

        executor.shutdown();

        image.saveImage("fractalMath.png");
    }

    private FractalImage startWorker(int iterations) {
        Transformations transformations = new Transformations(config.getTransformations());
        Variations variations = new Variations(config.getVariations());
        FractalMath fractalMath = new FractalMath(config.getFractal());
        FractalImage workerImage = new FractalImage(config);
        for (int pointNum = 0; pointNum < (iterations / config.getFractal().getIterOnPoint()); pointNum++) {
            Point point = fractalMath.getRandomPoint();
            for (int iter = 0; iter < config.getFractal().getIterOnPoint(); iter++) {
                int transformationInd = transformations.applyTransformation(point);
                int variationInd = variations.applyVariation(point);

                ImagePoint imagePoint = fractalMath.convertToImageCoords(point);
                workerImage.addPoint(imagePoint, transformationInd, variationInd);
            }
        }
        return workerImage;
    }

    public static Config readConfig(String configPath) {
        Config config = null;
        try {
            config = ConfigLoader.loadConfig(configPath);
        } catch (Exception e) {
            log.error(String.valueOf(e));
        }
        return config;
    }
}
