package com.tank;

import java.io.IOException;
import java.util.Properties;

public class PropertyMgr {
    static Properties properties = new Properties();

    static {
        try {
            properties.load(PropertyMgr.class.getClassLoader().getResourceAsStream("Config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getValue(String key){
        return properties.getProperty(key);
    }

    public static void main(String[] args) {
        System.out.println(getValue("initTankCount"));
    }
}
