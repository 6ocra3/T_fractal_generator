package backend.academy.fractal.processors;

import backend.academy.fractal.structs.Pixel;
import java.awt.Color;
import java.lang.Math;

public class LogarithmicGammaProcessor {
    public Pixel[][] process(Pixel[][] image) {
        int maxDensity = getMaxDensity(image);
        double alpha = 5;

        for (Pixel[] pixels : image) {
            for (Pixel pixel : pixels) {
                if (pixel.density() > 0) {
                    Color originalColor = pixel.color();
                    int correctedRed = applyLogGamma(originalColor.getRed(), pixel.density(), maxDensity, alpha);
                    int correctedGreen = applyLogGamma(originalColor.getGreen(), pixel.density(), maxDensity, alpha);
                    int correctedBlue = applyLogGamma(originalColor.getBlue(), pixel.density(), maxDensity, alpha);

                    pixel.color(new Color(correctedRed, correctedGreen, correctedBlue));
                }
            }
        }

        return image;
    }

    private int applyLogGamma(int colorValue, int density, int maxDensity, double alpha) {
        double densityFactor = Math.max((double) density / maxDensity, 0.6);

        double normalizedValue = (double) colorValue / 255.0;

        double correctedValue = Math.log(1 + alpha * normalizedValue * densityFactor) / Math.log(1 + alpha);

        return (int) (correctedValue * 255);
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
