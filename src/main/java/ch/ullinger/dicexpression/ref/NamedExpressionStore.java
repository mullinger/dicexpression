package ch.ullinger.dicexpression.ref;

import java.util.List;

import ch.ullinger.dicexpression.base.Expression;
import ch.ullinger.dicexpression.exception.ExpressionLoopException;

public abstract class NamedExpressionStore {

    public abstract void addExpression(String name, Expression exp) throws ExpressionLoopException;

    public abstract Expression getExpression(String name);

    public abstract List<Expression> getAllExpressions();

    public abstract void clearExpressions();


    protected boolean isInfiniteLoop(final Expression exp, final String ref) {
        if (exp instanceof NamedExpressionReference) {
            NamedExpressionReference reference = (NamedExpressionReference) exp;
            if (ref.equals(reference.getName())) {
                return true;
            } else {
                List<Expression> expressions = reference.getSubExpressions();
                for (Expression expression : expressions) {
                    return isInfiniteLoop(expression, ref);
                }
            }
        } else {
            List<Expression> expressions = exp.getSubExpressions();
            for (Expression expression : expressions) {
                if (isInfiniteLoop(expression, ref)) {
                    return true;
                };
            }
        }

        return false;
    }
}
