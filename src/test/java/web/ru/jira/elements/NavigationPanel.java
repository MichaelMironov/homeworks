package web.ru.jira.elements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import web.ru.jira.pages.TaskPage;

import static com.codeborne.selenide.Selenide.*;

public class NavigationPanel {

    public static final SelenideElement PROJECTS = $x("//a[@id='browse_link']");

    public static final SelenideElement TASKS = $x("//*[@href='/issues/']");

    public static final SelenideElement ALL_PROJECTS = $x("//*[@id='project_view_all_link_lnk']");

    public static final SelenideElement BOARDS = $x("//a[@id='greenhopper_menu']");

    private static final SelenideElement createTaskButton = $x("//a[@id=\"create_link\"]");

    private static final Logger LOGGER = LogManager.getLogger(NavigationPanel.class);

    @Step("Посмотреть все проекты")
    public static NavigationPanel viewAllProjects() {
        PROJECTS.click();
        $x("//*[@id='browse_link-content']").shouldBe(Condition.visible);
        ALL_PROJECTS.click();
        return page(NavigationPanel.class);
    }

    @Step("Выбрать раздел меню: {menu}")
    public static SelenideElement selectMenuSection(SelenideElement menu){
        menu.click();
        menu.shouldHave(Condition.attribute("aria-expanded", "true"));
        return menu;
    }

    @Step("Выбрать раздел меню: {menu} и подраздел {text}")
    public static void selectMenuSubsectionByText(SelenideElement menu, String text){
        menu.click();
        menu.shouldHave(Condition.attribute("aria-expanded", "true"));
        $(By.linkText(text)).shouldBe(Condition.visible).click();
        LOGGER.info("Переход в подраздел - " + text);
    }

    @Step("Нажать кнопку [создать задачу]")
    public static void clickToCreateTask(){
        createTaskButton.click();
        TaskPage.sectionTaskCreation.shouldBe(Condition.appear);
    }

}
