/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stickybit.dao.classes;

import edu.stickybit.entity.LigneFacture;
import edu.stickybit.interfaceDAO.ILigneFactureDAO;
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
public class LigneFactureDAO implements ILigneFactureDAO {

    private Connection connection;

    // java.sql.Timestamp  sqlDate ;
    public LigneFactureDAO() {
        connection = MyConnectionGroup.getInstance();
    }

    public void createLigneFacture(LigneFacture ligne) {

        String requete = "insert into ligne_facture ( id_facture,id_medicament,id_intervention,quantite,prix,lib_ligne) values (?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            // ps.setInt(1, ligne.getId_ligne());
            ps.setInt(1, ligne.getId_facture());
            ps.setInt(2, ligne.getId_medicament());
            ps.setInt(3, ligne.getId_intervention());
            ps.setInt(4, ligne.getQuantite());
            ps.setFloat(5, ligne.getPrix());
            ps.setString(6, ligne.getLib_ligne());

            ps.executeUpdate();
            // System.out.println("Ajout effectuée avec succès");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de l'insertion " + ex.getMessage());
        }
    }

    public void createLigneFacture2(LigneFacture ligne) {

        String requete = "insert into ligne_facture (id_facture,id_intervention,quantite,prix,lib_ligne) values (?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            // ps.setInt(1, ligne.getId_ligne());
            ps.setInt(1, ligne.getId_facture());
            ps.setInt(2, ligne.getId_intervention());
            ps.setInt(3, ligne.getQuantite());
            ps.setFloat(4, ligne.getPrix());
            ps.setString(5, ligne.getLib_ligne());

            ps.executeUpdate();
            // System.out.println("Ajout effectuée avec succès");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de l'insertion " + ex.getMessage());
        }
    }

    public void updateLigneFacture(LigneFacture ligne) {
        String requete = "update ligne_facture set  id_medicament=?, id_intervention=? , quantite=? , lib_ligne=? where id_ligne=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);

            ps.setInt(1, ligne.getId_medicament());
            ps.setInt(2, ligne.getId_intervention());
            ps.setInt(3, ligne.getQuantite());
            ps.setString(4, ligne.getLib_ligne());
            ps.setInt(5, ligne.getId_ligne());
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }

    public void deleteLigneFacture(int id) {
        String requete = "delete from ligne_facture where id_ligne=?";
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

    public List<LigneFacture> FindLigneFacture(int id) {
        List<LigneFacture> list = new ArrayList<LigneFacture>();

        String requete = "select * from ligne_facture where id_facture=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                LigneFacture ligne = new LigneFacture();
                ligne.setId_facture(resultat.getInt(1));
                ligne.setId_ligne(resultat.getInt(2));
                ligne.setId_medicament(resultat.getInt(3));
                ligne.setId_intervention(resultat.getInt(4));
                ligne.setQuantite(resultat.getInt(5));
                ligne.setPrix(resultat.getFloat(6));
                ligne.setLib_ligne(resultat.getString(7));
                System.err.println(ligne.toString());
                list.add(ligne);
            }
            return list;

        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la recherche de la facture " + ex.getMessage());
            return null;
        }
    }

    public void ajouterMedicament(LigneFacture ligne) {

        String requete = "insert into ligne_facture ( id_facture,id_medicament,quantite,prix,lib_ligne) values (?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);

            ps.setInt(1, ligne.getId_facture());
            ps.setInt(2, ligne.getId_medicament());
            ps.setInt(3, ligne.getQuantite());
            ps.setFloat(4, ligne.getPrix());
            ps.setString(5, ligne.getLib_ligne());

            ps.executeUpdate();
            System.out.println("Ajout médicament avec succès");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de l'insertion " + ex.getMessage());
        }
    }

    public void ajouterIntervention(LigneFacture ligne) {

        String requete = "insert into ligne_facture ( id_facture,quantite,prix,id_intervention,lib_ligne) values (?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);

            ps.setInt(1, ligne.getId_facture());
            ps.setInt(2, ligne.getQuantite());
            ps.setFloat(3, ligne.getPrix());
            ps.setInt(4, ligne.getId_intervention());
            ps.setString(5, ligne.getLib_ligne());

            ps.executeUpdate();
            System.out.println("Ajout intervention avec succès");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de l'insertion " + ex.getMessage());
        }
    }

    /* public int CalculTotalFact(){
     String requete ="SELECT  SUM (prix*quantite) as totalPrix FROM ligne_facture where id_facture=?";
     try {
            
     PreparedStatement ps = connection.prepareStatement(requete);
     ps.executeUpdate();
     ResultSet rs = ps.executeQuery();
     int Total =0;
     if(rs.next()){           
     Total = rs.getInt("totalPrix");

     }
          
     return Total;
          
     } catch (SQLException ex) {
            
     }
     return 0;
     } */

    @Override
    public List<LigneFacture> DisplayAllligne() {
         List<LigneFacture> listfacture = new ArrayList<>();
        String requete = "select * from  ligne_facture";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(requete);
            while (resultat.next()) {
                LigneFacture ligne = new LigneFacture();
                ligne.setId_facture(resultat.getInt(1));
                ligne.setId_medicament(resultat.getInt(3));
                ligne.setQuantite(resultat.getInt(5));
               
                listfacture.add(ligne);
            }
           return listfacture;
           
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des depots " + ex.getMessage());
            return null;
        } 
    }
}
