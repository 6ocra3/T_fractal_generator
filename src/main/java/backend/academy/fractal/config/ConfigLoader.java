package backend.academy.fractal.config;

import org.yaml.snakeyaml.Yaml;
import java.io.InputStream;

public class ConfigLoader {
    public static Config loadConfig(String fileName) throws Exception {
        Yaml yaml = new Yaml();
        try (InputStream inputStream = ConfigLoader.class.getClassLoader().getResourceAsStream(fileName)) {
            return yaml.loadAs(inputStream, Config.class);
        }
    }
}
