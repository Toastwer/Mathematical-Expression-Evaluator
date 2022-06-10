package solution;

import solution.exceptions.InvalidExpressionException;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionEvaluator {

    public double calculate(String input) throws InvalidExpressionException {
        String[] tokens = tokenize(input);
        Expression instruction = new Expression(tokens);
        return instruction.getOutput();
    }

    private static String[] tokenize(String input) {
        Pattern pattern = Pattern.compile("=>|[-+*/%=()]|[A-Za-z_][A-Za-z0-9_]*|[0-9]*(\\.?[0-9]+)");
        Matcher matcher = pattern.matcher(input);

        Stack<String> tokens = new Stack<>();
        while (matcher.find()) {
            tokens.add(matcher.group());
        }
        return tokens.toArray(new String[0]);
    }
}
