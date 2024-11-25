package backend.academy.fractal;

import backend.academy.fractal.config.Config;
import backend.academy.fractal.processors.LogarithmicGammaProcessor;
import backend.academy.fractal.structs.ImagePoint;
import backend.academy.fractal.structs.Pixel;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FractalImage {
    private static final double RATIO_STEP = 0.1;
    List<Color> multiColors = List.of(Color.WHITE, Color.BLUE, Color.RED);
    List<Color> gradient;
    Color baseColor = new Color(0, 0, 0);
    @Getter
    Pixel[][] image;
    LogarithmicGammaProcessor gammaProcessor = new LogarithmicGammaProcessor();
    int width;
    int height;
    Config config;

    public FractalImage(Config config) {
        gradient = Gradient.generateMultiGradient(multiColors, config.getVariations().size() + 1);
        this.config = config;
        this.width = config.getFractal().getWidth();
        this.height = config.getFractal().getHeight();
        image = new Pixel[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Pixel pixel = new Pixel(x, y, baseColor, 0);
                image[y][x] = pixel;
            }
        }
    }

    public void addPoint(ImagePoint point, int transformationIndex, int variationIndex) {
        if (point.x() >= 0 && point.x() < width && point.y() >= 0 && point.y() < height) {
            Pixel pixel = image[point.y()][point.x()];
            pixel.incrementDensity();

            double ratio = Math.min(1.0, RATIO_STEP * pixel.density());

            Color variationColor = gradient.get(variationIndex);

            Color blendedColor = blendColors(pixel.color(), variationColor, ratio);

            pixel.color(blendedColor);
        }
    }

    private void addSymmetry(boolean xSymmetry, boolean ySymmetry) {
        if (xSymmetry) {
            for (int y = 0; y < height / 2; y++) {
                if (width >= 0) {
                    System.arraycopy(image[y], 0, image[height - y - 1], 0, width);
                }
            }
        }
        if (ySymmetry) {
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width / 2; x++) {
                    image[y][width - x - 1] = image[y][x];
                }
            }
        }
    }

    public void saveImage(String imageName) {
        image = gammaProcessor.process(image);
        addSymmetry(config.getFractal().isHorizontal(), config.getFractal().isVertical());

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                bufferedImage.setRGB(x, y, image[y][x].color().getRGB());
            }
        }

        try {
            File outputFile = new File(imageName);
            ImageIO.write(bufferedImage, "png", outputFile);
            log.info("Фрактал сохранен в файл: {}", imageName);
        } catch (IOException e) {
            log.error("Ошибка при сохранении изображения: {}", e.getMessage());
        }
    }

    private Color blendColors(Color baseColor, Color newColor, double ratio) {
        int red = (int) (baseColor.getRed() * (1 - ratio) + newColor.getRed() * ratio);
        int green = (int) (baseColor.getGreen() * (1 - ratio) + newColor.getGreen() * ratio);
        int blue = (int) (baseColor.getBlue() * (1 - ratio) + newColor.getBlue() * ratio);
        return new Color(red, green, blue);
    }

    public void blendImages(FractalImage partlyImage){
        for(int y = 0; y<height;y++){
            for(int x = 0; x<width;x++){
                blendPixels(image[y][x], partlyImage.image()[y][x]);
            }
        }
    }

    private void blendPixels(Pixel pixelOrigin, Pixel pixelBlend) {
        pixelOrigin.density(pixelOrigin.density() + pixelBlend.density());
        if(pixelBlend.color().equals(baseColor)){
            return;
        }
        if(pixelOrigin.color().equals(baseColor)){
            pixelOrigin.color(pixelBlend.color());
            return;
        }
        int densityOrigin = pixelOrigin.density();
        int densityBlend = pixelBlend.density();

        int totalDensity = densityOrigin + densityBlend;

        if (totalDensity == 0) {
            return;
        }


        double ratio = Math.min(1.0, RATIO_STEP * (densityOrigin + densityBlend)););
        Color blendedColor = blendColors(pixelOrigin.color(), pixelBlend.color(), 1);

        pixelOrigin.color(blendedColor);
    }

}
