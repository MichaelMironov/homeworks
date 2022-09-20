package web.ru.jira.pages.steps.models;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class AuthorizationPage {

    public static SelenideElement loginField = $x("//input[@id='login-form-username']");

    public static SelenideElement passwordField = $x("//input[@id='login-form-password']");

}
