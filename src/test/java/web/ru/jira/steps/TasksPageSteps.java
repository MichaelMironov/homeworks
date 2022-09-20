package web.ru.jira.steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.commands.PressEnter;

import static web.ru.jira.pages.TasksPage.*;

public class TasksPageSteps {

//    public static void filterByText(String text){
//        filter.click();
//        filterPopup.shouldBe(Condition.visible);
//        filterText.click();
//        filterText.sendKeys(text);
//    }

    public static void searchByText(String text){
        searcher.click();
        searcher.shouldHave(Condition.attribute("data-focus-visible-added")).sendKeys(text);
        searcher.pressEnter();
    }

    public static void totalCountTasks(){
       String[] temp = tasksCount.innerText().split(" ");

       //TODO by logger
        System.out.println("Общее количество задач: " + temp[temp.length-1]);
    }

}
