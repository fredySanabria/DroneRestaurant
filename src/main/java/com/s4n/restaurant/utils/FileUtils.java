package com.s4n.restaurant.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class FileUtils {
    public static String getProperty(String property) throws IOException {
        Properties properties = new Properties();
        try (InputStream input = FileUtils.class.getClassLoader().getResourceAsStream("app.properties")) {
            properties.load(input);
            return properties.getProperty(property);
        }
    }

    public static boolean validateFileName(String regex, String fileName){
        int matches = 0;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(fileName);
        while (matcher.find()){
            matches++;
        }
        return matches > 0;
    }
}
