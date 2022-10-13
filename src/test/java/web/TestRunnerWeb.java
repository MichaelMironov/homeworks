package web;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "summary"},
        monochrome = true,
        glue = {"web.ru.jira.steps", "web.ru.jira.hooks"},
        features = "classpath:features",
        tags = "@ui"
)
public class TestRunnerWeb {

}

