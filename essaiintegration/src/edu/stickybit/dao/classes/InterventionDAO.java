/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.stickybit.dao.classes;

import edu.stickybit.entity.Intervention;
import edu.stickybit.interfaceDAO.IInterventionAO;
import edu.stickybit.technique.MyConnectionGroup;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Dontey
 */
public class InterventionDAO  implements IInterventionAO{
    
    public   List<Intervention> listemed = new ArrayList<Intervention>();

   private Connection connection;
    java.sql.Timestamp sqlDate;
   public InterventionDAO() {
    connection = MyConnectionGroup.getInstance();
    }
   
   public void createIntervention(Intervention inter) {

        String requete = "insert into intervention (lib_intervention,prix_interv,date_interv) values (?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
           
            ps.setString(1, inter.getLib_intervention());
            ps.setFloat(2, inter.getPrix_interv());
            ps.setDate(3, inter.getDate_interv());
            

            ps.executeUpdate();
            System.out.println("Ajout effectuée avec succès");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de l'insertion " + ex.getMessage());
        }
    }
   
    @Override
    public Intervention findIntervBylibelle(String lib) {
         Intervention interv = new Intervention();
        String requete = "select * from intervention where lib_intervention=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1,lib);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
               interv.setId_intervention(resultat.getInt(1));
               interv.setLib_intervention(resultat.getString(2));
               interv.setPrix_interv(resultat.getFloat(3));
               interv.setDate_interv(resultat.getDate(4));
               
            }
      

            return interv;
          
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
           System.out.println("erreur lors de la recherche du medicamnt " + ex.getMessage());
            return null;
        }
    }
    
    public List<Intervention> DisplayAllInterventions() {
        List<Intervention> listeInterv = new ArrayList<Intervention>();

        String requete = "select * from intervention";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Intervention inte = new Intervention();
                inte.setId_intervention(resultat.getInt(1));
              //  inte.setDate_interv(resultat.getDate(2));
                inte.setPrix_interv(resultat.getFloat(3));
                inte.setLib_intervention(resultat.getString(2));

                listeInterv.add(inte);
            }
            return listeInterv;

        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des factures " + ex.getMessage());
            return null;
        }
    }

}
