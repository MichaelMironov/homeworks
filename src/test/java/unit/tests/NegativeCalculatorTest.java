package unit.tests;

import calculator.Calculator;
import calculator.CalculatorException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class NegativeCalculatorTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(NegativeCalculatorTest.class.getSimpleName());
    private static int COUNT = 1;

    @ParameterizedTest
    @MethodSource("negativeData")
    public void negativeTest(String operand1, String operator, String operand2) {

        LOGGER.info("Тестовые данные #{}: Оператор: \"{}\". Операнды = {} и {}", COUNT++, operator, operand1, operand2);

        RuntimeException exception = assertThrows(CalculatorException.class, () -> {
            String[] array = {operand1, operator, operand2};
            Calculator.execute(array);
        });

        LOGGER.info("Ожидаемое исключение - {}. Сообщение: {}", CalculatorException.class.getSimpleName(), exception.getMessage());

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
