package backend.academy.fractal.structs;

import java.awt.Color;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Pixel {
    int x;
    int y;
    Color color;
    int density;

    public Pixel(int x, int y, Color color, int density) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.density = density;
    }

    public void incrementDensity() {
        density++;
    }
}
