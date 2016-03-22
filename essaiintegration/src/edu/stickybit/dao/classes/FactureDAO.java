/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stickybit.dao.classes;

import edu.stickybit.technique.MyConnectionGroup;
import edu.stickybit.interfaceDAO.IFactureDAO;
import edu.stickybit.entity.Facture;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dontey
 */
public class FactureDAO implements IFactureDAO {

    private Connection connection;
    java.util.Date d = new java.util.Date();
  //  java.sql.Timestamp sqlDate;

    public FactureDAO() {
        connection = MyConnectionGroup.getInstance();
    }

    public void createFacture(Facture facture) {

       // sqlDate = new Timestamp(facture.getDate_facturation().getTime());
        String requete = "insert into facture (id_patient,id_dadmin,date_facturation,etat,total_facture) values (?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, facture.getId_patient());
            ps.setInt(2, facture.getId_dadmin());
            ps.setTimestamp(3, (Timestamp) d);
            ps.setBoolean(4, facture.isEtat());
            ps.setFloat(5, facture.getTotal_facture());

            ps.executeUpdate();
            System.out.println("Ajout effectuée avec succès");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de l'insertion " + ex.getMessage());
        }
    }

    public void ajoutFacture(Facture facture) {

      //  sqlDate = new Timestamp(facture.getDate_facturation().getTime());
        String requete = "insert into facture (id_patient,id_dadmin,etat,total_facture) values (?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, facture.getId_patient());
            ps.setInt(2, facture.getId_dadmin());
            ps.setBoolean(3, facture.isEtat());
            ps.setFloat(4, facture.getTotal_facture());
            

            ps.executeUpdate();
            System.out.println("Ajout effectuée avec succès");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de l'insertion " + ex.getMessage());
        }
    }

        public void updateFacture(Facture Facture) {
        String requete = "update facture set  total_facture=?, etat=? where id_facture=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setFloat(1, Facture.getTotal_facture());
            ps.setBoolean(2, Facture.isEtat());
            ps.setInt(3, Facture.getId_facture());
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }
    
    public void updateTotalFacture(Facture Facture) {
        String requete = "update facture set  total_facture=? where id_facture=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setFloat(1, Facture.getTotal_facture());
            ps.setInt(2, Facture.getId_facture());
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }
    
    
    
    
 

    
    public void deleteFacture(int id) {
        String requete = "delete from facture where id_facture=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Suppression effectuée avec succès");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }

    public Facture findFactureById(int id) {
        Facture facture = new Facture();
        String requete = "select * from facture where id_facture=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                facture.setId_facture(resultat.getInt(1));
                facture.setId_patient(resultat.getInt(2));
                facture.setId_dadmin(resultat.getInt(3));
                facture.setDate_facturation(resultat.getDate(4)); 
                facture.setEtat(resultat.getBoolean(5));
                facture.setTotal_facture(resultat.getFloat(6));
            }
      //      System.out.println("La facture ayant comme id " + facture.getId_facture() + " est " + facture.getId_patient() + " " + facture.getDate_facturation() + " " + facture.getTotal_facture() + " " + facture.isEtat());
            return facture;

        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la recherche de la facture " + ex.getMessage());
            return null;
        }
    } 
    public int retourId() throws SQLException{
        int id1=0;
       String requete = "select max(Id_facture) FROM facture";
       PreparedStatement ps = connection.prepareStatement(requete);
        ResultSet resultat = ps.executeQuery();
       try {
           
           
            while (resultat.next()) {
            id1=resultat.getInt(1);
            }
           return id1;
        } catch (Exception e) {
        }
        
        return id1;
        
        
    }
   
    public List<Facture> FindFactureParId(int id){
        List<Facture> list = new ArrayList<Facture>();
        
        String requete = "select * from facture where id_facture=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                Facture facture = new Facture();
                facture.setId_facture(resultat.getInt(1));
                facture.setId_patient(resultat.getInt(2));
                facture.setId_dadmin(resultat.getInt(3));
                facture.setDate_facturation(resultat.getDate(4));
                facture.setEtat(resultat.getBoolean(5));
                facture.setTotal_facture(resultat.getFloat(6));
                list.add(facture);
            }
         return list;

        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la recherche de la facture " + ex.getMessage());
            return null;
        }
    }
    
    public List<Facture> FindFactureParNom(String nom){
        List<Facture> list = new ArrayList<Facture>();
        
        String requete = "select * from facture where nom_patient=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, nom);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                Facture facture = new Facture();
                facture.setId_facture(resultat.getInt(1));
                facture.setId_patient(resultat.getInt(2));
                facture.setId_dadmin(resultat.getInt(3));
                facture.setDate_facturation(resultat.getDate(4));
                facture.setEtat(resultat.getBoolean(5));
                facture.setTotal_facture(resultat.getFloat(6));
                list.add(facture);
            }
         return list;

        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la recherche de la facture " + ex.getMessage());
            return null;
        }
    }
    
    public List<Facture> FindFactureParCin(int cin){
        List<Facture> list = new ArrayList<Facture>();
        
        String requete = "select * from facture where id_patient= (select id_patient from patient where cin_patient=(?))" ;
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, cin);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                Facture facture = new Facture();
                facture.setId_facture(resultat.getInt(1));
                facture.setId_patient(resultat.getInt(2));
                facture.setId_dadmin(resultat.getInt(3));
                facture.setDate_facturation(resultat.getDate(4));
                facture.setEtat(resultat.getBoolean(5));
                facture.setTotal_facture(resultat.getFloat(6));
                list.add(facture);
            }
         return list;

        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la recherche de la facture " + ex.getMessage());
            return null;
        }
    }
        

    
               public List<Facture> DisplayAllFactures() {
        List<Facture> listefactures = new ArrayList<Facture>();

        String requete = "select * from facture";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Facture facture = new Facture();
                facture.setId_facture(resultat.getInt(1));
                facture.setId_patient(resultat.getInt(2));
                facture.setId_dadmin(resultat.getInt(3));
                facture.setDate_facturation(resultat.getDate(4));
                facture.setEtat(resultat.getBoolean(5));
                facture.setTotal_facture(resultat.getFloat(6));

                listefactures.add(facture);
            }
            return listefactures;

        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des factures " + ex.getMessage());
            return null;
        }
    }
       
    

    private static IFactureDAO iFactureDAO;

    public static IFactureDAO getInstance() {
        if (iFactureDAO == null) {
            iFactureDAO = new FactureDAO();
        }
        return iFactureDAO;
    }

        public int ajoutFacture2(Facture facture) {
   
        String requete = "insert into facture (id_patient,id_dadmin,date_facturation,total_facture, etat) values (?,?,?,?,?)";
        String requete2="select last_insert_id() as id_facture from facture";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
               PreparedStatement ps2 = connection.prepareStatement(requete2);
            ps.setInt(1, facture.getId_patient());
            ps.setInt(2, facture.getId_dadmin());
            ps.setDate(3, (Date) facture.getDate_facturation());
            ps.setFloat(4, facture.getTotal_facture());
            ps.setBoolean(5, facture.isEtat());
            
            
            

            ps.executeUpdate();
            
            System.out.println("Ajout effectuée avec succès");
            
             ResultSet rs = ps2.executeQuery();
          int lastid=0;
           if(rs.next()){           
                lastid = rs.getInt("id_facture");

           }
           System.err.println(lastid);
           return lastid;
            
           
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de l'insertion " + ex.getMessage());
        }
        return 0;
    }

    @Override
    public int retourIdFact(int id_patient) {
       
            int id1=0;
            String requete = "select max(Id_facture) FROM facture where id_patient=?";
            Facture facture = new Facture();
            
            try {
                PreparedStatement prep = connection.prepareStatement(requete);
                 prep.setInt(1,id_patient);
                 ResultSet resultat = prep.executeQuery();                
                while (resultat.next()) {            
                   prep.setInt(1,resultat.getInt(1));
                    id1=resultat.getInt(1);
                }
                return id1;
            } catch (Exception e) {
            }
        return id1;
    
    }}