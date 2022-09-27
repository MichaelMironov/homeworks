package web.ru.jira.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;

import static web.ru.jira.hooks.WebHooks.setConfiguration;
import static web.ru.jira.steps.AuthorizationPageSteps.logOut;
import static web.ru.jira.steps.BoardsPageSteps.deleteSuccessfulTask;

public class CucumberHooks {
    @Before()
    public static void setUp() {
        setConfiguration();
    }

    @After
    public static void postCondition() {
        deleteSuccessfulTask(false);
        logOut();
    }
}
