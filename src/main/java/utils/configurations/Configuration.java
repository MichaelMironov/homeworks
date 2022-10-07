package utils.configurations;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *  Класс для про слушивания проперти файла. с возможностью подгрузки из вне
 *  */
public class Configuration {
    private static final String CONFIGURATION_FILE = "/test.properties";
    private static final Properties properties;

    static {
        properties = new Properties();
        try (InputStream inputStream = Configuration.class.getResourceAsStream(CONFIGURATION_FILE)) {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file" + CONFIGURATION_FILE, e);
        }
    }

    // Эта строка смотрит на входящие данные из вне. Допустим можно указать данные в Jenkins, если таких данных нет то брать из проперти файла
    public static String getConfigurationValue(String key) {

        return ((System.getProperty(key) == null) ? properties.getProperty(key) : System.getProperty(key));
    }

    public Configuration(){

    }
}
