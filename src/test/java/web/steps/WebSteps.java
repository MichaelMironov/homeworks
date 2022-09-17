package web.steps;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.codeborne.selenide.Selenide.$x;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WebSteps {

    @Given("I open {string}")
    public void open(String url){
        Selenide.open(url);
    }

    @When("I {double} {string} {double}")
    public void calculate(double operand1, String operator, double operand2){

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

        assertEquals(expected, Double.parseDouble(actual));
    }

    @Then("I get {string} as a result")
    public void getStringResult(String expected){
        String actual = $x("//span[@id=\"cwos\"]").innerText();

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
