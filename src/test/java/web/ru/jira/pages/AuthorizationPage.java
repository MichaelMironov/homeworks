package web.ru.jira.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static utils.configurations.Configuration.getConfigurationValue;

public class AuthorizationPage {

    private static final SelenideElement loginField = $x("//input[@id='login-form-username']");

    private static final SelenideElement passwordField = $x("//input[@id='login-form-password']");

    private static final SelenideElement buttonLogIn = $x("//input[@type='submit' and @name='login']");

    private static final SelenideElement usernameError = $x("//div[@id='usernameerror']");

    private static final Logger LOGGER = LogManager.getLogger(BoardsPage.class);

    public static void openAuthorizationPage() {
        step("Открыть страницу авторизации", () -> {
            open(getConfigurationValue("authorizeUrl"));
        });
    }

    public static void logInAs(String login) {

        step("Авторизоваться в системе как: " + login, () -> {
            SelenideLogger.removeListener("AllureSelenide");
            loginField.click();
            step("Ввести логин: ***** в элемент ["+ loginField.getSearchCriteria()+"]", () -> loginField.shouldHave(Condition.attribute("data-focus-visible-added")).sendKeys(getConfigurationValue(login)));

            passwordField.click();
            step("Ввести пароль: ***** в элемент ["+ passwordField.getSearchCriteria() +"]", () -> passwordField.shouldHave(Condition.attribute("data-focus-visible-added")).sendKeys(getConfigurationValue("password")));

            buttonLogIn.shouldBe(Condition.enabled).click();

            assertFalse(usernameError.isDisplayed(), "Введены некорретные учетные данные!");
        });
        SelenideLogger.addListener("AllureSelenide",new AllureSelenide());
        LOGGER.info("Пользователь авторизован как - " + login);

    }

    public static void logOut() {
        step("Выйти из системы", () -> {
            $x("//span[@class='aui-avatar-inner']").shouldBe(Condition.visible).click();
            $x("//a[@id='log_out']").shouldBe(Condition.visible).click();
        });

    }

}
