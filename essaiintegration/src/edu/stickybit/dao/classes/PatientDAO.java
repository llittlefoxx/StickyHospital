/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.stickybit.dao.classes;

import edu.stickybit.entity.DossierAdministratif;
import edu.stickybit.entity.DossierMedical;
import edu.stickybit.entity.DossierPatient;
import edu.stickybit.entity.Patient;
import edu.stickybit.interfaceDAO.IPatientDAO;
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
public class PatientDAO  implements IPatientDAO {

    java.util.Date utilDate = new java.util.Date();
    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
    public static int i=0;
     private Connection connection;
     
      public PatientDAO() {
        connection = MyConnectionGroup.getInstance();
    }
    @Override
    public int insertPatient(Patient patient) {
       String requete = "insert into patient (nom_patient,prenom_patient,cin_patient,adresse,tel_patient,mail_patient,url_photo_patient,sex_patient,date_n_patient) values (?,?,?,?,?,?,?,?,?)";
        String requete2 = "select last_insert_id() as id_patient from patient";

        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            PreparedStatement ps2 = connection.prepareStatement(requete2);
            ps.setString(1, patient.getNom_patient());
            ps.setString(2, patient.getPrenom_patient());
            ps.setInt(3, patient.getCin_patient());
            ps.setString(4, patient.getAdresse());
            ps.setString(5, patient.getTel_patient());
            ps.setString(6, patient.getMail_patient());
            ps.setString(7, patient.getUrl_photo_patient());
            ps.setString(8, patient.getSex_patient());
            ps.setDate(9, patient.getDate_n_patient());

            ps.executeUpdate();
            System.out.println("ajout du patient terminé avec succé");

            // insertion de son dossier            
            ResultSet rs = ps2.executeQuery();
            int lastid = 0;
            if (rs.next()) {
                lastid = rs.getInt("id_patient");

            }

            DossierPatient d = new DossierPatient(lastid, sqlDate);
            DossierPatientDAO dossier_p = new DossierPatientDAO();
            i = dossier_p.insertDossierPatient(d);

            // insertion de son dossier administratif         
            DossierAdministratif dd = new DossierAdministratif(i, sqlDate);
            DossierAdministratifDAO doss = new DossierAdministratifDAO();
            doss.insertDossierAdministratif(dd);
            //insertion de son dossier medical
            DossierMedical dm = new DossierMedical(i);
            DossierMedicalDAO dossm = new DossierMedicalDAO();
            dossm.insertDossierMedical(dm);

            return 1;
        } catch (SQLException ex) {
            Logger.getLogger(PatientDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }

    }


    @Override
    public void updatePatient(Patient patient) {
             
         String requete = "update patient set nom_patient=? , prenom_patient=? ,cin_patient=? , adresse=? , tel_patient=? , mail_patient=? , url_photo_patient=?, sex_patient=? where id_patient=? ";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
         
            ps.setString(1,patient.getNom_patient());
            ps.setString(2,patient.getPrenom_patient());
            ps.setInt(3,patient.getCin_patient());
            ps.setString(4,patient.getAdresse());
            ps.setString(5, patient.getTel_patient());
            ps.setString(6,patient.getMail_patient());
            ps.setString(7,patient.getUrl_photo_patient());
            ps.setString(8,patient.getSex_patient());
            ps.setInt(9,patient.getId_patient());
             
            
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
           
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }

    
    
