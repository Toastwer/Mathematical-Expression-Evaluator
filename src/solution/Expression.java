package solution;

import solution.exceptions.InvalidExpressionException;
import solution.exceptions.InvalidTokenConversionException;
import solution.token.OperatorToken;
import solution.token.OperatorToken.Operator;
import solution.token.Token;
import solution.token.ValueToken;

import java.util.Stack;

public class Expression {
    private final Token[] expression;
    private final double output;

    public Expression(String[] tokens) throws InvalidTokenConversionException, InvalidExpressionException {
        expression = new Token[tokens.length];

        for (int i = 0; i < tokens.length; i++) {
            expression[i] = Token.of(tokens[i]);
        }

        filterInput(expression);
        output = executeInput(0, expression.length);
    }

    private void filterInput(Token[] tokens) {
        for (int i = 0; i < tokens.length; i++) {
            Token token = tokens[i];

            if (token instanceof OperatorToken operatorToken && operatorToken.isOperator(Operator.MINUS)) {
                if (i == 0) {
                    tokens[0] = null;
                    tokens[1].negate();
                } else {
                    Token prevToken = tokens[i - 1];
                    if (prevToken instanceof OperatorToken prevOpToken
                        && prevOpToken.isOperator(Operator.PLUS, Operator.MINUS, Operator.MULTIPLY, Operator.DIVIDE, Operator.LEFT_PARENTHESES)) {
                        tokens[i] = null;
                        tokens[i + 1].negate();
                    }
                }
            }
        }
    }

    private double executeInput(int lowerBound, int upperBound) throws InvalidExpressionException {
        Stack<Integer> newLowerBound = new Stack<>();
        for (int i = lowerBound; i < upperBound; i++) {
            Token token = expression[i];
            if (!(token instanceof OperatorToken operatorToken))
                continue;

            if (operatorToken.isOperator(Operator.LEFT_PARENTHESES)) {
                newLowerBound.add(i);
            } else if (operatorToken.isOperator(Operator.RIGHT_PARENTHESES)) {
                int leftBracketPos = newLowerBound.pop();

                executeInput(leftBracketPos + 1, i);

                if (expression[leftBracketPos].isNegated()) {
                    negateRight(leftBracketPos);
                }

                expression[leftBracketPos] = null;
                expression[i] = null;
            }
        }

        for (int i = lowerBound; i < upperBound; i++) {
            Token token = expression[i];
            if (!(token instanceof OperatorToken operatorToken))
                continue;

            switch (operatorToken.getOperator()) {
                case MULTIPLY -> expression[i] = Token.of(getAndRemoveLeft(i) * getAndRemoveRight(i));
                case DIVIDE -> expression[i] = Token.of(getAndRemoveLeft(i) / getAndRemoveRight(i));
            }
        }

        for (int i = lowerBound; i < upperBound; i++) {
            Token token = expression[i];
            if (!(token instanceof OperatorToken operatorToken))
                continue;

            switch (operatorToken.getOperator()) {
                case PLUS -> expression[i] = Token.of(getAndRemoveLeft(i) + getAndRemoveRight(i));
                case MINUS -> expression[i] = Token.of(getAndRemoveLeft(i) - getAndRemoveRight(i));
            }
        }

        return getRight(0);
    }

    private double getAndRemoveLeft(int relative) throws InvalidExpressionException {
        for (int i = relative; i >= 0; i--) {
            if (expression[i] instanceof ValueToken valueToken) {
                double value = valueToken.getValue();
                expression[i] = null;
                return value;
            }
        }

        throw new InvalidExpressionException("Invalid expression has been given.");
    }

    private double getAndRemoveRight(int relative) throws InvalidExpressionException {
        for (int i = relative; i < expression.length; i++) {
            if (expression[i] instanceof ValueToken valueToken) {
                double value = valueToken.getValue();
                expression[i] = null;
                return value;
            }
        }


        throw new InvalidExpressionException("Invalid expression has been given.");
    }

    private double getRight(int relative) throws InvalidExpressionException {
        for (int i = relative; i < expression.length; i++) {
            if (expression[i] instanceof ValueToken valueToken) {
                return valueToken.getValue();
            }
        }

        throw new InvalidExpressionException("Invalid expression has been given.");
    }

    private void negateRight(int relative) throws InvalidExpressionException {
        for (int i = relative; i < expression.length; i++) {
            if (expression[i] instanceof ValueToken valueToken) {
                valueToken.negate();
                return;
            }
        }

        throw new InvalidExpressionException("Invalid expression has been given.");
    }

    public double getOutput() {
        return output;
    }
}
