/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stickybit.general;

import edu.stickybit.dao.classes.MedecinDAO;
import edu.stickybit.technique.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;

/**
 *
 * @author Tlili Mohamed Ali
 */
public class InsertionImage {

    private Connection connection;

    public void insertImg(String url) {
        String requete = "insert into testimg (img) values(?)";
        try {
            MyConnection cnx = new MyConnection();
            connection = cnx.dbCnx();
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, url);
            ps.executeUpdate();
            System.out.println("ajout");
        } catch (Exception ex) {
            Logger.getLogger(MedecinDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public String getImg(int id) throws SQLException{
        String x=null; 
         MyConnection cnx = new MyConnection();
            connection = cnx.dbCnx();
        String requete = "select img from testimg where id=?";
        try {
            
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ps.executeQuery();
            ResultSet resultat = ps.executeQuery();
             while (resultat.next()) {
            x=resultat.getString(1);
           
             }
            return x;
        } catch (Exception e) {
        }
        return x;
    }
}
