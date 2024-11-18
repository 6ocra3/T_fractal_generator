package backend.academy.fractal.structs;

import lombok.Getter;
import lombok.Setter;
import java.awt.Color;

@Getter @Setter
public class Pixel {
    int x;
    int y;
    Color color;
    int density;


    public Pixel(int x, int y, Color color, int density){
        this.x = x;
        this.y = y;
        this.color = color;
        this.density = density;
    }

    public void incrementDensity(){
        density++;
    }
}
