package ch.ullinger.dicexpression.ref;

import java.util.HashMap;
import java.util.Map;

import ch.ullinger.dicexpression.base.Expression;

public class NamedExpressionStore {

    private static final NamedExpressionStore instance = new NamedExpressionStore();

    public static NamedExpressionStore getInstance() {
        return instance;
    }

    private Map<String, Expression> namedExpressions = new HashMap<String, Expression>();

    private NamedExpressionStore() {}

    public void addExpressionAlias(final String name, final Expression expression) {
        namedExpressions.put(name, expression);
    }

    public Expression lookupExpression(final String name) {
        Expression expression = namedExpressions.get(name);
        return expression;
    }

    public Map<String, Expression> getNamedExpressions() {
        return namedExpressions;
    }

    public void setNamedExpressions(Map<String, Expression> namedExpressions) {
        this.namedExpressions = namedExpressions;
    }

}
