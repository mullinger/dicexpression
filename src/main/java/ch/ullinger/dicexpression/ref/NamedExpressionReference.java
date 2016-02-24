package ch.ullinger.dicexpression.ref;

import java.util.ArrayList;
import java.util.List;

import ch.ullinger.dicexpression.base.Expression;
import ch.ullinger.dicexpression.exception.UnknownReferenceException;

public class NamedExpressionReference implements Expression {

    private String name;

    public NamedExpressionReference(final String name) throws UnknownReferenceException {
        setName(name);
    }


    @Override
    public int evaluate() {
        Expression expression = NamedExpressionStoreFactory.getInstance().getExpression(name);

        int result = 0;
        if (expression != null) {
            result = expression.evaluate();
        }

        return result;
    }

    public String getName() {
        return name;
    }


    public void setName(final String name) throws UnknownReferenceException {
        Expression expression = NamedExpressionStoreFactory.getInstance().getExpression(name);
        if (expression != null) {
            this.name = name;
        } else {
            throw new UnknownReferenceException("Unknown Expression Reference '" + name + "'");
        }
    }


    @Override
    public List<Expression> getSubExpressions() {
        Expression expression = NamedExpressionStoreFactory.getInstance().getExpression(name);
        ArrayList<Expression> list = new ArrayList<Expression>();
        list.add(expression);
        return list;
    }

}
