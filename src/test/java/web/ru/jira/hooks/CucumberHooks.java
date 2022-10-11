package web.ru.jira.hooks;

import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import web.hooks.WebHooks;
import web.ru.jira.pages.AuthorizationPage;

import static io.qameta.allure.Allure.step;

public class CucumberHooks extends WebHooks {

    @Before
    public void setUp(Scenario scenario) {
        step("Начало сценария: " + scenario.getName(), WebHooks::setConfiguration);
    }

    @After
    public void postCondition(Scenario scenario) {
        step("Окончание сценария: " + scenario.getName(), AuthorizationPage::logOut);
    }

    //    При необходимости можно добавить в степ для скриншота конкретного шага
    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
