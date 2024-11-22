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
}
