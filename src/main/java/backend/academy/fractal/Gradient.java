package backend.academy.fractal;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Gradient {
    public static List<Color> generateMultiGradient(List<Color> colors, int n) {
        List<Color> gradient = new ArrayList<>();

        int segments = colors.size() - 1;
        int stepsPerSegment = n / segments;
        int remaining = n % segments;

        for (int i = 0; i < segments; i++) {
            Color startColor = colors.get(i);
            Color endColor = colors.get(i + 1);
            int steps = stepsPerSegment + (remaining-- > 0 ? 1 : 0);

            List<Color> segmentGradient = generateGradient(startColor, endColor, steps);
            gradient.addAll(segmentGradient.subList(0, segmentGradient.size() - 1));
        }
        gradient.add(colors.getLast());
        return gradient;
    }

    public static List<Color> generateGradient(Color startColor, Color endColor, int n) {
        List<Color> gradient = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            double ratio = (double) i / (n - 1);
            int red = (int) (startColor.getRed() + ratio * (endColor.getRed() - startColor.getRed()));
            int green = (int) (startColor.getGreen() + ratio * (endColor.getGreen() - startColor.getGreen()));
            int blue = (int) (startColor.getBlue() + ratio * (endColor.getBlue() - startColor.getBlue()));
            gradient.add(new Color(red, green, blue));
        }
        return gradient;
    }
}
