/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.stickybit.entity;

/**
 *
 * @author Asma Boussabat
 */
public class DossierMedical {
 
    private int id_dossiermedical;
    private int id_dossierPatient;
    private int id_allergie;
    private int id_intervention;
    private int id_maladie;

    public DossierMedical() {
    }

    public DossierMedical(int id_dossiermedical, int id_dossierPatient, int id_allergie, int id_intervention, int id_maladie) {
        this.id_dossiermedical = id_dossiermedical;
        this.id_dossierPatient = id_dossierPatient;
        this.id_allergie = id_allergie;
        this.id_intervention = id_intervention;
        this.id_maladie = id_maladie;
    }

     public DossierMedical(int id_dossierPatient) {      
        this.id_dossierPatient = id_dossierPatient;        
    }
    
    public int getId_dossiermedical() {
        return id_dossiermedical;
    }

    public void setId_dossiermedical(int id_dossiermedical) {
        this.id_dossiermedical = id_dossiermedical;
    }

    public int getId_dossierPatient() {
        return id_dossierPatient;
    }

    public void setId_dossierPatient(int id_dossierPatient) {
        this.id_dossierPatient = id_dossierPatient;
    }

    public int getId_allergie() {
        return id_allergie;
    }

    public void setId_allergie(int id_allergie) {
        this.id_allergie = id_allergie;
    }

    public int getId_intervention() {
        return id_intervention;
    }

    public void setId_intervention(int id_intervention) {
        this.id_intervention = id_intervention;
    }

    public int getId_maladie() {
        return id_maladie;
    }

    public void setId_maladie(int id_maladie) {
        this.id_maladie = id_maladie;
    }

    @Override
    public String toString() {
        return "DossierMedical{" + "id_dossiermedical=" + id_dossiermedical + ", id_dossierPatient=" + id_dossierPatient + ", id_allergie=" + id_allergie + ", id_intervention=" + id_intervention + ", id_maladie=" + id_maladie + '}';
    }
     
    
    
    
}
