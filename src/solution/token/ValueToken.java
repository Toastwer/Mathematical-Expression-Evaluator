package solution.token;

public class ValueToken extends Token {
    private double value;

    protected ValueToken(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public void negate() {
        super.negate();
        value *= -1;
    }
}
