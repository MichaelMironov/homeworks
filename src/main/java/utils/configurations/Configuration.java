package utils.configurations;


import java.io.FileOutputStream;
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

    public static void setEnvironmentProperties(){
        String path = System.getProperty("allure.results.directory");
        try {
            Properties props = new Properties();
            FileOutputStream fos = new FileOutputStream(path + "/environment.properties");
            props.setProperty(System.getProperty("os.name"), System.getProperty("os.version"));
            props.setProperty("Architecture",System.getProperty("os.arch"));
            props.setProperty("JDK", System.getProperty("java.version"));
            props.setProperty("Cucumber plugin", System.getProperty("cucumber.plugin"));
            props.store(fos, "environment");
            fos.close();

        } catch (Exception e) {
            System.err.println("Ошибка записи в файл");
            e.printStackTrace();
        }
    }

    public Configuration(){

    }
}
