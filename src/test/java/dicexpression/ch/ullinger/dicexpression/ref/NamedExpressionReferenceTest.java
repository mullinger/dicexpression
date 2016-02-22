package dicexpression.ch.ullinger.dicexpression.ref;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ch.ullinger.dicexpression.base.ConstantExpression;
import ch.ullinger.dicexpression.op.MaxExpression;
import ch.ullinger.dicexpression.op.SumExpression;
import ch.ullinger.dicexpression.ref.NamedExpressionReference;
import ch.ullinger.dicexpression.ref.NamedExpressionStore;

public class NamedExpressionReferenceTest {

    private ConstantExpression str = new ConstantExpression(3);

    @Before
    public void before() {
        NamedExpressionStore store = NamedExpressionStore.getInstance();
        store.getNamedExpressions().clear();

        store.addExpressionAlias("str", str);
    }

    @Test
    public void testDefault() {
        assertEquals(1, NamedExpressionStore.getInstance().getNamedExpressions().size());

        NamedExpressionReference expressionReference = new NamedExpressionReference("str");

        assertEquals(3, expressionReference.evaluate());
    }


    @Test
    public void testAddExpression() {
        NamedExpressionStore.getInstance().addExpressionAlias("dex", new ConstantExpression(5));

        assertEquals(5, new NamedExpressionReference("dex").evaluate());
    }

    @Test
    public void testReferenceInExpression() {
        NamedExpressionStore.getInstance().addExpressionAlias("dex", new ConstantExpression(5));
        MaxExpression max = new MaxExpression(new NamedExpressionReference("dex"), new NamedExpressionReference("str"));
        NamedExpressionStore.getInstance().addExpressionAlias("max", max);

        SumExpression sum = new SumExpression(new NamedExpressionReference("max"), new NamedExpressionReference("str"));

        assertEquals(5, max.evaluate());
        assertEquals(5, NamedExpressionStore.getInstance().lookupExpression("max").evaluate());
        assertEquals(8, sum.evaluate());
    }
}
