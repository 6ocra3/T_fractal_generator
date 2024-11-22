package backend.academy.fractal.utils;

import java.util.List;
import java.util.Random;

public class GetRandom {
    public static int getRandomWithChances(List<Double> chances) {
        Random rnd = new Random();
        int multiplicator = 10000;
        double sum = 0;
        for (double chance : chances) {
            sum += chance;
        }
        for (int i = 0; i < chances.size(); i++) {
            chances.set(i, chances.get(i) / sum);
        }
        int randInt = rnd.nextInt(multiplicator);
        int prev = 0;
        for (int i = 0; i < chances.size(); i++) {
            if (prev < randInt && randInt <= prev + chances.get(i) * multiplicator) {
                return i;
            }
            prev += (int) (chances.get(i) * multiplicator);
        }
        return 0;
    }
}
