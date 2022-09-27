package web.ru.jira.tests;

import org.junit.jupiter.api.*;
import web.ru.jira.hooks.WebHooks;
import web.ru.jira.models.Task;

import static web.ru.jira.elements.NavigationPanel.*;
import static web.ru.jira.pages.TaskPage.*;
import static web.ru.jira.steps.AuthorizationPageSteps.*;
import static web.ru.jira.steps.BoardsPageSteps.*;
import static web.ru.jira.steps.TasksPageSteps.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BaseTest extends WebHooks {

    @BeforeAll
    public static void authorizationAsUser(){
        openAuthorizationPage();
        logInAs(USER);
    }

    @AfterAll
    public static void postCondition(){
        deleteSuccessfulTask(false);
    }

    @Test
    void TestCountTasks(){
        selectMenuSubsectionByText(TASKS,"Поиск задач");
        totalCountTasks();
    }

    @Test
    void TestStatusTask(){
        selectMenuSubsectionByText(TASKS,"Поиск задач");
        filterTasksByText("TestSelenium_bug");
        openTaskWithId(21967);
        statusShouldBe("В работе");
        checkFixIn("2.0");
    }

    @Test
    void TestCreationOfNewTask(){
        selectMenuSubsectionByText(TASKS,"Поиск задач");
        clickToCreateTask();
        Task newTask = createNewTask("test selenide", "Ошибка", "12345678asdasdasdasdasdasdasd");
        selectMenuSubsectionByText(TASKS, "Поиск задач");
        filterTasksByText(newTask.getTitle());
        idCreatedTaskShouldBe(newTask.getId());
    }

    @Test
    void TestClosingTask(){
        selectMenuSubsectionByText(TASKS,"Поиск задач");
        clickToCreateTask();
        Task newTask = createNewTask("test selenide", "Ошибка", "12345678asdasdasdasdasdasdasd");
        selectMenuSubsectionByText(BOARDS, "Доска TEST");
        addTaskToSprint(newTask.getId(), "test selenide");
        toSprintBoard();
        moveTaskByIdTo(newTask.getId(), IN_WORK);
        moveTaskByIdTo(newTask.getId(), DONE);
    }

}
