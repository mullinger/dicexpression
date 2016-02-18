package ch.ullinger.dicexpression;

/**
 * Created by max on 18.02.16.
 */
public class ConstantExpression implements Expression {

    private int value;

    public ConstantExpression(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public int evaluate() {
        return value;
    }
}
