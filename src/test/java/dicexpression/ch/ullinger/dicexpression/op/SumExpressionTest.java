package dicexpression.ch.ullinger.dicexpression.op;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ch.ullinger.dicexpression.base.ConstantExpression;
import ch.ullinger.dicexpression.base.DicePoolExpression;
import ch.ullinger.dicexpression.op.SumExpression;

/**
 * Created by max on 18.02.16.
 */
public class SumExpressionTest {


    @Test
    public void testSimpleSum() throws Exception {
        SumExpression sumExpression = new SumExpression();

        assertEquals(0, sumExpression.evaluate());

    }

    @Test
    public void testSingleConsantSum() throws Exception {
        SumExpression sumExpression = new SumExpression(new ConstantExpression(3));

        assertEquals(3, sumExpression.evaluate());
    }


    @Test
    public void testMultipleConsantSum() throws Exception {
        SumExpression sumExpression =
                new SumExpression(new ConstantExpression(1), new ConstantExpression(-2), new ConstantExpression(3));

        assertEquals(2, sumExpression.evaluate());
    }


    @Test(timeout = 10000)
    public void testDiceSum() throws Exception {
        SumExpression sumExpression = new SumExpression(new DicePoolExpression(5, 6), new DicePoolExpression(2, 8));

        boolean hasReachedMax = false;
        boolean hasReachedMin = false;

        int min = 7;
        int max = 46;

        int count = 0;
        do {
            int result = sumExpression.evaluate();

            hasReachedMin |= result == min;
            hasReachedMax |= result == max;

            assertTrue(result >= min);
            assertTrue(result <= max);
            count++;
        } while (!hasReachedMax && !hasReachedMin);

        System.out.println("Reached max and min after " + count + " iterations");
    }
}
