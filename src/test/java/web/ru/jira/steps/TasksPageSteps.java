package web.ru.jira.steps;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import java.util.Arrays;

import static com.codeborne.selenide.Selenide.$x;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static web.ru.jira.pages.TasksPage.*;

public class TasksPageSteps {

    @Step("Отфильтровать задачи по тексту: {text}")
    public static void filterTasksByText(String text) {
        searcher.shouldBe(Condition.appear).click();
        searcher.sendKeys(text);
        searcher.pressEnter();
    }

    @Step("Общее количество задач")
    public static void totalCountTasks() {
        String[] temp = tasksCount.innerText().split(" ");

        //TODO by logger
        System.out.println("Общее количество задач: " + temp[temp.length - 1]);
    }

    @Step("Открыть задачу с id: {id}")
    public static void openTaskWithId(int id) {
        $x("//li[contains(@data-key, 'TEST-" + id + "')]").shouldBe(Condition.visible).click();
    }

    @Step("Проверка исправления в версии: {expectedVersion}")
    public static void checkFixIn(String expectedVersion) {

        String actualVersion = Arrays.stream($x("//span[@id=\"fixfor-val\"]//a")
                        .innerText().split(" "))
                        .reduce((first, second) -> second).get();

        assertEquals(expectedVersion, actualVersion);
    }


}
