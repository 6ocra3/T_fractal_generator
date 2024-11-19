package backend.academy.fractal.config;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.util.List;

@Getter
@Setter
@Accessors(fluent = false, chain = false)
public class Config {
    private FractalConfig fractal;
    private List<TransformationConfig> transformations;
    private List<VariationConfig> variations;

    public Config() {
    }

    @Getter
    @Setter
    @Accessors(fluent = false, chain = false)
    public static class FractalConfig {
        private int width;
        private int height;
        private boolean horizontal;
        private boolean vertical;
    }

    @Getter
    @Setter
    @Accessors(fluent = false, chain = false)
    public static class TransformationConfig {
        private double[] transformation;
        private double chance;
    }

    @Getter
    @Setter
    @Accessors(fluent = false, chain = false)
    public static class VariationConfig {
        private String name;
        private double chance;
    }
}
