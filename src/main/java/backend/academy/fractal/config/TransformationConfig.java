package backend.academy.fractal.config;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.util.List;

@Getter
@Setter
@Accessors(fluent = false, chain = false)
public class TransformationConfig {
    private List<Double> transformation;
    private double chance;
}
