package backend.academy.fractal;

import backend.academy.fractal.config.Config;
import backend.academy.fractal.config.ConfigLoader;
import backend.academy.fractal.structs.ImagePoint;
import backend.academy.fractal.structs.Point;
import backend.academy.fractal.transformations.Transformations;
import backend.academy.fractal.variations.Variations;
import java.util.concurrent.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FractalApp {
    Config config;
    FractalMath fractalMath;
    FractalImage image;
    Transformations transformations;
    Variations variations;

    public FractalApp() {
        setupApp();

        long startTime = System.nanoTime();
        int totalThreads = config.getFractal().getThreads();
        int iterationsForWorker = config.getFractal().getIterations() / totalThreads;
        ExecutorService executor = Executors.newFixedThreadPool(totalThreads);

        CompletionService<FractalImage> completionService = new ExecutorCompletionService<>(executor);

        for(int i = 0; i<totalThreads;i++){
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

        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;

        log.info("Fractal generation completed in {} ms", totalTime/ 1_000_000);

        executor.shutdown();

        image.saveImage("fractalMath.png");
    }

    private FractalImage startWorker(int iterations){
        FractalImage workerImage = new FractalImage(config);
        Point point = fractalMath.getRandomPoint();
        for (int iter = 0; iter < iterations; iter++) {
            int transformationInd = transformations.applyTransformation(point);
            int variationInd = variations.applyVariation(point);

            ImagePoint imagePoint = fractalMath.convertToImageCoords(point);
            workerImage.addPoint(imagePoint, transformationInd, variationInd);
        }
        return workerImage;
    }

    private void setupApp() {
        try {
            config = ConfigLoader.loadConfig("config.yaml");
        } catch (Exception e) {
            log.error(String.valueOf(e));
        }
        fractalMath = new FractalMath(config.getFractal());
        image = new FractalImage(config);
        transformations = new Transformations(config.getTransformations());
        variations = new Variations(config.getVariations());

    }
}
