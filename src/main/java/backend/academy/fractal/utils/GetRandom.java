package backend.academy.fractal.utils;

import java.util.List;
import java.util.Random;
import lombok.experimental.UtilityClass;

@UtilityClass
public class GetRandom {
    private static final int MULTIPLICATOR = 10000;

    public static int getRandomWithChances(List<Double> chances) {
        Random rnd = new Random();
        double sum = 0;
        for (double chance : chances) {
            sum += chance;
        }
        for (int i = 0; i < chances.size(); i++) {
            chances.set(i, chances.get(i) / sum);
        }
        int randInt = rnd.nextInt(MULTIPLICATOR);
        int prev = 0;
        for (int i = 0; i < chances.size(); i++) {
            if (prev < randInt && randInt <= prev + chances.get(i) * MULTIPLICATOR) {
                return i;
            }
            prev += (int) (chances.get(i) * MULTIPLICATOR);
        }
        return 0;
    }
}
