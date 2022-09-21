package web.ru.jira.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class AuthorizationPage {

    public static SelenideElement loginField = $x("//input[@id='login-form-username']");

    public static SelenideElement passwordField = $x("//input[@id='login-form-password']");

    public static SelenideElement buttonLogIn = $x("//input[@type='submit' and @name='login']");

    public static SelenideElement usernameError = $x("//div[@id='usernameerror']");

}
