import api.TestRunnerApi;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import web.TestRunnerWeb;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TestRunnerApi.class,
        TestRunnerWeb.class
})
public class TestSuiteProject {
}