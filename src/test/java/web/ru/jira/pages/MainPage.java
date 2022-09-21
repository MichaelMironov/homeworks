package web.ru.jira.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class MainPage {

    public static SelenideElement welcomeInfoBlock = $x("//div[@class='intro']");
}
