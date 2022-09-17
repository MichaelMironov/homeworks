package calculator;

import java.util.regex.Pattern;

public class Calculator {

    public static String execute(String[] params) {

        for (String elem : params)
            if (elem == null || elem.isEmpty() || elem.trim().length() == 0)
                throw new CalculatorException("Значение операнда не может быть пустым или null!");
        if (Pattern.matches("\\D", params[0]) || Pattern.matches("\\D", params[2]))
            throw new CalculatorException("Операнды должны быть числом!");

        String operator = params[1];
        double operand1;
        double operand2;

        operand1 = Double.parseDouble(params[0]);
        operand2 = Double.parseDouble(params[2]);
        if ((operand1 > Integer.MAX_VALUE || operand1 < Integer.MIN_VALUE)
                || (operand2 > Integer.MAX_VALUE || operand2 < Integer.MIN_VALUE)) {
            throw new CalculatorException("Превышен порог значений операндов!");
        }
        double result = calculate(operator, operand1, operand2);
        if (result > Double.MAX_VALUE || result < Double.MIN_VALUE) {
            throw new CalculatorException("Превышен порог значения результата!");
        }
        return String.valueOf(result);

    }

    private static double calculate(String operator, double a, double b) {
        switch (operator) {
            case "+":
                return add(a, b);
            case "-":
                return subst(a, b);
            case "*":
                return mult(a, b);
            case "/":
                return div(a, b);
        }
        throw new CalculatorException("Неизвестный оператор!");
    }

    private static double add(double a, double b) {
        return a + b;
    }

    private static double subst(double a, double b) {
        return a - b;
    }

    private static double div(double a, double b) {
        if (b == 0) {
            throw new CalculatorException("Деление на ноль запрещено!");
        }
        return a / b;
    }

    private static double mult(double a, double b) {
        return a * b;
    }

}
