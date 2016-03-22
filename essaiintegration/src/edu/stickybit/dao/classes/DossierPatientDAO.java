/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stickybit.dao.classes;

import edu.stickybit.entity.DossierPatient;
import edu.stickybit.entity.Patient;
import edu.stickybit.interfaceDAO.IDossierPatientDAO;
import edu.stickybit.technique.MyConnectionGroup;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asma Boussabat
 */
public class DossierPatientDAO implements IDossierPatientDAO {

    private Connection connection;

    public DossierPatientDAO() {
        connection = MyConnectionGroup.getInstance();
    }

    @Override
    public int insertDossierPatient(DossierPatient dossier_p) {
        int lastid=0;

        String requete = "insert into dossier (id_patient,date_creation_dossier) values (?,?)";
        String requete2="select last_insert_id() as id_dossierPatient from dossier";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            PreparedStatement ps2 = connection.prepareStatement(requete2);
            ps.setInt(1, dossier_p.getId_patient());
            ps.setDate(2, dossier_p.getDate_creation_dossier());
            ps.executeUpdate();
            System.out.println("ajout du dossier avec succé");
            
             ResultSet rs2 = ps2.executeQuery();
          
           if(rs2.next()){           
                lastid = rs2.getInt("id_dossierPatient");
              } 
           return lastid;
          
        } catch (SQLException ex) {
            Logger.getLogger(PatientDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         return lastid;
    }

    @Override
    public void deleteDossierPatient(int id_patient) {
        String requete = "delete from dossier where id_patient=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id_patient);
            ps.executeUpdate();
            System.out.println("dossier supprimé");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression du dossier " + ex.getMessage());
        }
    }

    @Override
    public DossierPatient findDossierPatientById(int id_dossier_p) {

        DossierPatient dossier_p = new DossierPatient();
        String requete = "select * from dossier where id_dossierPatient=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id_dossier_p);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                dossier_p.setId_dossierPatient(resultat.getInt(1));
                dossier_p.setId_patient(resultat.getInt(2));
                dossier_p.setDate_creation_dossier(resultat.getDate(3));
                System.out.println(dossier_p.toString());
            }
            return dossier_p;

        } catch (SQLException ex) {
            System.out.println("erreur lors de la recherche du dossier " + ex.getMessage());
            return null;
        }
    }

    public List<DossierPatient> DisplayAllDossierPatient() {
        List<DossierPatient> listeDossierPatient = new ArrayList();
        String requete = "select * from dossier";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);

            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                DossierPatient dossier = new DossierPatient();

                dossier.setId_dossierPatient(resultat.getInt(1));
                dossier.setId_patient(resultat.getInt(2));
                dossier.setDate_creation_dossier(resultat.getDate(3));
                System.out.println(dossier.toString());

                listeDossierPatient.add(dossier);
            }

            return listeDossierPatient;

        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de l'affichage des dossiers des patient " + ex.getMessage());
            return null;
        }
    }
}
