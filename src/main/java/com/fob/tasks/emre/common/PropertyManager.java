package com.fob.tasks.emre.common;

import java.sql.SQLException;

/**
 * Supposed to manage db connections to get some basic properties 
 * but db access is not implemented (seems not necessary for the task)
 * @author Emre
 */
public class PropertyManager {

    private static String AUTH_KEY;

    public static String getAuthKey() {
        return AUTH_KEY;
    }

    /**
     * Get project properties from db
     * @throws SQLException
     */
    public static void loadProperties() throws SQLException{
        /**
         * ..reading from db...
         */
        AUTH_KEY = "0f65448e9f4f195c073d36faf576c69e??==";
    }
}
