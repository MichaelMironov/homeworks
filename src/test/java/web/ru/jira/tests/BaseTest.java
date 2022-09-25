package web.ru.jira.tests;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import web.hooks.WebHooks;
import web.ru.jira.models.Task;

import static web.ru.jira.elements.NavigationPanel.*;
import static web.ru.jira.pages.TaskPage.*;
import static web.ru.jira.steps.AuthorizationPageSteps.*;
import static web.ru.jira.steps.BoardsPageSteps.*;
import static web.ru.jira.steps.TasksPageSteps.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BaseTest extends WebHooks {

    @BeforeAll
    public static void authorization(){
        // 1. Авторизация
        openAuthorizationPage();
        logInAs(USER);
    }

    @AfterAll
    public static void postCondition(){
        deleteSuccessfulTask(false);
    }

    @Test
    public void TestFullScenario(){

        //Указать раздел меню на главной панели и его подраздел
        // 2. Переход в проект Test (TEST)
        selectMenuSubsectionByText(PROJECTS,"Test (TEST)");

        // 3. Переход в поиск задач и подсчет кол-ва задач
        selectMenuSubsectionByText(TASKS,"Поиск задач");
        totalCountTasks();

        // 4. Переход в задачу TestSelenium_bug и проверка привязки в затронутой версии и статуса
        filterTasksByText("TestSelenium_bug");
        openTaskWithId(21967);
        checkFixIn("2.0");
        statusShouldBe("Сделать");

        // 5. Создание нового бага с описанием и перевод задачи по статусам до закрытого.
        clickToCreateTask();
        Task newTask = createNewTask("test selenide", "Ошибка", "12345678asdasdasdasdasdasdasd");

        selectMenuSubsectionByText(TASKS, "Поиск задач");

        filterTasksByText(newTask.getTitle());

        openTaskWithId(newTask.getId());

        selectMenuSubsectionByText(BOARDS, "Доска TEST");

        addTaskToSprint(newTask.getId(), newTask.getTitle());

        toSprintBoard();

        moveTaskByIdTo(newTask.getId(), IN_WORK);
        moveTaskByIdTo(newTask.getId(), DONE);

    }

    @Disabled
    @Test
    public void TestFindingTask(){

        //Указать раздел меню на главной панели и его подраздел
        selectMenuSubsectionByText(TASKS,"Поиск задач");
        totalCountTasks();

        filterTasksByText("TestSelenium");

        openTaskWithId(21966);
        checkFixIn("2.0");

        openTaskWithId(21967);
        checkFixIn("2.0");

    }

    @Disabled
    @Test
    void TestCreationOfNewTask(){

        selectMenuSubsectionByText(TASKS, "Поиск задач");

        clickToCreateTask();

        Task newTask = createNewTask("test selenide", "Ошибка", "12345678asdasdasdasdasdasdasd");

        messageSuccessCreation.shouldBe(Condition.visible);

        selectMenuSubsectionByText(TASKS, "Поиск задач");

        filterTasksByText(newTask.getTitle());

        idCreatedTaskShouldBe(newTask.getId());

    }

    @Disabled
    @Test
    void TestClosingTask(){
        
        selectMenuSubsectionByText(TASKS, "Поиск задач");

        clickToCreateTask();

        Task newTask = createNewTask("test selenide", "Ошибка", "12345678asdasdasdasdasdasdasd");

        selectMenuSubsectionByText(BOARDS, "Доска TEST");

        addTaskToSprint(newTask.getId(), "test selenide");

        toSprintBoard();

        moveTaskByIdTo(newTask.getId(), IN_WORK);
        moveTaskByIdTo(newTask.getId(), DONE);

    }

}
