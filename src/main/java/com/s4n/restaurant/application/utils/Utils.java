package com.s4n.restaurant.application.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class Utils {
    public static String getProperty(String property){
        Properties properties = new Properties();
        try (InputStream input = Utils.class.getClassLoader().getResourceAsStream("app.properties")) {
            properties.load(input);
            return properties.getProperty(property);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
