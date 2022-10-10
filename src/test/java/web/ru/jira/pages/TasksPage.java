package web.ru.jira.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import java.util.Arrays;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static web.ru.jira.pages.BoardsPage.taskId;

public class TasksPage {

    private static final SelenideElement searcher = $x("//input[@id=\"searcher-query\"]");

    private static final SelenideElement tasksCount = $x("//span[starts-with(@class, 'results-count-total')]");

    private static final SelenideElement fixVersion = $x("//span[@id=\"fixfor-val\"]//a");

    private static final Logger LOGGER = LogManager.getLogger(TasksPage.class);

    public static void filterTasksByText(String text) {
        step("Отфильтровать задачи по тексту: " + text, ()->{
            searcher.shouldBe(Condition.appear).click();
            searcher.sendKeys(text);
            searcher.pressEnter();
        });
        LOGGER.info("Поиск задачи по названию: " + text);
    }

    public static void totalCountTasks() {
        step("Поиск количества задач", ()->LOGGER.info("Общее количество задач: "+ tasksCount.shouldBe(Condition.visible).innerText()));
    }

    public static void openTaskWithId(int id) {
        step("Открыть задачу с id: " + id, ()->$(By.partialLinkText("TEST-"+id+"")).shouldBe(Condition.visible).click());
        LOGGER.info("Открытие задачи с id: " + id);
    }

    public static void checkFixIn(String expectedVersion) {
        step("Проверка исправления в версии. Ожидаемое значение: " + expectedVersion, ()->{
            String actualVersion = Arrays.stream(fixVersion
                            .innerText().split(" "))
                    .reduce((first, second) -> second).get();

            assertEquals(expectedVersion, actualVersion);
            LOGGER.info("Ожидаемое исправление в версии: " + expectedVersion+". Актуальное: " + actualVersion );
        });
    }

    public static void statusShouldBe(String expectedStatus){
        step("Проверка статуса. Ожидаемое значение: " + expectedStatus, () ->{
            String actualStatus = $x("//span[@id='status-val']//span").shouldBe(Condition.visible).innerText();
            assertEquals(expectedStatus,actualStatus);
            LOGGER.info("Ожидаемый статус задачи: " + expectedStatus+". Актуальный: " + actualStatus);
        } );
    }

    public static void statusTaskByIdShouldBe(String expectedStatus, int taskId){
        step("Проверка статуса. Ожидаемое значение: " + expectedStatus, () ->{
            $x("//a[contains(@title, 'TEST-"+ taskId+"')]").shouldBe(Condition.visible).doubleClick().scrollIntoView(true);
            String actualStatus = $x("//span[@id='status-val']//span").shouldBe(Condition.visible).innerText();
            assertEquals(expectedStatus,actualStatus);
            LOGGER.info("Ожидаемый статус задачи: " + expectedStatus+". Актуальный: " + actualStatus);
        } );
    }

}
