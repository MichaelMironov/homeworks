package web.ru.jira.tests;

import org.junit.jupiter.api.*;
import web.hooks.WebHooks;
import web.ru.jira.models.Task;

import static web.ru.jira.elements.NavigationPanel.*;
import static web.ru.jira.pages.TaskPage.*;
import static web.ru.jira.steps.AuthorizationPageSteps.*;
import static web.ru.jira.steps.BoardsPageSteps.*;
import static web.ru.jira.steps.TasksPageSteps.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BaseTest extends WebHooks {

    @Test
    public void TestFullScenario(){

        openAuthorizationPage();
        logInAs(USER);

        //Указать раздел меню на главной панели и его подраздел
        selectMenuSubsectionByText(TASKS,"Поиск задач");
        totalCountTasks();

        filterTasksByText("TestSelenium");

        openTaskWithId(21966);
        checkFixIn("2.0");

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
    @Order(1)
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
    @Order(2)
    void TestCreationOfNewTask(){

        clickToCreateTask();

        Task newTask = createNewTask("test selenide", "Ошибка", "12345678asdasdasdasdasdasdasd");

        selectMenuSubsectionByText(TASKS, "Поиск задач");

        filterTasksByText(newTask.getTitle());

        openTaskWithId(newTask.getId());

    }

    @Disabled
    @Test
    @Order(3)
    void TestClosingTask(){

        selectMenuSubsectionByText(BOARDS, "Доска TEST");

        /*Указать существущую в бэклоге задачу(id, title)
        Или создать объект новой и передать значения, используя созданные степы */
//        addTaskToSprint(22063,"test selenide");

        moveTaskByIdTo(22064, IN_WORK);
        moveTaskByIdTo(22064, DONE);

    }

}
