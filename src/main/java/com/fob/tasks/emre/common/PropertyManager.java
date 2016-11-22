package com.fob.tasks.emre.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyManager {

    // TODO
    public static String REST_URI = "*";
    public static String ACCESS_KEY;

    public static void loadProperties() throws IOException{
        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream("config.properties");
            prop.load(input);

            REST_URI = prop.getProperty("REST_URI");
            ACCESS_KEY = prop.getProperty("ACCESS_KEY");

        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
