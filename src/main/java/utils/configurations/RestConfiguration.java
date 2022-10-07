package utils.configurations;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "classpath:config/configuration.properties",
        "system:properties",
        "system:env"
})
public interface RestConfiguration extends Config {

    @Key("baseUrl")
    @DefaultValue("")
    String getBaseUrl();

}

