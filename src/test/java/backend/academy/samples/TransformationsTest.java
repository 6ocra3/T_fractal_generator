package backend.academy.samples;

import backend.academy.fractal.FractalApp;
import backend.academy.fractal.config.Config;
import backend.academy.fractal.structs.Point;
import backend.academy.fractal.transformations.Transformations;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TransformationsTest {

    @Test
    public void testTransformation(){
        // Arrange
        Config config = FractalApp.readConfig("config.yaml");
        Transformations transformations = new Transformations(config.getTransformations());
        Point point = new Point(5, 5);
        Point testPoint = new Point(5, 5);

        // Act
        int transformationIndex = transformations.applyTransformation(testPoint);

        //Assert
        List<Double> transformation = transformations.transformations().get(transformationIndex);
        double shouldX = transformation.get(0) * point.x() + transformation.get(1) * point.y() + transformation.get(4);
        double shouldY = transformation.get(2) * point.x() + transformation.get(3) * point.y() + transformation.get(5);

        assertThat(shouldX).isEqualTo(testPoint.x());
        assertThat(shouldY).isEqualTo(testPoint.y());

    }
}
