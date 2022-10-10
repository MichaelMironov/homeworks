package web.ru.jira.steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.ru.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import web.ru.jira.elements.NavigationPanel;
import web.ru.jira.models.Task;

import static com.codeborne.selenide.Selenide.*;
import static web.ru.jira.elements.NavigationPanel.clickToCreateTask;
import static web.ru.jira.elements.NavigationPanel.selectMenuSubsectionByText;
import static web.ru.jira.pages.BoardsPage.*;
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

                System.out.println(DONE);
                break;
            case "В работе":
                column = IN_WORK;
                break;
        }

        selectMenuSubsectionByText(NavigationPanel.TASKS, "Поиск задач");

        filterTasksByText(newTask.getTitle());

        openTaskWithId(newTask.getId());

        selectMenuSubsectionByText(NavigationPanel.BOARDS, "Доска TEST");

        addTaskToSprint(newTask.getId(), newTask.getTitle());

        toSprintBoard();

        moveTaskByIdTo(newTask.getId(), column);

        LOGGER.info("Перенос задачи в колонку - " + temp);

    }

    private static Task newTask;

    @Тогда("^статус новой задачи - ([^\"]*)$")
    public void statusTask(String status) {
        statusTaskByIdShouldBe(status, newTask.getId());
    }

    @Дано("^открыта страницу по адресу: ([^\"]*)$")
    public void openPageFromUrl(String url) {
        open(url);
    }

}