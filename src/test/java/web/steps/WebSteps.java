package web.steps;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import static com.codeborne.selenide.Selenide.$x;

public class WebSteps {

    @Given("I open {string}")
    public void open(String url){
        Selenide.open(url);
    }

    @When("I divide {int} by {int}")
    public void divide(String operand1, String operand2){

        guiInput(operand1);

        $x("//div[@aria-label=\"деление\"]").click();

        guiInput(operand2);

    }

    private void guiInput(String operand){

        char[] firstOperandArray = operand.toCharArray();

        for(char num : firstOperandArray ){
            $x("//div[@role='button' and text() = "+num+"]").click();
        }
    }

}
