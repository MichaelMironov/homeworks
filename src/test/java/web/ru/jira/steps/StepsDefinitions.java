package web.ru.jira.steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.ru.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import web.ru.jira.elements.NavigationPanel;
import web.ru.jira.models.Task;
import web.ru.jira.pages.BoardsPage;

import static com.codeborne.selenide.Selenide.*;
import static web.ru.jira.pages.TaskPage.createNewTask;
import static web.ru.jira.pages.AuthorizationPage.logInAs;
import static web.ru.jira.pages.AuthorizationPage.openAuthorizationPage;
import static web.ru.jira.pages.TasksPage.*;

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
                elem = NavigationPanel.TASKS;
        }

        NavigationPanel.selectMenuSubsectionByText(elem, submenu);
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
        NavigationPanel.clickToCreateTask();
        newTask = createNewTask(title, type, description);
    }

    @И("^перенести новую задачу на скрам-доске в колонку ([^\"]*)$")
    public static void movingTo(String column) {

        String temp = column;

        switch (column) {
            case "Выполнено":
                column = BoardsPage.DONE;
                break;
            case "В работе":
                column = BoardsPage.IN_WORK;
                break;
        }

        NavigationPanel.selectMenuSubsectionByText(NavigationPanel.TASKS, "Поиск задач");

        filterTasksByText(newTask.getTitle());

        openTaskWithId(newTask.getId());

        NavigationPanel.selectMenuSubsectionByText(NavigationPanel.BOARDS, "Доска TEST");

        BoardsPage.addTaskToSprint(newTask.getId(), newTask.getTitle());

        BoardsPage.toSprintBoard();

        BoardsPage.moveTaskByIdTo(newTask.getId(), column);

        LOGGER.info("Перенос задачи в колонку - " + temp);

    }

    static Task newTask;

    @Тогда("^статус новой задачи - ([^\"]*)$")
    public void statusTask(String status) {
        $x("//a[contains(@title, 'TEST-"+ BoardsPage.taskId+"')]")
                .shouldBe(Condition.visible)
                .doubleClick().scrollIntoView(true);

        statusShouldBe(status);
    }

    @Дано("^открыта страницу по адресу: ([^\"]*)$")
    public void openPageFromUrl(String url) {
        open(url);
    }

}