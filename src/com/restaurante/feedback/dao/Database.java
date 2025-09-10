//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.restaurante.feedback.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static Database instance;
    private final String url = "xxxxx";
    private final String user = "xxxxx";
    private final String password = "xxxxx";

    private Database() throws SQLException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver Oracle JDBC não encontrado. Adicione ojdbc8.jar ao classpath.", e);
        }
    }

    public static synchronized Database getInstance() throws SQLException {
        if (instance == null) {
            instance = new Database();
        }

        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl", "rm558610", "201002");
    }
}
