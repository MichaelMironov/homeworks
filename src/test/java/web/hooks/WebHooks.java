package web.hooks;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import utils.configurations.WebConfiguration;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import static utils.configurations.Configuration.setEnvironmentProperties;


public class WebHooks {
    @BeforeAll
    public static void setConfiguration() {

        WebConfiguration cfg = ConfigFactory.create(WebConfiguration.class,
                System.getProperties(),
                System.getenv());

        setEnvironmentProperties();

        Configuration.browserSize = cfg.webDriverBrowserSize();
        Configuration.timeout = TimeUnit.SECONDS.toMillis(cfg.webDriverTimeoutSeconds());
        Configuration.headless = true;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false)
        );
    }

    public static void addCategoriesToAllure() {
        try {
            Files.copy(Paths.get("src/test/resources/categories.json"),Paths.get("target/allure-results/categories.json"));
        } catch (IOException e) {
            System.out.println("Ошибка в формировании отчета категорий Allure");
            e.printStackTrace();
        }
    }

    @AfterAll
    static void closeDriver() {
        WebDriverRunner.getWebDriver().quit();
    }
}
