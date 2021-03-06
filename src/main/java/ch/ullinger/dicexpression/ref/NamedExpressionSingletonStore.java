package ch.ullinger.dicexpression.ref;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ch.ullinger.dicexpression.base.Expression;
import ch.ullinger.dicexpression.exception.ExpressionLoopException;

public class NamedExpressionSingletonStore extends NamedExpressionStore {
    private static final NamedExpressionStore instance = new NamedExpressionSingletonStore();

    public static NamedExpressionStore getInstance() {
        return instance;
    }

    private Map<String, Expression> namedExpressions = new HashMap<String, Expression>();

    private NamedExpressionSingletonStore() {}

    public Map<String, Expression> getNamedExpressions() {
        return namedExpressions;
    }

    public void setNamedExpressions(final Map<String, Expression> namedExpressions) {
        this.namedExpressions = namedExpressions;
    }

    @Override
    public void addExpression(final String name, final Expression exp) throws ExpressionLoopException {
        Expression oldValue = namedExpressions.get(name);

        if ((exp != null)) {
            namedExpressions.put(name, exp);
            if (isInfiniteLoop(exp, name)) {
                namedExpressions.put(name, oldValue);
                throw new ExpressionLoopException("Loop found when trying to add expression with name '" + name + "'");
            }
        } else {
            namedExpressions.remove(name);
        }
    }


    @Override
    public Expression getExpression(final String name) {
        return namedExpressions.get(name);
    }

    @Override
    public List<Expression> getAllExpressions() {
        ArrayList<Expression> list = new ArrayList<Expression>();
        list.addAll(namedExpressions.values());
        return list;
    }

    @Override
    public void clearExpressions() {
        namedExpressions.clear();
    }
}
