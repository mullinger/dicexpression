package ch.ullinger.dicexpression.ref;

import java.util.List;

import ch.ullinger.dicexpression.base.Expression;

public interface NamedExpressionStore {

    public void addExpression(String name, Expression exp) throws ExpressionLoopException;

    public Expression getExpression(String name);

    public List<Expression> getAllExpressions();

    public void clearExpressions();
}
