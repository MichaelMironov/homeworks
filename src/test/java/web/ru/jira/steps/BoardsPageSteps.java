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

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.*;
import static web.ru.jira.pages.BoardsPage.*;

public class BoardsPageSteps {

    public static String taskName;


    public static final String IN_WORK = "//li[@data-column-id='5']";
    public static final String DONE = "//li[@data-column-id='6']";

    @Step("Добавить задачу с id: {id} и title: {name}")
    public static void addTaskToSprint(int id, String name) {

        taskName = id + " " + name;
        tasksList.click();
        searcherBacklogTasks.setValue(taskName).should(Condition.appear);
        $x("//div[@class=\"ghx-description\"]").should(Condition.visible);

        $x("//div[contains(@data-issue-key, " + id + ")]")
                .shouldBe(Condition.exist).contextClick().$(By.linkText("Доска Спринт 1"));

        $(By.linkText("Доска Спринт 1")).shouldBe(Condition.visible).click();

        confirmButton.click();

    }

    @Step("Перейти к доске текущего спринта")
    public static void toSprintBoard() {
        activeTasks.shouldBe(Condition.visible).doubleClick();
    }

    @Step("Переместить задачу с id: {id} в {in}")
    public static void moveTaskByIdTo(int id, String in) {

        WebDriver driverRunner = WebDriverRunner.getWebDriver();
        driverRunner.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);

        WebDriverWait wait = new WebDriverWait(driverRunner, 10);

        WebElement from = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='TEST-"+ id +"']")));

        WebElement to = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(in)));

        Actions actions = new Actions(driverRunner);

        if(in.equals(IN_WORK)){
            actions.dragAndDrop(from, to).perform();
            wait.until(ExpectedConditions.elementToBeClickable(By.name("Transition"))).click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@title='TEST-"+ id +"']")));
            $(By.xpath("//a[@title='TEST-"+ id +"']")).shouldBe(Condition.visible).scrollTo();
        }else{
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='TEST-"+ id +"']")));
            actions.clickAndHold(from).moveToElement(to).release().pause(100).perform();
        }
    }
}
