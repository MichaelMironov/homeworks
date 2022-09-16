package web.steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WebSteps {

    @Given("I open {string}")
    public void open(String url){
        Selenide.open(url);
    }

    @When("I divide {double} by {double}")
    public void divide(double operand1, double operand2){

        guiInput(operand1);

        $x("//div[@aria-label=\"деление\"]").click();

        guiInput(operand2);

        $x("//div[@aria-label=\"равно\"]").click();

    }

    private void guiInput(double operand){

        String temp = String.valueOf(operand);

        char[] firstOperandArray = temp.toCharArray();

        for(char num : firstOperandArray ){
            if(num == '.'){
                $x("//div[@aria-label=\"запятая\"]").click();
            }
            $x("//div[@role='button' and text() = "+num+"]").click();
        }
    }

    @Then("I get {double} as a result")
    public void result(double expected){
        String actual = $x("//span[@id=\"cwos\"]").innerText();

        assertEquals(expected, Double.parseDouble(actual));
    }
}
