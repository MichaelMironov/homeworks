package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PersonalBasket {

    private final WebDriver driver;

    public PersonalBasket(WebDriver driver) {
        this.driver = driver;
        driver.get("https://www.eldorado.ru/personal/basket.php");
    }

    public PersonalBasket deleteItems() {

        driver.findElement(By.xpath("//span[contains(text(), 'Очистить корзину')]")).click();
        driver.findElement(By.xpath("//span[contains(text(), 'Очистить корзину')]")).click();
        return this;

    }

    public void isItemsRemoved() {
        // isDisplayed() отрабатывает логически неверно, т.к. элемент закрыт изображением товара, пока не удален
        driver.findElement(By.xpath("//div[contains(text(), \"Товар удален\")]")).click();
    }
}
