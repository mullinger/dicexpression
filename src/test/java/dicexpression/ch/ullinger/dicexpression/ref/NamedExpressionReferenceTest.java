package dicexpression.ch.ullinger.dicexpression.ref;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ch.ullinger.dicexpression.base.ConstantExpression;
import ch.ullinger.dicexpression.base.DicePoolExpression;
import ch.ullinger.dicexpression.exception.ExpressionLoopException;
import ch.ullinger.dicexpression.exception.UnknownReferenceException;
import ch.ullinger.dicexpression.op.MaxExpression;
import ch.ullinger.dicexpression.op.SumExpression;
import ch.ullinger.dicexpression.ref.NamedExpressionReference;
import ch.ullinger.dicexpression.ref.NamedExpressionSingletonStore;
import ch.ullinger.dicexpression.ref.NamedExpressionStore;
import ch.ullinger.dicexpression.ref.NamedExpressionStoreFactory;

public class NamedExpressionReferenceTest {

    private ConstantExpression str = new ConstantExpression(3);

    @Before
    public void before() throws ExpressionLoopException {
        NamedExpressionStoreFactory.setStore(NamedExpressionSingletonStore.getInstance());
        NamedExpressionStore store = NamedExpressionStoreFactory.getInstance();
        store.clearExpressions();

        store.addExpression("str", str);
    }

    @Test
    public void testDefault() throws UnknownReferenceException {
        assertEquals(1, NamedExpressionStoreFactory.getInstance().getAllExpressions().size());

        NamedExpressionReference expressionReference = new NamedExpressionReference("str");

        assertEquals(3, expressionReference.evaluate());
    }


    @Test
    public void testAddExpression() throws ExpressionLoopException, UnknownReferenceException {
        NamedExpressionStoreFactory.getInstance().addExpression("dex", new ConstantExpression(5));

        assertEquals(5, new NamedExpressionReference("dex").evaluate());
    }

    @Test
    public void testReferenceInExpression() throws ExpressionLoopException, UnknownReferenceException {
        NamedExpressionStoreFactory.getInstance().addExpression("dex", new ConstantExpression(5));
        MaxExpression max = new MaxExpression(new NamedExpressionReference("dex"), new NamedExpressionReference("str"));
        NamedExpressionStoreFactory.getInstance().addExpression("max", max);

        SumExpression sum = new SumExpression(new NamedExpressionReference("max"), new NamedExpressionReference("str"));

        assertEquals(5, max.evaluate());
        assertEquals(5, NamedExpressionStoreFactory.getInstance().getExpression("max").evaluate());
        assertEquals(8, sum.evaluate());
    }

    @Test(timeout = 1000, expected = UnknownReferenceException.class)
    public void simpleLoopTest() throws ExpressionLoopException, UnknownReferenceException {
        NamedExpressionStore store = NamedExpressionStoreFactory.getInstance();
        NamedExpressionReference ref = new NamedExpressionReference("1");
        store.addExpression("1", ref);

        ref.evaluate();
    }

    @Test(timeout = 1000, expected = ExpressionLoopException.class)
    public void moreLoopTest() throws ExpressionLoopException, UnknownReferenceException {
        NamedExpressionStore store = NamedExpressionStoreFactory.getInstance();

        store.addExpression("const", new ConstantExpression(0));
        MaxExpression advantage = new MaxExpression(
                new SumExpression(new DicePoolExpression(1, 20), new NamedExpressionReference("const")),
                new SumExpression(new DicePoolExpression(1, 20), new NamedExpressionReference("const")));
        store.addExpression("advantage", advantage);

        System.out.println(advantage.evaluate());

        store.addExpression("const", advantage);

        System.out.println(advantage.evaluate());
    }


    @Test(expected = UnknownReferenceException.class)
    public void testCanNotMakeMissingReference() throws UnknownReferenceException {
        new NamedExpressionReference("foo-shizzle-bar");
    }
}
