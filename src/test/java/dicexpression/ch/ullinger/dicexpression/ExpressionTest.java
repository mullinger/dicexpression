package dicexpression.ch.ullinger.dicexpression;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ch.ullinger.dicexpression.base.Expression;

public class ExpressionTest {


    @Test
    public void test() {
        Expression exp = new Expression() {
            @Override
            public int evaluate() {
                return 0;
            }
        };

        assertEquals(0, exp.evaluate());
    }
}
