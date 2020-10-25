package main.java.model;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionSQLite {

    private static Connection CONNECTION;

    public static Connection dbConnector() {

        try {


            InputStream inputStream = null;
            Properties prop = null;
            try {
                prop = new Properties();
                String propFileName = "config.properties";

                inputStream = ConnectionSQLite.class.getClassLoader().getResourceAsStream(propFileName);

                if (inputStream != null) {
                    prop.load(inputStream);
                } else {
                    throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
                }

                Class.forName("org.sqlite.JDBC");
                CONNECTION = DriverManager.getConnection(prop.getProperty("DRIVER") + ":" + prop.getProperty("CONNECTION_STRING") + prop.getProperty("DBNAME"));
                CONNECTION.setAutoCommit(Boolean.FALSE);

                inputStream.close();
            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
            return CONNECTION;

        } catch (Exception e) {
            return null;
        }

    }
}

