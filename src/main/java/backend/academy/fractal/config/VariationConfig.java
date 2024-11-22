package backend.academy.fractal.config;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent = false, chain = false)
public class VariationConfig {
    private String name;
    private double chance;
}
