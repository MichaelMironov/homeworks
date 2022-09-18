package unit.tests;

import calculator.Calculator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import web.steps.WebSteps;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PositiveCalculatorTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(PositiveCalculatorTest.class.getSimpleName());
    private static int COUNT = 1;

    @ParameterizedTest
    @MethodSource("positiveData")
    public void positiveTest(String operand1, String operator, String operand2, double expectedResult) {
        String[] array = {operand1, operator, operand2};

        LOGGER.info("Тестовые данные #{}: Оператор: \"{}\". Операнды = {} и {}" , COUNT++ ,operator, operand1, operand2);

        double actualResult = Double.parseDouble(Calculator.execute(array));

        LOGGER.info("Ожидаемый результат = {}. Фактический результат = {}", expectedResult, actualResult);

        assertEquals(expectedResult, actualResult, 0.001);

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
