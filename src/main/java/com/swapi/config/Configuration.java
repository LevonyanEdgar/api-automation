package com.swapi.config;


import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.core.util.FileUtils;

import java.io.IOException;
import java.util.Properties;


@Log4j2
public class Configuration {

    public static final String API_DOMAIN_URL;

    private static final Properties configs;

    static {
        configs = readFromFile("/application.properties");
        API_DOMAIN_URL =getProperty("swapi.api.domain");


    }

    private Configuration() {

    }

    public static Properties readFromFile(String path) {
        Properties properties = new Properties();
        try {
            properties.load(FileUtils.class.getResourceAsStream(path));
        } catch (IOException e) {
            log.info(e.getMessage());
        }
        return properties;
    }

    public static String getProperty(String key) {
        if (System.getProperty(key) == null || System.getProperty(key).isEmpty()) {
            String property = configs.getProperty(key);
            log.info ("Getting property " + key + ": " + property);
            return property;
        } else {
            String property = System.getProperty(key);
            log.info("Getting property " + key + ": " + property);
            return property;
        }
    }

}
