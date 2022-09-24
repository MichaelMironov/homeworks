package web.ru.jira.tests;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import web.hooks.WebHooks;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"}, glue = {"steps", "hooks"}, tags = "@jira", features = "classpath:features")
public class TestRunner extends WebHooks {

}

