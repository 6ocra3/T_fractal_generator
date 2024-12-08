package backend.academy;

import backend.academy.fractal.FractalApp;
import backend.academy.fractal.config.Config;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {
    public static void main(String[] args) {
        Config config = FractalApp.readConfig("config.yaml");
        FractalApp fractalApp = new FractalApp(config);
    }
}
