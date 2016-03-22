/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stickybit.general;

import edu.stickybit.entity.DossierAdministratif;
import edu.stickybit.entity.DossierMedical;
import edu.stickybit.entity.DossierPatient;
import edu.stickybit.entity.Patient;
import edu.stickybit.dao.classes.DossierAdministratifDAO;
import edu.stickybit.dao.classes.DossierMedicalDAO;
import edu.stickybit.dao.classes.DossierPatientDAO;
import edu.stickybit.dao.classes.PatientDAO;
import edu.stickybit.technique.MyConnectionGroup;
import java.util.Date;

/**
 *
 * @author Asma Boussabat
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      // TODO code application logic here

        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

        MyConnectionGroup.getInstance();
        
         /*   test des CRUD du patient    */
      
       
        /*
         Patient p1=new Patient("ayoub","wartani",9,"tunis","7777","ayoub@esprit.tn","img","m");
         Patient p2=new Patient("xxxx","wartani",9,"tunis","7777","ayoub@esprit.tn","img","m");
         PatientDAO pp=new PatientDAO();
         DossierPatientDAO dossier_p=new DossierPatientDAO();
         pp.deletePatient(3);
     
         dossier_p.deleteDossierPatient(3);
         pp.insertPatient(p2);
    
         pp.deletePatient(2);
         pp.updatePatient(p1);
         pp.findPatientById(3);
         pp.DisplayAllPatient();
     
         */

        /* Test des CRUD du dossier  */
        
        /*
         DossierPatient d=new DossierPatient(1,sqlDate);
         DossierPatientDAO dossier_p = new DossierPatientDAO();
         dossier_p.insertDossierPatient(d);
         dossier_p.deleteDossierPatient(2);
         dossier_p.DisplayAllDossierPatient();
         dossier_p.findDossierPatientById(3);
         */
        
        
        /* test des CRUD du dossier administratif */
     
       /*
      DossierAdministratif d=new DossierAdministratif(7,sqlDate);
      DossierAdministratifDAO doss=new DossierAdministratifDAO();
      doss.insertDossierAdministratif(d);
      doss.deleteDossierAdministratif(7);
      doss.findDossierAdministratiftById(7);      
      doss.DisplayAllDossierAdministratif();
      */
      
        
        /* test des CRUD du dossier medical */
        
      /* 
      DossierMedicalDAO dd=new DossierMedicalDAO();
      dd.DisplayAllDossierMedical();
      dd.findDossierMedicaltById(3);
                
      */          
              
        
        Patient p1=new Patient("Tlili","Dali",9,"Ariana","55555","dali@esprit.tn","img","m");
        Patient p2=new  Patient("Boussabat","Asma",456,"bizerte","7777","asma@esprit.tn","img","f");
        Patient p3=new  Patient("Wertatani","Ayoub",123,"Mourouj","8888","ayoub@esprit.tn","img","m");
        PatientDAO pp=new PatientDAO();
       
        
       //pp.insertPatient(p1);
       //pp.insertPatient(p2);
      //  pp.insertPatient(p3);
          
    // pp.deletePatient(1);
      // pp.deletePatient(2);
  
    }

}
