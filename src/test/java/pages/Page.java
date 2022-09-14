package pages;

import org.openqa.selenium.WebDriver;

public class Page {

    protected static WebDriver driver;

    public static void setDriver(WebDriver webDriver) {
        driver = webDriver;
    }

}
