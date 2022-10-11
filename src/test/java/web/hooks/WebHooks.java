package web.hooks;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import java.io.FileOutputStream;
import java.util.Objects;
import java.util.Properties;

import static utils.configurations.Configuration.setEnvironmentProperties;


public class WebHooks {
    @BeforeAll
    public static void setConfiguration() {

        setEnvironmentProperties();

        Configuration.timeout = 7000;
        Configuration.startMaximized = true;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true)
        );

//        String webDriverLocation = null;
//
//        if (System.getProperty("os.name").toUpperCase().contains("MAC"))
//            webDriverLocation = getConfigurationValue("webdriverLocalMac");
//        if (System.getProperty("os.name").toUpperCase().contains("WINDOWS"))
//            webDriverLocation = getConfigurationValue("webdriverLocalWin");
//
//        if (webDriverLocation != null) {
//            System.setProperty("webdriver.chrome.driver", webDriverLocation);
//        }

    }

    @AfterAll
    static void closeDriver() {
        if (Objects.nonNull(WebDriverRunner.getWebDriver()))
            WebDriverRunner.getWebDriver().quit();

    }
}
