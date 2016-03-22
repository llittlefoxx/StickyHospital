/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stickybit.entity;

import java.util.Date;

/**
 *
 * @author Tlili Mohamed Ali
 */
public class Hospitalisation {

    private int id_hos;
    private int id_patient;
    private String adm_login;
    private int id_lit;
    private String date_h;
    private String date_s;
    private int id_med;
    private String type_admisssion;
    private String etat_patient;

        
/*
    public Hospitalisation(int id_hos, int id_patient, String adm_login, Date date_h, Date date_s, int id_med, String type_admisssion, String etat_patient) {
    this.id_hos = id_hos;
    this.id_patient = id_patient;
    this.adm_login = adm_login;
    this.date_h = date_h;
    this.date_s = date_s;
    this.id_med = id_med;
    this.type_admisssion = type_admisssion;
    this.etat_patient = etat_patient;
    }*/
    public Hospitalisation() {
    }

    public Hospitalisation(int id_hos, int id_patient, String adm_login,int id_lit, int id_med, String type_admisssion, String etat_patient) {
        this.id_hos = id_hos;
        this.id_patient = id_patient;
        this.adm_login = adm_login;
        this.id_lit = id_lit;
        this.id_med = id_med;
        this.type_admisssion = type_admisssion;
        this.etat_patient = etat_patient;
    }

    public Hospitalisation(int id_patient, String adm_login,int id_lit ,String date_h, int id_med, String type_admisssion, String etat_patient) {
        this.id_patient = id_patient;
        this.adm_login = adm_login;
        this.id_lit = id_lit;
        this.date_h = date_h;
        this.id_med = id_med;
        this.type_admisssion = type_admisssion;
        this.etat_patient = etat_patient;
    }

    @Override
    public String toString() {
        return "Hospitalisation{" + "id_hos=" + id_hos + ", id_patient=" + id_patient + ", adm_login=" + adm_login + ", date_h=" + date_h + ", date_s=" + date_s + ", id_med=" + id_med + ", type_admisssion=" + type_admisssion + ", etat_patient=" + etat_patient + '}';
    }

    public int getId_lit() {
        return id_lit;
    }

    public void setId_lit(int id_lit) {
        this.id_lit = id_lit;
    }

    public int getId_hos() {
        return id_hos;
    }

    public void setId_hos(int id_hos) {
        this.id_hos = id_hos;
    }

    public int getId_patient() {
        return id_patient;
    }

    public void setId_patient(int id_patient) {
        this.id_patient = id_patient;
    }

    public String getAdm_login() {
        return adm_login;
    }

    public void setAdm_login(String adm_login) {
        this.adm_login = adm_login;
    }

    public String getDate_h() {
        return date_h;
    }

    public void setDate_h(String date_h) {
        this.date_h = date_h;
    }

    
    
    
    public String getDate_s() {
        return date_s;
    }

    public void setDate_s(String date_s) {
        this.date_s = date_s;
    }

    public int getId_med() {
        return id_med;
    }

    public void setId_med(int id_med) {
        this.id_med = id_med;
    }

    public String getType_admisssion() {
        return type_admisssion;
    }

    public void setType_admisssion(String type_admisssion) {
        this.type_admisssion = type_admisssion;
    }

    public String getEtat_patient() {
        return etat_patient;
    }

    public void setEtat_patient(String etat_patient) {
        this.etat_patient = etat_patient;
    }
    
    
    
}
