package utils.configurations;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "classpath:config/chrome.properties",
        "system:properties",
        "system:env"
})
public interface WebConfiguration extends Config {

    @Key("webdriver.browser.size")
    @DefaultValue("1920x1080")
    String webDriverBrowserSize();

    @Key("webdriver.timeoutSeconds")
    @DefaultValue("5")
    int webDriverTimeoutSeconds();

}