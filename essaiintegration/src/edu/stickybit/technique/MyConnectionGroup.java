/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.stickybit.technique;

/**
 *
 * @author Asma Boussabat
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MyConnectionGroup {

    Properties properties;
    private String url;
    private String login;
    private String password;
    private static Connection instance;

    public MyConnectionGroup() {
            try {
                properties =new Properties();
                properties.load(new FileInputStream(new File("Configuration.properties")));
                url = properties.getProperty("url");
                login = properties.getProperty("utilisateur");
                password = properties.getProperty("pwd");                
                instance= DriverManager.getConnection(url, login, password);
                System.out.println("Connection established");
            } catch (SQLException | IOException ex) {
                Logger.getLogger(MyConnectionGroup.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    public static Connection getInstance() {
        if (instance == null) {
            new MyConnectionGroup();
        }
        return instance;
    }
}
