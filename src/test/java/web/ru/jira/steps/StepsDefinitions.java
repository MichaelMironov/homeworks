package web.ru.jira.steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.ru.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import web.ru.jira.models.Task;

import static com.codeborne.selenide.Selenide.*;
import static web.ru.jira.elements.NavigationPanel.*;
import static web.ru.jira.pages.TaskPage.createNewTask;
import static web.ru.jira.steps.AuthorizationPageSteps.logInAs;
import static web.ru.jira.steps.AuthorizationPageSteps.openAuthorizationPage;
import static web.ru.jira.steps.BoardsPageSteps.*;
import static web.ru.jira.steps.TasksPageSteps.*;

public class StepsDefinitions {

    private static final Logger LOGGER = LogManager.getLogger(StepsDefinitions.class);

    @Дано("^пользователь авторизован в системе как ([^\"]*)$")
    public static void authorizeAs(String user) {
        openAuthorizationPage();
        logInAs(user);
    }

    @Тогда("^в разделе ([^\"]*) выбрать подраздел ([^\"]*)$")
    public static void chooseSubMenu(String menu, String submenu) {
        SelenideElement elem = null;

        switch (menu) {
            case "Задачи":
                elem = TASKS;
        }

        selectMenuSubsectionByText(elem, submenu);
    }

    @И("^проверить общее количество заведенных задач в проекте$")
    public static void tasksCount() {
        totalCountTasks();
    }

    @Затем("^перейти в задачу с названием - ([^\"]*) и id - (\\d+)$")
    public static void goToTask(String title, int id) {
        filterTasksByText(title);
        openTaskWithId(id);
    }

    @И("^статус задачи - ([^\"]*), исправить в версиях - ([^\"]*)$")
    public static void checkTask(String status, String version) {
        statusShouldBe(status);
        checkFixIn(version);
    }

    @Затем("^создать новую задачу с типом ([^\"]*), именем ([^\"]*) и описанием ([^\"]*)$")
    public static void createTask(String type, String title, String description) {
        clickToCreateTask();
        newTask = createNewTask(title, type, description);
    }

    @И("^перенести новую задачу на скрам-доске в колонку ([^\"]*)$")
    public static void movingTo(String column) {

        String temp = column;

        switch (column) {
            case "Выполнено":
                column = DONE;
                break;
            case "В работе":
                column = IN_WORK;
                break;
        }

        selectMenuSubsectionByText(TASKS, "Поиск задач");

        filterTasksByText(newTask.getTitle());

        openTaskWithId(newTask.getId());

        selectMenuSubsectionByText(BOARDS, "Доска TEST");

        addTaskToSprint(newTask.getId(), newTask.getTitle());

        toSprintBoard();

        moveTaskByIdTo(newTask.getId(), column);

        LOGGER.info("Перенос задачи в колонку - " + temp);

    }

    static Task newTask;

    @Тогда("^статус новой задачи - ([^\"]*)$")
    public void statusTask(String status) {
        $x("//a[contains(@title, 'TEST-"+taskId+"')]")
                .shouldBe(Condition.visible)
                .doubleClick().scrollIntoView(true);

        statusShouldBe(status);
    }

    @Дано("^открыта страницу по адресу: ([^\"]*)$")
    public void openPageFromUrl(String url) {
        open(url);
    }

}