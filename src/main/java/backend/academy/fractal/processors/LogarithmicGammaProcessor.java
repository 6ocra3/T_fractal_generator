package backend.academy.fractal.processors;

import backend.academy.fractal.structs.Pixel;
import java.awt.Color;

public class LogarithmicGammaProcessor {
    private static final int ALPHA = 5;
    private static final double MIN_DENSITY_FACTOR = 0.6;
    private static final double MAX_COLOR_VALUE = 255.0;

    public Pixel[][] process(Pixel[][] image) {
        int maxDensity = getMaxDensity(image);

        for (Pixel[] pixels : image) {
            for (Pixel pixel : pixels) {
                if (pixel.density() > 0) {
                    Color originalColor = pixel.color();
                    int correctedRed = applyLogGamma(originalColor.getRed(), pixel.density(), maxDensity);
                    int correctedGreen = applyLogGamma(originalColor.getGreen(), pixel.density(), maxDensity);
                    int correctedBlue = applyLogGamma(originalColor.getBlue(), pixel.density(), maxDensity);

                    pixel.color(new Color(correctedRed, correctedGreen, correctedBlue));
                }
            }
        }

        return image;
    }

    private int applyLogGamma(int colorValue, int density, int maxDensity) {
        double densityFactor = Math.max((double) density / maxDensity, MIN_DENSITY_FACTOR);

        double normalizedValue = (double) colorValue / MAX_COLOR_VALUE;

        double correctedValue = Math.log(1 + ALPHA * normalizedValue * densityFactor) / Math.log(1 + ALPHA);

        return (int) (correctedValue * MAX_COLOR_VALUE);
    }

    private int getMaxDensity(Pixel[][] image) {
        int maxDensity = 0;
        for (Pixel[] pixels : image) {
            for (Pixel pixel : pixels) {
                maxDensity = Math.max(maxDensity, pixel.density());
            }
        }
        return maxDensity;
    }
}
