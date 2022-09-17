package unit.tests;

import calculator.Calculator;
import calculator.CalculatorException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class NegativeCalculatorTest {

    @ParameterizedTest
    @MethodSource("negativeData")
    public void negativeTest(String operator, String operand1, String operand2) {
        RuntimeException exception = assertThrows(CalculatorException.class, () -> {
            String[] array = {operand1, operator, operand2};
            Calculator.execute(array);
        });
    }

    public static Object[][] negativeData() {

        return new Object[][]{
                {"one", "+", "two"},
                {"2", "a", "3"},
                {"5.1", "", ""},
                {"5.1", "*", null},
                {"4", "1", "4"},
                {"2", "@", "3"},
                {"2", "/", "0"},
                {"2147483648", "*", "2"},
                {"2147483643", "*", "2"},
                {"100", "+", "-2147483649"},
                {"-2147483648", "-", "100"}
        };
    }
}
