import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.sleep;
import static web.ru.jira.elements.NavigationPanel.*;
import static web.ru.jira.steps.AuthorizationPageSteps.*;
import static web.ru.jira.steps.TasksPageSteps.*;

public class BaseTest {
    @Test
    public void Test1(){

        openAuthorizationPage();
        logInAs(USER);

        selectMenuSubsectionByText(TASKS,"Поиск задач");
        totalCountTasks();

        filterTasksByText("TestSelenium");

        openTaskWithId(21966);
        checkFixIn("2.0");

        openTaskWithId(21967);
        checkFixIn("2.0");


        sleep(2000);

    }
}
