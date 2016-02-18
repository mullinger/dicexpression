package ch.ullinger.dicexpression.rand;

import java.util.Random;

/**
 * Created by max on 18.02.16.
 */
public class RandomFactory {


    public static Rand getRandom() {
        return getMathRandom();
    }

    private static Rand getMathRandom() {
        return new Rand() {
            private Random rand = new Random();
            @Override
            public int rollDice(int sides) {
                return rand.nextInt(sides)+1;
            }
        };
    }
}
