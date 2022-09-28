package web.ru.jira.steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static web.ru.jira.pages.BoardsPage.*;

public class BoardsPageSteps {

    private static final Logger LOGGER = LogManager.getLogger(BoardsPageSteps.class);

    public static String taskName;
    public static int taskId;


    public static final String IN_WORK = "//li[@data-column-id='5']";
    public static final String DONE = "//li[@data-column-id='6']";

    @Step("Добавить задачу с id: {id} и title: {name}")
    public static void addTaskToSprint(int id, String name) {

        LOGGER.info("Добавить задачу в активный спринт");

        taskName = id + " " + name;
        taskId = id;
        tasksList.click();
        searcherBacklogTasks.setValue(taskName).should(Condition.appear);
        $x("//div[@class=\"ghx-description\"]").should(Condition.visible);

        $x("//div[contains(@data-issue-key, " + id + ")]")
                .shouldBe(Condition.exist).contextClick().$(By.partialLinkText("Доска Спринт 1"));

        $(By.linkText("Доска Спринт 1")).shouldBe(Condition.visible).click();

        confirmButton.click();

        LOGGER.info("Задача с id: " + id + " добавлена в активный спринт");
    }

    @Step("Перейти к доске текущего спринта")
    public static void toSprintBoard() {
        activeTasks.shouldBe(Condition.visible).doubleClick();
        LOGGER.info("Переход к доске активного спринта");
    }

    @Step("Переместить задачу с id: {id} в {in}")
    public static void moveTaskByIdTo(int id, String in) {

        SelenideElement task = $x("//a[@title='TEST-"+ id +"']");

        SelenideElement column = $x(in).shouldBe(Condition.visible);

        task.shouldBe(Condition.visible).dragAndDropTo(column);

        if(in.equals(IN_WORK)){
            $(By.name("Transition")).shouldBe(Condition.visible).click();
        }
    }

    public static void deleteSuccessfulTask(boolean answer){
        if(answer){
            $x("//a[contains(@title, 'TEST-"+taskId+"')]/parent::div/following-sibling::div")
                    .shouldBe(Condition.visible)
                    .contextClick();
            $x("//div[@class='aui-list']").shouldBe(Condition.visible, Condition.appear);
            $(By.linkText("Удалить")).click();
            $x("//input[@name='Delete']").shouldBe(Condition.visible).click();
        }
    }
}
