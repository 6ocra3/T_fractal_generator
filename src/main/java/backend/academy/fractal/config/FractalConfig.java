package backend.academy.fractal.config;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent = false, chain = false)
public class FractalConfig {
    private int width;
    private int height;
    private boolean horizontal;
    private boolean vertical;
    private int iterations;
    private int threads;
}
