/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.stickybit.entity;

import java.sql.Date;

/**
 *
 * @author Asma Boussabat
 */

public class DossierPatient {
    
  
    private int id_dossierPatient;
    private int id_patient;
    private Date date_creation_dossier;

    public DossierPatient() {
    }
    public DossierPatient(int id_dossierPatient, int id_patient, Date date_creation_dossier) {
        this.id_dossierPatient = id_dossierPatient;
        this.id_patient = id_patient;
        this.date_creation_dossier = date_creation_dossier;
    }

     public DossierPatient(int id_patient,Date date_creation_dossier) {
         this.id_patient = id_patient;
       this.date_creation_dossier = date_creation_dossier;
    }
     public DossierPatient(int id_patient) {
         this.id_patient = id_patient;
       
    }
     
    public int getId_dossierPatient() {
        return id_dossierPatient;
    }

    public void setId_dossierPatient(int id_dossierPatient) {
        this.id_dossierPatient = id_dossierPatient;
    }

    public int getId_patient() {
        return id_patient;
    }

    public void setId_patient(int id_patient) {
        this.id_patient = id_patient;
    }

    public Date getDate_creation_dossier() {
        return date_creation_dossier;
    }

    public void setDate_creation_dossier(Date date_creation_dossier) {
        this.date_creation_dossier = date_creation_dossier;
    }

    @Override
    public String toString() {
        return "DossierPatient{" + "id_dossierPatient=" + id_dossierPatient + ", id_patient=" + id_patient + ", date_creation_dossier=" + date_creation_dossier + '}';
    }
    
    
    
}
