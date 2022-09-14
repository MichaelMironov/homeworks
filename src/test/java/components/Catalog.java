package components;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Catalog {

    private final WebDriver driver;

    public Catalog(WebDriver driver) {
        this.driver = driver;
        driver.get("https://www.eldorado.ru/d/aksessuary/");
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id='fl-296620']")
    private WebElement iframe;

    @FindBy(xpath = "//a[@href=\"/d/batareyki-i-akkumulyatory/\"]")
    private WebElement batterySection;

    @FindBy(xpath = "//a[@href=\"/c/batareyki/\"]")
    private WebElement batteryType;

    @FindBy(xpath = "//div[@data-dy=\"buttonBlock\"]")
    private WebElement buyButton;

    public Catalog selectBatterySection() {
        batterySection.click();
        if (iframe.isDisplayed())  // iframe со скидкой не всегда может появиться
            executeIframe();
        scrollToBatteryCategory();
        batteryType.click();
        return this;
    }

    public Catalog buyBattery() {
        buyButton.click();
        buyButton.click();
        return this;
    }

    public void purchaseCount(int count) {
        int size = driver.findElements(By.xpath("//div[@id='cart-items']")).size();
        Assertions.assertEquals(count, size);
    }

    private void executeIframe() {
        driver.switchTo().frame(driver.findElement(By.xpath("//*[@id='fl-296620']")));
        driver.findElement(By.xpath("//button[@class='Notification-button Notification-buttonAllow js-turn-on']")).click();
        driver.switchTo().defaultContent();
    }

    private void scrollToBatteryCategory() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,450)", "");
    }
}
