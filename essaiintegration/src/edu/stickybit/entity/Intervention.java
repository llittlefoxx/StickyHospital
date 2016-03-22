/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.stickybit.entity;

import java.sql.Date;

/**
 * 
 * @author Dontey
 */
public class Intervention {
    private int id_intervention;
    private String lib_intervention;
    private Float prix_interv;
    private java.sql.Date date_interv;

    public Intervention(int id_intervention, String lib_intervention, Float prix_interv, Date date_interv) {
        this.id_intervention = id_intervention;
        this.lib_intervention = lib_intervention;
        this.prix_interv = prix_interv;
        this.date_interv = date_interv;
    }

    public Intervention() {
    }

    public void setId_intervention(int id_intervention) {
        this.id_intervention = id_intervention;
    }

    public void setLib_intervention(String lib_intervention) {
        this.lib_intervention = lib_intervention;
    }

    public void setPrix_interv(Float prix_interv) {
        this.prix_interv = prix_interv;
    }

    public void setDate_interv(Date date_interv) {
        this.date_interv = date_interv;
    }

    public int getId_intervention() {
        return id_intervention;
    }

    public String getLib_intervention() {
        return lib_intervention;
    }

    public Float getPrix_interv() {
        return prix_interv;
    }

    public Date getDate_interv() {
        return date_interv;
    }
    
    
    

}
