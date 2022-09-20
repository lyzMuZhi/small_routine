package com.shestnut.user.internal.common.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @author luoyongzhi
 */
public class PropertiesUtil {
    private static final Properties properties = new Properties();

    static {
        try {
            properties.load(new InputStreamReader(
                    PropertiesUtil.class.getClassLoader().getResourceAsStream("config.properties"), "UTF-8"));
        } catch (IOException e) {
            throw new RuntimeException("加载config.properties失败", e);
        }
    }

    public static String get(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
