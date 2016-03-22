/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.stickybit.dao.classes;

import edu.stickybit.entity.DossierAdministratif;
import edu.stickybit.entity.DossierMedical;
import edu.stickybit.interfaceDAO.IDossierMedicalDAO;
import edu.stickybit.technique.MyConnectionGroup;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asma Boussabat
 */
public class DossierMedicalDAO implements IDossierMedicalDAO {

    
     private Connection connection;
     public DossierMedicalDAO() {
        connection = MyConnectionGroup.getInstance();
    }
   
    @Override
    public void insertDossierMedical(DossierMedical dossier) {
        String requete = "insert into dossier_medical (id_dossierPatient) values (?)";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1,PatientDAO.i);
            ps.executeUpdate();
            System.out.println("ajout du dossier medical avec succé");
        } catch (SQLException ex) {
            Logger.getLogger(PatientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteDossierMedical(int id_dossierPatient) {
     String requete = "delete from dossier_medical where id_dossierPatient=?";
       
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1,id_dossierPatient);
            ps.executeUpdate();
            System.out.println("dossier medical supprimé");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression du dossier medical" + ex.getMessage());
        } 
    }

    @Override
    public DossierMedical findDossierMedicaltById(int id_dossierPatient) {
         DossierMedical dossier= new DossierMedical();
        String requete = "select * from dossier_medical where id_dossierPatient=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id_dossierPatient);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                dossier.setId_dossiermedical(resultat.getInt(1));
                dossier.setId_dossierPatient(resultat.getInt(2));           
                dossier.setId_allergie(resultat.getInt(3));
                dossier.setId_intervention(resultat.getInt(4));
                dossier.setId_maladie(resultat.getInt(5));
                
                System.out.println(dossier.toString());
            }
            return dossier;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du dossier medical" + ex.getMessage());
            return null;
        }}

    @Override
    public List<DossierMedical> DisplayAllDossierMedical() {
       List<DossierMedical> listeDossierMedical = new ArrayList();
        String requete = "select * from dossier_medical";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);

            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                DossierMedical dossier = new DossierMedical();

                dossier.setId_dossiermedical(resultat.getInt(1));
                dossier.setId_dossierPatient(resultat.getInt(2));
                dossier.setId_allergie(resultat.getInt(3));
                dossier.setId_intervention(resultat.getInt(4));
                dossier.setId_maladie(resultat.getInt(5));
                System.out.println(dossier.toString());

                listeDossierMedical.add(dossier);
            }

            return listeDossierMedical;

        } catch (SQLException ex) {
            
            System.out.println("erreur lors de l'affichage des dossiers medicaux " + ex.getMessage());
            return null;
        }
    }
    
}
