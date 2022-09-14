package pages;

import components.Catalog;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends Page{

    public MainPage() {
        PageFactory.initElements(driver, this);
    }

    public static Catalog toProductCatalog() {
        return new Catalog(driver);
    }

}
