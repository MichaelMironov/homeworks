package web.ru.jira.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import web.ru.jira.models.Task;

import static com.codeborne.selenide.Selenide.*;

public class TaskPage {

    private static final Logger LOGGER = LogManager.getLogger(TasksPage.class);

    public static SelenideElement sectionTaskCreation = $x("//section[@id=\"create-issue-dialog\"]");

    public static SelenideElement issueTypeField = $x("//input[@id='issuetype-field']");

    public static SelenideElement descriptionField = $x("//body[@id='tinymce']//p");

    public static SelenideElement taskTitle = $x("//input[@name='summary']");

    public static SelenideElement messageSuccessCreation = $x("//a[@class=\"issue-created-key issue-link\"]");

    public static SelenideElement submitButton = $x("//input[@id='create-issue-submit']");

    public static int taskId;

    public static void selectType(String type) {

        issueTypeField.click();
        issueTypeField.sendKeys(Keys.BACK_SPACE);
        issueTypeField.setValue(type);
        issueTypeField.pressEnter().shouldBe(Condition.focused);

    }

    public static void setTitle(String title) {
        taskTitle.setValue(title);
    }

    public static void setDescription(String description) {
        switchTo().frame(0);
        descriptionField.setValue(description);
        switchTo().defaultContent();
    }

    public static void submitTask() {

        submitButton.click();

        String[] nameOfCreatedTask = messageSuccessCreation.shouldBe(Condition.visible).innerText().split("-");

        taskId = Integer.parseInt(nameOfCreatedTask[1].trim());

        LOGGER.info("Id созданной задачи: " + taskId);

    }

    public static Task createNewTask(String title, String type, String description) {
        LOGGER.info("Создание задачи. Название: " + title + ". Тип: " + type + ". Описание: " + description);
        setTitle(title);
        selectType(type);
        setDescription(description);
        submitTask();
        messageSuccessCreation.shouldBe(Condition.visible);
        return new Task(title, type, description, taskId);
    }

}
