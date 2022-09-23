package web.hooks;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static utils.Configuration.getConfigurationValue;

public class WebHooks {
    @BeforeAll
    public static void setConfiguration() {

        Configuration.timeout = 7000;
        Configuration.startMaximized = true;

        String webDriverLocation = null;

        if (System.getProperty("os.name").toUpperCase().contains("MAC"))
            webDriverLocation = getConfigurationValue("webdriverLocalMac");
        if (System.getProperty("os.name").toUpperCase().contains("WINDOWS"))
            webDriverLocation = getConfigurationValue("webdriverLocalWin");

        if (webDriverLocation != null) {
            System.setProperty("webdriver.chrome.driver", webDriverLocation);
        }

    }

    @AfterAll
    static void closeDriver(){
        WebDriverRunner.closeWebDriver();
    }
}
