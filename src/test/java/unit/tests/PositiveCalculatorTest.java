package unit.tests;

import calculator.Calculator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PositiveCalculatorTest {

    @ParameterizedTest
    @MethodSource("positiveData")
    public void positiveTest(String operand1, String operator, String operand2, double expectedResult) {
        String[] array = {operand1, operator, operand2};
        assertEquals(Double.parseDouble(Calculator.execute(array)), expectedResult, 0.001);
    }

    public static Object[][] positiveData() {
        return new Object[][]{
                {"2147483647", "+", "0", Integer.MAX_VALUE},
                {"5", "*", "0", 0.0},
                {"10", "/", "5.0", 2.0},
                {"-2147483648", "-" , "0", Integer.MIN_VALUE},
                {"2.0", "-", "1.1", 0.9},
        };
    }

}
