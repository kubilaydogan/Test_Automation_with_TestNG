package Framework.Core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigurationReader {
    private static final Logger log = LoggerFactory.getLogger(ConfigurationReader.class);
    private static Properties properties;

    static {
        try {
            String path = "configuration.properties";
            FileInputStream input = new FileInputStream(path);
            properties = new Properties();
            properties.load(input);
            input.close();
        } catch (Exception e) {
            log.error("Error reading configuration file with exception :" + e);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }

    // or

    public String readConfigurationFile(String configKey) {
        properties = new Properties();
        try {
            properties.load(new FileInputStream("configuration.properties"));
        } catch (Exception e) {
            log.error("Error reading configuration file with exception :" + e);
        }
        return properties.getProperty(configKey).trim();
    }
}
