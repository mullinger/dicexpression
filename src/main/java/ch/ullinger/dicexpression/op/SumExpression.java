package ch.ullinger.dicexpression.op;

import java.util.ArrayList;
import java.util.List;

import ch.ullinger.dicexpression.base.Expression;

/**
 * Created by max on 18.02.16.
 */
public class SumExpression implements Expression {

    private List<Expression> expressions = new ArrayList<Expression>();

    public SumExpression(final Expression... expressions) {
        for (Expression exp : expressions) {
            this.expressions.add(exp);
        }
    }

    public List<Expression> getExpressions() {
        return expressions;
    }

    public void setExpressions(final List<Expression> expressions) {
        this.expressions = expressions;
    }

    @Override
    public int evaluate() {
        int sum = 0;

        for (Expression exp : expressions) {
            sum += exp.evaluate();
        }

        return sum;
    }

    @Override
    public List<Expression> getSubExpressions() {
        return expressions;
    }
}
