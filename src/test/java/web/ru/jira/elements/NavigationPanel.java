package web.ru.jira.elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class NavigationPanel {

    public static final SelenideElement PROJECTS = $x("//a[@id='browse_link']");

    public static final SelenideElement tasks = $x("//*[@href='/issues/']");

    public static final SelenideElement ALL_PROJECTS = $x("//*[@id='project_view_all_link_lnk']");

    public static NavigationPanel viewAllProjects() {
        PROJECTS.click();
        $x("//*[@id='browse_link-content']").shouldBe(Condition.visible);
        ALL_PROJECTS.click();
        return page(NavigationPanel.class);
    }

//    public static SelenideElement selectMenuSection(SelenideElement menu){
//        menu.click();
//        menu.shouldHave(Condition.attribute("aria-expanded", "true"));
//
//        return menu;
//    }

    public static void selectMenuSubsectionByText(SelenideElement menu, String text){
        menu.click();
        menu.shouldHave(Condition.attribute("aria-expanded", "true"));
        $(By.linkText(text)).click();
    }



}
