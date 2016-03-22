/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.stickybit.entity;

import java.util.Date;

/**
 *
 * @author Dontey
 */
public class Facture {
    
    private int id_facture;
    private int id_patient;
    private int id_dadmin;
    private Date date_facturation;
    private float total_facture;
    private boolean etat;

    public Facture(float total_facture) {
        this.total_facture = total_facture;
    }

    public Facture(int id_facture, float total_facture) {
        this.id_facture = id_facture;
        this.total_facture = total_facture;
    }
    
    

    public Facture(java.sql.Date date_facturation, float total_facture, boolean etat) {
        this.date_facturation = date_facturation;
        this.total_facture = total_facture;
        this.etat = etat;
    }

    public Facture(int id_facture, int id_dadmin, java.sql.Date date_facturation) {
        this.id_facture = id_facture;
        this.id_dadmin = id_dadmin;
        this.date_facturation = date_facturation;
    }
    

    public Facture(int id_facture, float total_facture, boolean etat) {
        this.id_facture = id_facture;
        this.total_facture = total_facture;
        this.etat = etat;
    }
    

    public Facture(float total_facture, boolean etat) {
        this.total_facture = total_facture;
        this.etat = etat;
    }

    public Facture(int id_patient, int id_dadmin, float total_facture, boolean etat) {
        this.id_patient = id_patient;
        this.id_dadmin = id_dadmin;
        this.total_facture = total_facture;
        this.etat = etat;
    }

    public Facture(int id_patient, int id_dadmin, java.util.Date date_facturation, float total_facture, boolean etat) {
        this.id_patient = id_patient;
        this.id_dadmin = id_dadmin;
        this.date_facturation = date_facturation;
        this.total_facture = total_facture;
        this.etat = etat;
    }

    public Facture() {
    }

    public void setId_facture(int id_facture) {
        this.id_facture = id_facture;
    }

    public void setId_patient(int id_patient) {
        this.id_patient = id_patient;
    }

    public void setId_dadmin(int id_dadmin) {
        this.id_dadmin = id_dadmin;
    }

    public void setDate_facturation(java.sql.Date date_facturation) {
        this.date_facturation = date_facturation;
    }

    public void setTotal_facture(float total_facture) {
        this.total_facture = total_facture;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    public int getId_facture() {
        return id_facture;
    }

    public int getId_patient() {
        return id_patient;
    }

    public int getId_dadmin() {
        return id_dadmin;
    }

    public java.util.Date getDate_facturation() {
        return date_facturation;
    }

    public float getTotal_facture() {
        return total_facture;
    }

    public boolean isEtat() {
        return etat;
    }

}