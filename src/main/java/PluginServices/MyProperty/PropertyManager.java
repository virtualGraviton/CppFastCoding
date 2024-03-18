package PluginServices.MyProperty;

import PluginServices.MyNotice;
import com.intellij.openapi.application.PathManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
public class PropertyManager {
    private static final Logger logger = LoggerFactory.getLogger(PropertyManager.class);
    Properties properties;

    public PropertyManager() {
        properties = new Properties();
        read();
    }

    public String get(String key) {
        return properties.getProperty(key);
    }

    public void set(String key, String val) {
        properties.setProperty(key, val);
    }

    public void read() {
        try {
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("config.properties");
            if (inputStream != null) {
                properties.load(inputStream);
                inputStream.close();
            }
        } catch (IOException exception) {
            logger.error("Config read failed", exception);
        }
    }

    public void save() {
        String jarPath = PathManager.getJarPathForClass(PropertyManager.class);
        System.out.println("当前插件的 JAR 包地址：" + jarPath);
        try {
            OutputStream outputStream = new FileOutputStream("config.properties");
            properties.store(outputStream, null);
            outputStream.close();
            read();
        } catch (IOException exception) {
            logger.error("Config write failed", exception);
        }
        MyNotice.ShowBalloon("Property:", "Config saved.");
    }
}
