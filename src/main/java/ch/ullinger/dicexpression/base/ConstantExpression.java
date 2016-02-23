package ch.ullinger.dicexpression.base;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by max on 18.02.16.
 */
public class ConstantExpression implements Expression {

    private int value;

    public ConstantExpression(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(final int value) {
        this.value = value;
    }

    @Override
    public int evaluate() {
        return value;
    }

    @Override
    public List<Expression> getSubExpressions() {
        return new ArrayList<Expression>();
    }
}
