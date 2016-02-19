package ch.ullinger.dicexpression;

import java.util.ArrayList;
import java.util.List;

public class MinExpression implements Expression {

    private List<Expression> expressions = new ArrayList<Expression>();

    public MinExpression(final Expression... expressions) {
        for (Expression expression : expressions) {
            this.expressions.add(expression);
        }
    }

    public List<Expression> getExpressions() {
        return expressions;
    }

    @Override
    public int evaluate() {
        int result = expressions.get(0).evaluate();

        for (Expression expression : expressions) {
            int evaluation = expression.evaluate();
            if (evaluation < result) {
                result = evaluation;
            }
        }

        return result;
    }

}
