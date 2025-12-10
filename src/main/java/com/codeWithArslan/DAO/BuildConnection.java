package com.codeWithArslan.DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class BuildConnection {
    Connection connection = null;
    String url = "jdbc:sqlserver://localhost:1433;database=Course_Registration;encrypt=true;trustServerCertificate=true;";
    String user = "sa";
    String password = "12345";

    public Connection establishConnection() {
        try  {
            connection = DriverManager.getConnection(url, user, password);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return connection;
    }

}
