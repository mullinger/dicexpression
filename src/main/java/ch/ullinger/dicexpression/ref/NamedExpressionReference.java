package ch.ullinger.dicexpression.ref;

import java.util.ArrayList;
import java.util.List;

import ch.ullinger.dicexpression.base.Expression;

public class NamedExpressionReference implements Expression {

    private String name;

    public NamedExpressionReference(final String name) {
        this.name = name;
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


    public void setName(final String name) {
        this.name = name;
    }


    @Override
    public List<Expression> getSubExpressions() {
        Expression expression = NamedExpressionStoreFactory.getInstance().getExpression(name);
        ArrayList<Expression> list = new ArrayList<Expression>();
        list.add(expression);
        return list;
    }

}
