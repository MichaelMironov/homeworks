package web.ru.jira.steps;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"},
        glue = {"steps", "hooks"},
        features = "classpath:features")
public class RunnerTest {

}

