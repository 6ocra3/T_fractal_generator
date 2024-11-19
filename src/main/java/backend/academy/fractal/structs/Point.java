package backend.academy.fractal.structs;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Point {
    double x;
    double y;
    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    public void updateCoords(double x, double y){
        this.x = x;
        this.y = y;
    }
}
