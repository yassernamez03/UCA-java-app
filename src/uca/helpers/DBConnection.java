/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uca.helpers;

import java.sql.*;

/**
 *
 * @author admin
 */
public class DBConnection {
    private static Connection connection;

    public static Connection getconnect() throws SQLException {
        connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","1234");
        return connection;    
    }
}
