package ch.ullinger.dicexpression.ref;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ch.ullinger.dicexpression.base.Expression;

public class NamedExpressionSingletonStore implements NamedExpressionStore {
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
        if ((exp != null)) {
            HashSet<String> names = new HashSet<String>();
            names.add(name);
            if (isInfiniteLoop(exp, names)) {
                throw new ExpressionLoopException("Loop found when trying to add expression with name '" + name + "'");
            }
            namedExpressions.put(name, exp);
        } else {
            namedExpressions.remove(name);
        }
    }

    private boolean isInfiniteLoop(final Expression exp, final Set<String> refs) {
        if (exp instanceof NamedExpressionReference) {
            NamedExpressionReference reference = (NamedExpressionReference) exp;
            boolean isNew = refs.add(reference.getName());
            if (!isNew) {
                return true;
            } else {
                List<Expression> expressions = reference.getSubExpressions();
                for (Expression expression : expressions) {
                    return isInfiniteLoop(expression, refs);
                }
            }
        } else {
            List<Expression> expressions = exp.getSubExpressions();
            for (Expression expression : expressions) {
                return isInfiniteLoop(expression, refs);
            }
        }

        return false;
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
