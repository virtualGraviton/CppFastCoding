package PluginServices.MyProperty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class PropertyManager {
    Properties properties;
    private static final Logger logger = LoggerFactory.getLogger(PropertyManager.class);

    public PropertyManager() {
        properties = new Properties();
        try {
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("config.properties");
            if (inputStream != null) {
                System.out.println("Get input stream success.");
                properties.load(inputStream);
                inputStream.close();
            }
        } catch (IOException exception) {
            logger.error("Config read failed", exception);
        }
        System.out.println(properties.getProperty("CompileSTD"));
    }

    public String get(String key) {
        return properties.getProperty(key);
    }

    public void set(String key, String val) {
        properties.setProperty(key, val);
    }

    public void save() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("config.properties");
            properties.store(fileOutputStream, null);
            fileOutputStream.close();
        } catch (IOException exception) {
            logger.error("Config write failed", exception);
        }
    }
}
