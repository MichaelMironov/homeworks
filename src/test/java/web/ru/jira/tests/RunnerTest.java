package web.ru.jira.tests;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {"web.ru.jira.steps", "web.ru.jira.hooks"},
        features = "classpath:features",
        plugin = {"pretty"},
        tags = "@jira")
public class RunnerTest {

}

