package solution.token;

import solution.exceptions.InvalidTokenConversionException;

public abstract class Token {
    private boolean negated = false;

    public void negate() {
        negated = true;
    }

    public boolean isNegated() {
        return negated;
    }

    public static Token of(double input) {
        return new ValueToken(input);
    }

    public static Token of(String input) throws InvalidTokenConversionException {
        if (input.matches("-?\\d+(.\\d+)?")) {
            return new ValueToken(Double.parseDouble(input));
        } else if (input.matches("[-+/*()]")) {
            return new OperatorToken(input);
        } else {
            throw new InvalidTokenConversionException("The input " + input + " cannot be converted into a Token.");
        }
    }
}