import calculator.Calculator;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        String[] params = readParams();
        System.out.println(Calculator.execute(params));
    }

    private static String[] readParams() {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите первый операнд: ");
        String operand1 = in.nextLine();
        System.out.print("Введите оператор: ");
        String operator = in.nextLine();
        System.out.print("Введите второй операнд: ");
        String operand2 = in.nextLine();
        return new String[]{operand1, operator, operand2};
    }

}
