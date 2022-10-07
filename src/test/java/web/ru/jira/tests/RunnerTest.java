package web.ru.jira.tests;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty","io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm"},
        glue = {"web.ru.jira.steps", "web.ru.jira.hooks"},
        features = "classpath:features",
        tags = "@ui")
public class RunnerTest {

}

