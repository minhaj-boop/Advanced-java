/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbconsoledemo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author HP
 */
public class DBConnection {

    private static Connection connection = null;
    private static DBConnection instance = new DBConnection();

    private DBConnection() {
        try {
            //FileReader fileReader = new FileReader("db.properties");
            //Properties properties = new Properties();
            //properties.load(fileReader);

            //String userName = properties.getProperty("username");
            //String password = properties.getProperty("password");
            //String URL = properties.getProperty("URL");

            String userName = System.getProperty("username");
            String password = System.getProperty("password");
            String URL = System.getProperty("URL");
            System.out.printf("Username: password as environment variable: [%s]:[%s]\n", userName, password);
            connection = DriverManager.getConnection(URL, userName, password);
            System.out.println("Connection created.");
            //}catch (FileNotFoundException ex) {
            //  Logger.getLogger(DBConsoleDemo.class.getName()).log(Level.SEVERE, null, ex);
            //} catch (IOException ex) {
            //  Logger.getLogger(DBConsoleDemo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public static Connection getConnection() {
        return connection;
    }
}
