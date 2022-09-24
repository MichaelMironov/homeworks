package web.ru.jira.steps;

import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.ru.����;
import io.cucumber.java.ru.�����;
import io.cucumber.java.ru.�;
import io.cucumber.java.ru.�����;
import web.ru.jira.models.Task;

import static web.ru.jira.elements.NavigationPanel.*;
import static web.ru.jira.pages.TaskPage.createNewTask;
import static web.ru.jira.steps.AuthorizationPageSteps.logInAs;
import static web.ru.jira.steps.AuthorizationPageSteps.openAuthorizationPage;
import static web.ru.jira.steps.BoardsPageSteps.*;
import static web.ru.jira.steps.BoardsPageSteps.DONE;
import static web.ru.jira.steps.TasksPageSteps.*;

public class StepsDefinitions {

    @����("������������ ����������� � ������� ��� ([^\"]*)")
    public static void authorizeAs(String user){
        openAuthorizationPage();
        logInAs(user);
    }

    @�����("� ������� ([^\"]*) ������� ��������� ([^\"]*)")
    public static void chooseSubMenu(String menu, String submenu){
        SelenideElement elem = null;

        switch (menu){
            case "������": elem = TASKS;
        }

        selectMenuSubsectionByText(elem, submenu);
    }

    @�("��������� ����� ���������� ���������� ����� � �������")
    public static void tasksCount(){
        totalCountTasks();
    }

    @�����("������� � ������ � ��������� - ([^\"]*) � id - (\\d+)")
    public static void goToTask(String title, int id){
        filterTasksByText(title);
        openTaskWithId(id);
    }

    @�("������ ������ - ([^\"]*), ��������� � ������� - ([^\"]*)")
    public static void checkTask(String status, String version){
        statusShouldBe(status);
        checkFixIn(version);
    }

    @�����("������� ����� ������ � ����� ([^\"]*), ������ ([^\"]*) � ��������� ([^\"]*)")
    public static void createTask(String type, String title, String description){
        clickToCreateTask();
        newTask = createNewTask(title, type, description);
    }

    @�("��������� ����� ������ �� �����-����� � ������� ([^\"]*)")
    public static void movingTo(String column){

        switch (column){
            case "���������": column = DONE;
        }

        selectMenuSubsectionByText(TASKS, "����� �����");

        filterTasksByText(newTask.getTitle());

        openTaskWithId(newTask.getId());

        selectMenuSubsectionByText(BOARDS, "����� TEST");

        addTaskToSprint(newTask.getId(), newTask.getTitle());

        toSprintBoard();

        moveTaskByIdTo(newTask.getId(), IN_WORK);
        moveTaskByIdTo(newTask.getId(), column);
    }

    static Task newTask;
}
