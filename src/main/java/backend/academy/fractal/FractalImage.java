package backend.academy.fractal;

import backend.academy.fractal.structs.ImagePoint;
import backend.academy.fractal.structs.Pixel;
import java.awt.Color;

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
        }
    }

    public void saveImage(String imageName){

    }
}
