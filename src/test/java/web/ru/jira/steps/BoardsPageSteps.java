package web.ru.jira.steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.codeborne.selenide.Selenide.*;
import static web.ru.jira.pages.BoardsPage.*;

public class BoardsPageSteps {

    public static String taskName;

    public static final String IN_WORK = "//li[@data-column-id=\"5\"]";
    public static final String DONE = "//li[@data-column-id=\"6\"]";

    @Step("Добавить задачу с id: {id} и title: {name}")
    public static void addTaskToSprint(int id, String name) {

        taskName = id + " " + name;
        tasksList.click();
        searcherBacklogTasks.setValue(taskName);
        $x("//div[contains(@data-issue-key, " + id + ")]")
                .shouldBe(Condition.visible).contextClick().$(By.linkText("Доска Спринт 1"));

        $(By.linkText("Доска Спринт 1")).shouldBe(Condition.visible).click();

        confirmButton.click();

    }

    @Step("Перейти к доске текущего спринта")
    public static void toSprintBoard() {
        activeTasks.shouldBe(Condition.visible).click();
    }

    @Step("Переместить задачу с id: {id} в {in}")
    public static void moveTaskByIdTo(int id, String in) {

        WebDriver driverRunner = WebDriverRunner.getWebDriver();

        WebDriverWait wait = new WebDriverWait(driverRunner, 10);

        WebElement from = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@title='TEST-" + id + "']")));

        WebElement to = driverRunner.findElement(By.xpath(in));

        Actions actions = new Actions(driverRunner);
        actions.dragAndDrop(from, to).perform();

        if (in.equals(IN_WORK)){
            wait.until(ExpectedConditions.presenceOfElementLocated(By.name("Transition"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='TEST-" + id + "']")));
        }

    }
}
