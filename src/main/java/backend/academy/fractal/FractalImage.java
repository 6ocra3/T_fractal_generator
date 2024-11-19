package backend.academy.fractal;

import backend.academy.fractal.processors.LogarithmicGammaProcessor;
import backend.academy.fractal.structs.ImagePoint;
import backend.academy.fractal.structs.Pixel;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FractalImage {
    List<Color> gradient;
    Color baseColor = new Color(0,0,0);
    Pixel[][] image;
    LogarithmicGammaProcessor gammaProcessor = new LogarithmicGammaProcessor();
    int width;
    int height;

    public FractalImage(int width, int height, int maxTransformations, int maxVariations){
        List<Color> multiColors = List.of(Color.WHITE, Color.BLUE, Color.RED);
        gradient = generateMultiGradient(multiColors, maxVariations+1);

        this.width = width;
        this.height = height;
        image = new Pixel[height][width];
        for(int y = 0; y<height;y++){
            for(int x = 0; x<width;x++){
                Pixel pixel = new Pixel(x, y, baseColor, 0);
                image[y][x] = pixel;
            }
        }
    }

    public void addPoint(ImagePoint point, int transformationIndex, int variationIndex){
        if (point.x() >= 0 && point.x() < width && point.y() >= 0 && point.y() < height) {
            Pixel pixel = image[point.y()][point.x()];
            pixel.incrementDensity();

            double ratio = Math.min(1.0, 0.1 * pixel.density());

            Color variationColor = gradient.get(variationIndex);

            Color blendedColor = blendColors(pixel.color(), variationColor, ratio);

            pixel.color(blendedColor);
        }
    }



    public void saveImage(String imageName){
        image = gammaProcessor.process(image);
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for(int y = 0; y<height;y++){
            for(int x = 0;x<width;x++){
                bufferedImage.setRGB(x, y, image[y][x].color().getRGB());
            }
        }

        try{
            File outputFile = new File(imageName);
            ImageIO.write(bufferedImage, "png", outputFile);
            System.out.println("Фрактал сохранен в файл: " + imageName);
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении изображения: " + e.getMessage());
        }
    }

    public static List<Color> generateMultiGradient(List<Color> colors, int n) {
        List<Color> gradient = new ArrayList<>();
        if (colors.size() < 2 || n < colors.size()) throw new IllegalArgumentException("Invalid input");

        int segments = colors.size() - 1;
        int stepsPerSegment = n / segments;
        int remaining = n % segments;

        for (int i = 0; i < segments; i++) {
            Color startColor = colors.get(i);
            Color endColor = colors.get(i + 1);
            int steps = stepsPerSegment + (remaining-- > 0 ? 1 : 0);

            List<Color> segmentGradient = generateGradient(startColor, endColor, steps);
            gradient.addAll(segmentGradient.subList(0, segmentGradient.size() - 1)); // Avoid duplicates
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

    private Color blendColors(Color baseColor, Color newColor, double ratio) {
        int red = (int) (baseColor.getRed() * (1 - ratio) + newColor.getRed() * ratio);
        int green = (int) (baseColor.getGreen() * (1 - ratio) + newColor.getGreen() * ratio);
        int blue = (int) (baseColor.getBlue() * (1 - ratio) + newColor.getBlue() * ratio);
        return new Color(red, green, blue);
    }
}
