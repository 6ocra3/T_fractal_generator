package backend.academy.samples;

import backend.academy.fractal.FractalApp;
import backend.academy.fractal.config.Config;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FractalAppTest {


    @Test
    public void testMultiThreading(){
        // Arrange
        Config config = FractalApp.readConfig("config.yaml");

        // Act
        config.getFractal().setThreads(1);
        long firstStartTime = System.nanoTime();
        FractalApp fractalApp = new FractalApp(config);
        long firstTime = System.nanoTime() - firstStartTime;

        config.getFractal().setThreads(6);
        long secondStartTime = System.nanoTime();
        FractalApp fractalApp2 = new FractalApp(config);
        long secondTime = System.nanoTime() - secondStartTime;

        // Assert
        assertThat(secondTime).isLessThan(firstTime);
    }
}
