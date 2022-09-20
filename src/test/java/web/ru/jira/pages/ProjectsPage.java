package web.ru.jira.pages;

import static com.codeborne.selenide.Selenide.$x;

public class ProjectsPage {
    public static void selectProjectByTitle(String text){
        $x("//a[@href=\"/browse/TEST\" and contains(@original-title,\""+text+"\")]").click();
    }
}
