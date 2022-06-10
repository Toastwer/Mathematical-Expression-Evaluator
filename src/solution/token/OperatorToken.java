package solution.token;

import solution.exceptions.InvalidTokenConversionException;

public class OperatorToken extends Token {
    public enum Operator {
        PLUS("+"),
        MINUS("-"),
        MULTIPLY("*"),
        DIVIDE("/"),
        LEFT_PARENTHESES("("),
        RIGHT_PARENTHESES(")");

        private final String string;

        Operator(String string) {
            this.string = string;
        }

        public String toString() {
            return string;
        }
    }

    private final Operator operator;

    protected OperatorToken(String operator) {
        for (Operator op : Operator.values()) {
            if (op.toString().equals(operator)) {
                this.operator = op;
                return;
            }
        }

        throw new InvalidTokenConversionException("The operator " + operator + " cannot be converted into a Token.");
    }

    public Operator getOperator() {
        return operator;
    }

    public boolean isOperator(Operator operator) {
        return this.operator == operator;
    }

    public boolean isOperator(Operator... operators) {
        for (Operator operator : operators) {
            if (this.operator == operator)
                return true;
        }

        return false;
    }
}
