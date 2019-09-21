package com.iflytek.mytank.loader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 属性加载类
 *
 * @author DuHuang 2019/9/21 0:28
 */
public class PropertiesLoader {
    //单例模式 实例
    private static PropertiesLoader propertiesLoader = new PropertiesLoader();

    private PropertiesLoader() {
        loadAll();
    }

    /**
     * 属性文件的key
     */
    interface Key {
        String IMAGE_REGEX="image.regex";
        String IMAGE_PATH = "image.path";
        String IMAGE_FORMAT = "image.format";
    }

    /**
     * 存储所有属性的Map
     */
    private Map<String, String> propertiesMap = new HashMap<>();

    /**
     * 加载全部属性文件
     */
    private void loadAll() {
        Properties props = new Properties();
        try {
            props.load(getClass().getResourceAsStream("/mytank.properties"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("找不到配置mytank.properties文件", e);
        } catch (IOException e) {
            throw new RuntimeException("读取配置mytank.properties文件出错", e);
        }
        propertiesMap.put(Key.IMAGE_FORMAT, props.getProperty(Key.IMAGE_FORMAT));
        propertiesMap.put(Key.IMAGE_PATH, props.getProperty(Key.IMAGE_PATH));
        propertiesMap.put(Key.IMAGE_REGEX, props.getProperty(Key.IMAGE_REGEX));
    }

    /**
     * 根据key获得属性值
     */
    public static String getProperties(String key) {
        return propertiesLoader.propertiesMap.get(key);
    }
}
