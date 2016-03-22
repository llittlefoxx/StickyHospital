/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.stickybit.general;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Tlili Mohamed Ali
 */


public class Prix {
   public static String prix_nuit;
 Properties properties;
    public Prix() {
        properties = new Properties();
        try {
            properties.load(new FileInputStream(new File("prix.properties")));
            prix_nuit = properties.getProperty("prixdujour");

        } catch (IOException e) {
            
        }

        
    }

    
   
}