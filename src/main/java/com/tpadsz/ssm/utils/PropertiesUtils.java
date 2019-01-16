package com.tpadsz.ssm.utils;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by hongjian.chen on 2019/1/16.
 */
public class PropertiesUtils {


    public static String getValue(String key) {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Properties prop = new Properties();
        try {
            InputStream in = resourceLoader.getResource("/common.properties").getInputStream();
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty(key);
    }

}
