package web.ru.jira.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class BoardsPage {

    public static SelenideElement backLog = $x("//div[@class='ghx-name']");

    public static SelenideElement searcherBacklogTasks = $x("//input[@aria-label=\"Поиск задач\"]");

    public static SelenideElement confirmButton = $x("//button[contains(@class,'button-panel-button')]");

    public static SelenideElement tasksList = $x("//a[starts-with(@class, 'aui-nav-item ')]");

    public static SelenideElement activeTasks = $x("//span[@title='Активные спринты']");


}
