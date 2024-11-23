package backend.academy.fractal.config;

import java.io.InputStream;
import lombok.experimental.UtilityClass;
import org.yaml.snakeyaml.Yaml;

@UtilityClass
public class ConfigLoader {
    public static Config loadConfig(String fileName) throws Exception {
        Yaml yaml = new Yaml();
        try (InputStream inputStream = ConfigLoader.class.getClassLoader().getResourceAsStream(fileName)) {
            return yaml.loadAs(inputStream, Config.class);
        }
    }
}
