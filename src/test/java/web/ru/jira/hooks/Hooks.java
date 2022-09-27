package web.ru.jira.hooks;

import com.codeborne.selenide.Condition;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static web.ru.jira.steps.AuthorizationPageSteps.*;
import static web.ru.jira.steps.BoardsPageSteps.deleteSuccessfulTask;

public class Hooks {
    @Before
    public void authorizeAs(Scenario scenario) {
//        openAuthorizationPage();
        open("https://edujira.ifellow.ru/secure/");
        logInAs(USER);

        scenario.getName();
    }

    @After
    public void postCondition() {
        deleteSuccessfulTask(false);
        $x("//span[@class='aui-avatar-inner']").shouldBe(Condition.visible).click();
        $x("//a[@id='log_out']").shouldBe(Condition.visible).click();
    }
}
