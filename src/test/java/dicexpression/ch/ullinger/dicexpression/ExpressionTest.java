package dicexpression.ch.ullinger.dicexpression;


import static org.junit.Assert.assertEquals;

import java.util.List;

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

            @Override
            public List<Expression> getSubExpressions() {
                return null;
            }
        };

        assertEquals(0, exp.evaluate());
    }
}
