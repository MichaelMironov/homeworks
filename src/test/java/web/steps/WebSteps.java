package web.steps;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import static com.codeborne.selenide.Selenide.$x;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WebSteps {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSteps.class.getSimpleName());
    private static int COUNT = 1;

    @Given("I open {string}")
    public void open(String url){
        Selenide.open(url);
    }

    @When("I {double} {string} {double}")
    public void calculate(double operand1, String operator, double operand2){
        LOGGER.info("Тестовые данные #{}: Оператор: \"{}\". Операнды = {} и {}" , COUNT++ ,operator, operand1, operand2);

        guiOperandInput(operand1);

        selectOperator(operator);

        guiOperandInput(operand2);

        $x("//div[@aria-label=\"равно\"]").click();

    }

    private void guiOperandInput(double operand){

        char[] operandArray = String.valueOf(operand).toCharArray();

        for(char num : operandArray ){
            if(num == '.'){
                $x("//div[@aria-label=\"запятая\"]").click();
            }
            $x("//div[@role='button' and text() = "+num+"]").click();
        }
    }

    @Then("I get {double} as a result")
    public void getNumResult(double expected){
        String actual = $x("//span[@id=\"cwos\"]").innerText();

        LOGGER.info("Ожидаемый результат = {}. Фактический результат = {}", expected, actual);

        assertEquals(expected, Double.parseDouble(actual));
    }

    @Then("I get {string} as a result")
    public void getStringResult(String expected){
        String actual = $x("//span[@id=\"cwos\"]").innerText();

        LOGGER.info("Ожидаемый результат = {}. Фактический результат = {}", expected, actual);

        assertEquals(expected, actual);
    }

    private void selectOperator(String operator){
        switch (operator){
            case "/": $x("//div[@aria-label=\"деление\"]").click(); break;
            case "*": $x("//div[@aria-label=\"умножение\"]").click(); break;
            case "+": $x("//div[@aria-label=\"сложение\"]").click(); break;
            case "-": $x("//div[@aria-label=\"вычитание\"]").click(); break;
        }
    }

}