     @Override
    public int updatePatientG(Patient patient) {
        
         String requete = "update patient set nom_patient=? , prenom_patient=? ,cin_patient=? , adresse=? , tel_patient=? , mail_patient=? where id_patient=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
         
            ps.setString(1,patient.getNom_patient());
            ps.setString(2,patient.getPrenom_patient());
            ps.setInt(3,patient.getCin_patient());
            ps.setString(4,patient.getAdresse());
            ps.setString(5, patient.getTel_patient());
            ps.setString(6,patient.getMail_patient());
            ps.setInt(7, patient.getId_patient());
        //  ps.setDate(11, (java.sql.Date) d);
        //  ps.setDate(12, (java.sql.Date) d);
       
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
            return 1;
        } catch (SQLException ex) {
           
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
            return 0;
        }
    }
    
    
    
    
    @Override
    public int deletePatient(int i) {
       String requete = "delete from patient where id_patient=?";
        try {
            
            //supression du dossier medical du patient      
            DossierMedicalDAO doss= new DossierMedicalDAO();
            doss.deleteDossierMedical(i);
            
            //supression du dossier administratif du patient      
            DossierAdministratifDAO dossier= new DossierAdministratifDAO();
            dossier.deleteDossierAdministratif(i);            
            
            //supression du dossier du patient      
            DossierPatientDAO dossier_p= new DossierPatientDAO();
            dossier_p.deleteDossierPatient(i);            
                                
           //suppression du patient
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1,i);
            ps.executeUpdate();
            
            System.out.println("Patient supprimé");
            return 1;
          
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la suppression " + ex.getMessage());
            return 0;
        }
    }

    public void deletePatient2(String nom_patient) {
       String requete = "delete from patient where nom_patient=?";
        try {
            
            //supression du dossier medical du patient      
            DossierMedicalDAO doss= new DossierMedicalDAO();
            doss.deleteDossierMedical(i);
            
            //supression du dossier administratif du patient      
            DossierAdministratifDAO dossier= new DossierAdministratifDAO();
            dossier.deleteDossierAdministratif(i);            
            
            //supression du dossier du patient      
            DossierPatientDAO dossier_p= new DossierPatientDAO();
            dossier_p.deleteDossierPatient(i);            
                                
           //suppression du patient
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1,i);
            ps.executeUpdate();
            System.out.println("Patient supprimé");
           
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }

    
    @Override
    public Patient findPatientById(int id_p) {
      Patient patient = new Patient();
        String requete = "select * from patient where id_patient=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, id_p);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                patient.setId_patient(resultat.getInt(1));
                patient.setNom_patient(resultat.getString(2));
                patient.setPrenom_patient(resultat.getString(3));
                patient.setCin_patient(resultat.getInt(4));
                patient.setAdresse(resultat.getString(5));
                patient.setTel_patient(resultat.getString(6));
                patient.setMail_patient(resultat.getString(7));
                patient.setUrl_photo_patient(resultat.getString(8));
                patient.setSex_patient(resultat.getString(9));              
                System.out.println(patient.toString());
             }
               return patient;
              
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la recherche du patient " + ex.getMessage());
            return null;
        }
    }

    public List<Patient> findPatientByName(String nom_patient) {
      Patient patient = new Patient();
      List<Patient> lisP=new ArrayList<>();
        String requete = "select * from patient where UPPER(nom_patient) LIKE UPPER(?)";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, "%"+nom_patient+"%");
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                patient.setId_patient(resultat.getInt(1));
                patient.setNom_patient(resultat.getString(2));
                patient.setPrenom_patient(resultat.getString(3));
                patient.setCin_patient(resultat.getInt(4));
                patient.setAdresse(resultat.getString(5));
                patient.setTel_patient(resultat.getString(6));
                patient.setMail_patient(resultat.getString(7));
                patient.setUrl_photo_patient(resultat.getString(8));
                patient.setSex_patient(resultat.getString(9));              
                System.out.println(patient.toString());
            lisP.add(patient);
            }
               return lisP;
              
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la recherche du patient " + ex.getMessage());
            return null;
        }
    }
    
    @Override
    public List<Patient> DisplayAllPatient() {

        List<Patient> listePatient = new ArrayList();
        String requete = "select * from patient";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
         
            ResultSet resultat = ps.executeQuery();
              while (resultat.next()) {
                Patient patient = new Patient();
                
                patient.setId_patient(resultat.getInt(1));
                patient.setNom_patient(resultat.getString(2));
                patient.setPrenom_patient(resultat.getString(3));
                patient.setCin_patient(resultat.getInt(4));
                patient.setAdresse(resultat.getString(5));
                patient.setTel_patient(resultat.getString(6));
                patient.setMail_patient(resultat.getString(7));
                patient.setUrl_photo_patient(resultat.getString(8));
                patient.setSex_patient(resultat.getString(9));              
                System.out.println(patient.toString());

                listePatient.add(patient);
            }
           
            return listePatient;

        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de l'affichage des patients " + ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Patient> findPatientByCin(int cin) {
        Patient patient = new Patient();
      List<Patient> lisP=new ArrayList<>();
        String requete = "select * from patient where cin_patient=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, cin);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                patient.setId_patient(resultat.getInt(1));
                patient.setNom_patient(resultat.getString(2));
                patient.setPrenom_patient(resultat.getString(3));
                patient.setCin_patient(resultat.getInt(4));
                patient.setAdresse(resultat.getString(5));
                patient.setTel_patient(resultat.getString(6));
                patient.setMail_patient(resultat.getString(7));
                patient.setUrl_photo_patient(resultat.getString(8));
                patient.setSex_patient(resultat.getString(9));              
                //System.out.println(patient.toString());
            lisP.add(patient);
            }
               return lisP;
              
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la recherche du patient " + ex.getMessage());
            return null;
        }
    }
    
}
