package utilities;

import java.io.FileInputStream; // To read file
import java.io.IOException; // To catch possible errors during file interactions
import java.util.Properties; // Receives data from .properties files as key-value

public class ConfigReader {
    private static Properties properties;

    static {
        try {
            FileInputStream file = new FileInputStream("configuration.properties");
            properties = new Properties();
            properties.load(file);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
