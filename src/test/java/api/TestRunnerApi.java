package api;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "summary"},
        features = "classpath:features",
        glue = "api.steps",
        tags = "@api"
)
public class TestRunnerApi {

}
