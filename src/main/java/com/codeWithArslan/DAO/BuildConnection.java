package com.codeWithArslan.DAO;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class BuildConnection {
    Connection connection = null;
    Properties prop = new Properties();

    public Connection establishConnection() {
        try(FileInputStream in = new FileInputStream("Config.properties");)  {
            prop.load(in);
            connection = DriverManager.getConnection(prop.getProperty("db.url"), prop.getProperty("db.user"), prop.getProperty("db.pass"));
        } catch(Exception e) {
            e.printStackTrace();
        }

        return connection;
    }

}
