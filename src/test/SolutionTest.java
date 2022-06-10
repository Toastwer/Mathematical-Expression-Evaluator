package test;

import org.junit.jupiter.api.Test;
import solution.Solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolutionTest {
    Solution solution = new Solution();

    @Test
    public void testAddition() {
        assertEquals(2d, solution.solve("1+1"), 0.01);
    }

    @Test
    public void testSubtraction() {
        assertEquals(0d, solution.solve("1 - 1"), 0.01);
    }

    @Test
    public void testMultiplication() {
        assertEquals(1d, solution.solve("1* 1"), 0.01);
    }

    @Test
    public void testDivision() {
        assertEquals(1d, solution.solve("1 /1"), 0.01);
    }

    @Test
    public void testNegative() {
        assertEquals(-123d, solution.solve("-123"), 0.01);
    }

    @Test
    public void testLiteral() {
        assertEquals(123d, solution.solve("123"), 0.01);
    }

    @Test
    public void testExpression() {
        assertEquals(21.25, solution.solve("2 /2+3 * 4.75- -6"), 0.01);
        assertEquals(7, solution.solve("1 + 2 * 3"), 0.01);
        assertEquals(1476d, solution.solve("12* 123"), 0.01);
    }

    @Test
    public void testComplex() {
        assertEquals(7.732, solution.solve("2 / (2 + 3) * 4.33 - -6"), 0.01);
        assertEquals(7.732, solution.solve("2 / (2 + (3 * 3 - 6)) * 4.33 - -6"), 0.01);
        assertEquals(492, solution.solve("12*123/-(-5+2)"), 0.01);
        assertEquals(3, solution.solve("(1-2)+-(-(-(-4)))"), 0.01);
        assertEquals(3, solution.solve("(1-2)+-(-4)"), 0.01);
        assertEquals(-3, solution.solve("1 - -(-(-(-4)))"), 0.01);
    }

    @Test
    public void testLargeComplex() {
        assertEquals(1, solution.solve(
                "(123.45*(678.90 / (-2.5+ 11.5)-(((80 -(19))) *33.25)) / 20) - (123.45*(678.90 / (-2.5+ 11.5)-(((80 -(19))) *33.25)) / 20) + (13 - 2)/ -(-11)"
        ), 0.01);
    }
}