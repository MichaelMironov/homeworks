package web.ru.jira.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class TasksPage {

    public static SelenideElement filter = $x("//div[@class='order-by-fields']");

    public  static SelenideElement filterPopup = $x("//a[contains(@class, 'active')]");

    public static SelenideElement filterText = $x("//input[@aria-label=\"Поиск\"]");

    public static SelenideElement searcher = $x("//input[@id=\"searcher-query\"]");

    public static SelenideElement tasksCount = $x("//div[@class='showing']//child::span");

    public static SelenideElement status = $x("//*[@id=\"status-val\"]/span");

}
