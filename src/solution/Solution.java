package solution;

public class Solution {

    public double solve(String stringToConvert) {
        ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();
        return expressionEvaluator.calculate(stringToConvert);
    }
}
