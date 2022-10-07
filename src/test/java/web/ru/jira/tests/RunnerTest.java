package web.ru.jira.tests;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {"ru.ifellow.web.ru.jira.steps", "ru.ifellow.web.ru.jira.hooks"},
        features = "classpath:features",
        plugin = {"pretty"},
        tags = "@jira")
public class RunnerTest {

}

