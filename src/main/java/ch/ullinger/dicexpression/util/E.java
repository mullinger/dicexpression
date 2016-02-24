package ch.ullinger.dicexpression.util;

import ch.ullinger.dicexpression.base.ConstantExpression;
import ch.ullinger.dicexpression.base.DicePoolExpression;
import ch.ullinger.dicexpression.base.Expression;
import ch.ullinger.dicexpression.op.MaxExpression;
import ch.ullinger.dicexpression.op.MinExpression;
import ch.ullinger.dicexpression.op.SumExpression;
import ch.ullinger.dicexpression.ref.NamedExpressionReference;


public class E/* xpression */ {


    public static ConstantExpression C(final int value) {
        return new ConstantExpression(value);
    }

    public static DicePoolExpression D(final int num, final int sides) {
        return new DicePoolExpression(num, sides);
    }


    public static MaxExpression max(final Expression... expressions) {
        return new MaxExpression(expressions);
    }

    public static MinExpression min(final Expression... expressions) {
        return new MinExpression(expressions);
    }

    public static SumExpression sum(final Expression... expressions) {
        return new SumExpression(expressions);
    }

    public static NamedExpressionReference ref(final String name) {
        return new NamedExpressionReference(name);
    }

}
