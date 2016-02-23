package ch.ullinger.dicexpression.base;

import java.util.List;

public interface Expression {

    public int evaluate();

    public List<Expression> getSubExpressions();
}
