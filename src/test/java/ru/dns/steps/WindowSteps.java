package ru.dns.steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Тогда;
import org.junit.Assert;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class WindowSteps {


    @Если("i open {string}")
    public void open(String url){
        Selenide.open(url);
    }

    @И("hover on {string}")
    public void hover(String element){
        $(Selectors.byText(element)).hover();
    }

    @Тогда("submenu appeared")
    public void isVisible(){
        $x("//div[@class='menu-desktop__submenu menu-desktop__submenu_top']").shouldBe(Condition.visible);
    }

    @И("click on {string}")
    public void click(String text){
        $(Selectors.byText(text)).click();
    }

    @Тогда("number of items in the cart - {int}")
    public void itemsCount(int count){
        String text = $(".cart-products-count").innerText();

        int items = Integer.parseInt(String.valueOf(text.charAt(0)));

        Assert.assertEquals(count, items);
    }

    @Тогда("given message {string}")
    public void getMessage(String message){
        $(Selectors.byText(message)).isDisplayed();
    }
}
