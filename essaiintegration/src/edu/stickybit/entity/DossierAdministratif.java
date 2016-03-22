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
public class DossierAdministratif {
    private int id_dadmin;
    private int id_dossierPatient;
    private Date date_creation;

    public DossierAdministratif() {
    }

    public DossierAdministratif(int id_dadmin,int id_dossierPatient, Date date_creation) {
        this.id_dadmin = id_dadmin;
        this.date_creation = date_creation;
    }

    
     public DossierAdministratif(int id_dossierPatient, Date date_creation) {
         
        this.id_dadmin = id_dadmin;
        this.id_dossierPatient=id_dossierPatient;
        this.date_creation = date_creation;
    }
      public DossierAdministratif(int id_dossierPatient) {
       
        this.id_dossierPatient=id_dossierPatient;
      
    }
     
     public DossierAdministratif(Date date_creation) {
     
        this.date_creation = date_creation;
    }

    public int getId_dossierPatient() {
        return id_dossierPatient;
    }

    public void setId_dossierPatient(int id_dossierPatient) {
        this.id_dossierPatient = id_dossierPatient;
    }
    
    public int getId_dadmin() {
        return id_dadmin;
    }

    public void setId_dadmin(int id_dadmin) {
        this.id_dadmin = id_dadmin;
    }

    public Date getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }

    @Override
    public String toString() {
        return "DossierAdministratif{" + "id_dadmin=" + id_dadmin + ", id_dossierPatient=" + id_dossierPatient + ", date_creation=" + date_creation + '}';
    }

   
    
    
}
