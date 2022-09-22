package web.ru.jira.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import web.ru.jira.models.Task;

import static com.codeborne.selenide.Selenide.*;

public class TaskPage {

    public static SelenideElement sectionTaskCreation = $x("//section[@id=\"create-issue-dialog\"]");

    public static SelenideElement issueTypeField = $x("//input[@id='issuetype-field']");

    public static SelenideElement iframeDescription = $x("//iframe[@class='tox-edit-area__iframe']");

    public static SelenideElement descriptionField = $x("//*[@id='tinymce']/p");

    public static SelenideElement taskTitle = $x("//input[@name='summary']");

    public static SelenideElement messageSuccessCreation = $x("//a[@class=\"issue-created-key issue-link\"]");

    public static SelenideElement submitButton = $x("//input[@id='create-issue-submit']");

    public static int taskId;

    public static void selectType(String type) {

        issueTypeField.click();
        issueTypeField.sendKeys(Keys.BACK_SPACE);
        issueTypeField.setValue(type);
        issueTypeField.pressEnter().shouldBe(Condition.visible);

    }

    public static void setTitle(String title) {
        taskTitle.setValue(title);
    }

    public static void setDescription(String description) {
        switchTo().frame(0);
        descriptionField.shouldBe(Condition.visible).setValue(description);
        switchTo().defaultContent();
    }

    public static void submitTask() {

        submitButton.click();

        messageSuccessCreation.shouldBe(Condition.appear);

        String[] nameOfCreatedTask = messageSuccessCreation.innerText().split("-");

        taskId = Integer.parseInt(nameOfCreatedTask[1].trim());

        System.out.println("Id созданной задачи: " + nameOfCreatedTask[1]); //TODO
    }

    public static Task createNewTask(String title, String type, String description) {
        setTitle(title);
        selectType(type);
        setDescription(description);
        submitTask();
        return new Task(title, type, description, taskId);
    }

}
