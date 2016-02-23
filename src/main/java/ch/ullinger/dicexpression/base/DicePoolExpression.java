package ch.ullinger.dicexpression.base;

import java.util.ArrayList;
import java.util.List;

import ch.ullinger.dicexpression.rand.Rand;
import ch.ullinger.dicexpression.rand.RandomFactory;

/**
 * Created by max on 18.02.16.
 */
public class DicePoolExpression implements Expression {

    private final int count;
    private final int sides;

    private Rand random = RandomFactory.getRandom();

    public DicePoolExpression(final int count, final int sides) {
        this.count = count;
        this.sides = sides;
    }

    public int getCount() {
        return count;
    }

    public int getSides() {
        return sides;
    }

    @Override
    public int evaluate() {
        int sum = 0;

        for (int i = 0; i < count; i++) {
            sum += random.rollDice(sides);
        }

        return sum;
    }

    @Override
    public List<Expression> getSubExpressions() {
        return new ArrayList<Expression>();
    }
}
