/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.stickybit.dao.classes;

import edu.stickybit.entity.DossierAdministratif;
import edu.stickybit.entity.DossierPatient;
import edu.stickybit.interfaceDAO.IDossierAdministratifDAO;
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
public class DossierAdministratifDAO implements IDossierAdministratifDAO{

    private Connection connection;
    public DossierAdministratifDAO() {
        connection = MyConnectionGroup.getInstance();
    }

    
    @Override
    public void insertDossierAdministratif(DossierAdministratif dossier) {

        String requete = "insert into dossier_administratif (id_dossierPatient,date_creation) values (?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1,PatientDAO.i);
            ps.setDate(2,dossier.getDate_creation());
            ps.executeUpdate();
            System.out.println("ajout du dossier administratif avec succé");
        } catch (SQLException ex) {
            Logger.getLogger(PatientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteDossierAdministratif(int id_dossierPatient) {
       String requete = "delete from dossier_administratif where id_dossierPatient=?";
       
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1,id_dossierPatient);
            ps.executeUpdate();
            System.out.println("dossier administratif supprimé");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression du dossier administratif" + ex.getMessage());
        } 
    }
    
    @Override
    public DossierAdministratif findDossierAdministratiftById(int id_dossierPatient) {
       DossierAdministratif dossier= new DossierAdministratif();
        String requete = "select * from dossier_administratif where id_dossierPatient=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id_dossierPatient);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                dossier.setId_dadmin(resultat.getInt(1));
                dossier.setId_dossierPatient(resultat.getInt(2));
                dossier.setDate_creation(resultat.getDate(3));
                System.out.println(dossier.toString());
            }
            return dossier;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du dossier administratif" + ex.getMessage());
            return null;
        }
    }

    @Override
    public List<DossierAdministratif> DisplayAllDossierAdministratif() {
    List<DossierAdministratif> listeDossierAdministratif = new ArrayList();
        String requete = "select * from dossier_administratif";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);

            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                DossierAdministratif dossier = new DossierAdministratif();

                dossier.setId_dadmin(resultat.getInt(1));
                dossier.setId_dossierPatient(resultat.getInt(2));
                dossier.setDate_creation(resultat.getDate(3));
                System.out.println(dossier.toString());

                listeDossierAdministratif.add(dossier);
            }

            return listeDossierAdministratif;

        } catch (SQLException ex) {
            
            System.out.println("erreur lors de l'affichage des dossiers administratifs " + ex.getMessage());
            return null;
        }
    }
}
