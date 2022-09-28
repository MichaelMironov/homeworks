package web.ru.jira.steps;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import java.util.Arrays;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static web.ru.jira.pages.TasksPage.*;

public class TasksPageSteps {

    private static final Logger LOGGER = LogManager.getLogger(TasksPageSteps.class);

    @Step("Отфильтровать задачи по тексту: {text}")
    public static void filterTasksByText(String text) {
        searcher.shouldBe(Condition.appear).click();
        searcher.sendKeys(text);
        searcher.pressEnter();
        LOGGER.info("Поиск задачи по названию: " + text);
    }

    @Step("Общее количество задач")
    public static void totalCountTasks() {
        LOGGER.info("Общее количество задач: "+$x("//span[starts-with(@class, 'results-count-total')]")
                .shouldBe(Condition.visible).innerText());
    }

    @Step("Открыть задачу с id: {id}")
    public static void openTaskWithId(int id) {
        $(By.partialLinkText("TEST-"+id+"")).shouldBe(Condition.visible).click();
        LOGGER.info("Открытие задачи с id: " + id);
    }

    @Step("Id созданной задачи должен быть: {id}")
    public static void idCreatedTaskShouldBe(int id) {
        $(By.partialLinkText("TEST-"+id+"")).shouldBe(Condition.visible);
        LOGGER.info("Ожидаемое id созданной задачи: " + id);
    }

    @Step("Проверка исправления в версии: {expectedVersion}")
    public static void checkFixIn(String expectedVersion) {

        String actualVersion = Arrays.stream($x("//span[@id=\"fixfor-val\"]//a")
                        .innerText().split(" "))
                        .reduce((first, second) -> second).get();

        assertEquals(expectedVersion, actualVersion);
        LOGGER.info("Ожидаемое исправление в версии: " + expectedVersion+". Актуальное: " + actualVersion );
    }

    public static void statusShouldBe(String expectedStatus){
        String actualStatus = $x("//span[@id='status-val']//span").shouldBe(Condition.visible).innerText();
        assertEquals(expectedStatus,actualStatus);
        LOGGER.info("Ожидаемый статус задачи: " + expectedStatus+". Актуальный: " + actualStatus);
    }

}
