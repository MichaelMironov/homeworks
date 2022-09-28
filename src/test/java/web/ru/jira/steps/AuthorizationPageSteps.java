package web.ru.jira.steps;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static utils.Configuration.getConfigurationValue;
import static web.ru.jira.pages.AuthorizationPage.*;

public class AuthorizationPageSteps {

    private static final Logger LOGGER = LogManager.getLogger(BoardsPageSteps.class);

    public static final String USER = "user";

    @Step("Открыть страницу авторизации")
    public static void openAuthorizationPage(){
        open(getConfigurationValue("authorizeUrl"));
        LOGGER.info("Переход на страницу авторизации...");
    }

    @Step("Авторизоваться в системе как {login}")
    public static void logInAs(String login){

        /*  При проверке инпутов, тесты на видимость и фокус проходят вне зависимости от того аткивен инпут или нет
        На активное поле ввода вешается кастомный аттрибут: data-focus-visible-added    */

        loginField.click();
        loginField.shouldHave(Condition.attribute("data-focus-visible-added")).sendKeys(getConfigurationValue(login));

        passwordField.click();
        passwordField.shouldHave(Condition.attribute("data-focus-visible-added")).sendKeys(getConfigurationValue("password"));

        buttonLogIn.shouldBe(Condition.enabled).click();

        assertFalse(usernameError.isDisplayed(), "Введены некорретные учетные данные!");

        LOGGER.info("Пользователь авторизован как - " + login);

    }

    @Step("Выйти из системы")
    public static void logOut(){
        $x("//span[@class='aui-avatar-inner']").shouldBe(Condition.visible).click();
        $x("//a[@id='log_out']").shouldBe(Condition.visible).click();
        LOGGER.info("Выход из системы");
    }

}
