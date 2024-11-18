package backend.academy.fractal;

import backend.academy.fractal.structs.ImagePoint;
import backend.academy.fractal.structs.Pixel;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FractalImage {
    Color baseColor = new Color(0,0,0);
    Pixel[][] image;
    int width;
    int height;

    public FractalImage(int width, int height){
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

    public void addPoint(ImagePoint point){
        if (point.x() >= 0 && point.x() < width && point.y() >= 0 && point.y() < height) {
            image[point.y()][point.x()].incrementDensity();
            image[point.y()][point.x()].color(new Color(255, 255, 255));
        }
    }

    public void saveImage(String imageName){
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
}
