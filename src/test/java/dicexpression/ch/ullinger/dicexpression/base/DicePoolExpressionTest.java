package dicexpression.ch.ullinger.dicexpression.base;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ch.ullinger.dicexpression.base.DicePoolExpression;
import ch.ullinger.dicexpression.util.E;

/**
 * Created by max on 18.02.16.
 */
public class DicePoolExpressionTest {

    @Test
    public void testSingleDie() {

        DicePoolExpression dicePool = E.D(1, 1);

        int evaluate = dicePool.evaluate();

        assertEquals(1, evaluate);
    }

    @Test
    public void testMultipleOneSidedDice() {
        DicePoolExpression dicePool = E.D(5, 1);

        int evaluate = dicePool.evaluate();

        assertEquals(5, evaluate);
    }


    @Test
    public void testDicePool() {
        DicePoolExpression dicePool = E.D(2, 20);

        for (int i = 0; i < 20; i++) {
            int evaluate = dicePool.evaluate();
            System.out.print(evaluate + ",");
            assertTrue(evaluate >= 2);
            assertTrue(evaluate <= 40);
        }
    }


    @Test(timeout = 10000)
    public void testDicePoolReachesExpectedMax() {
        DicePoolExpression dicePool = E.D(2, 20);

        int evaluate;
        do {
            evaluate = dicePool.evaluate();
            System.out.print(evaluate + ",");
            assertTrue(evaluate >= 2);
            assertTrue(evaluate <= 40);
        } while (evaluate != 40);
    }

    @Test(timeout = 10000)
    public void testDicePoolReachesExpectedMin() {
        DicePoolExpression dicePool = E.D(2, 20);

        int evaluate;
        do {
            evaluate = dicePool.evaluate();
            System.out.print(evaluate + ",");
            assertTrue(evaluate >= 2);
            assertTrue(evaluate <= 40);
        } while (evaluate != 2);
    }
}
