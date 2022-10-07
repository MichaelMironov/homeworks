package web.ru.jira.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import web.hooks.WebHooks;

import static web.ru.jira.pages.AuthorizationPage.logOut;

public class CucumberHooks {
    @Before()
    public void setUp(Scenario scenario) {
        scenario.log("Начало сценария: " + scenario.getName());
        WebHooks.setConfiguration();
    }

    @After
    public void postCondition(Scenario scenario) {
        scenario.log("Окончание сценария: " + scenario.getName());
        logOut();
    }
}
