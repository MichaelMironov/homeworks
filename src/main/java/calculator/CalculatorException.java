package calculator;

public class CalculatorException extends RuntimeException {

    private static final String MESSAGE = "Ошибка вычисления!";

    public CalculatorException() {
        super(MESSAGE);
    }

    public CalculatorException(String str) {
        super(MESSAGE + " " + str);
    }

    public CalculatorException(Throwable e) {
        super(MESSAGE, e);
    }

}

