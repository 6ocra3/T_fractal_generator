package backend.academy.fractal.config;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent = false, chain = false)
public class TransformationConfig {
    private List<Double> transformation;
    private double chance;
}
