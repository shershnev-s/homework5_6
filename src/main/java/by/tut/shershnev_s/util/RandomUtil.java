package by.tut.shershnev_s.util;

import java.util.Random;

public class RandomUtil {
    private static final Random random = new Random();

    public static int getRandom(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }
}
