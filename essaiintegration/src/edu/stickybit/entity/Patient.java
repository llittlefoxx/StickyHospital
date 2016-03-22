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
public class Patient {
 
    private int id_patient;
    private String nom_patient;
    private String prenom_patient;
    private int cin_patient;
    private String adresse;
    private String tel_patient;
    private String mail_patient;
    private String url_photo_patient;
    private String sex_patient;
    private Date date_n_patient;
    private Date date_d_patient;

    public Patient() {
    }

    
    public Patient(int id_patient,String nom_patient, String prenom_patient, int cin_patient, String adresse, String tel_patient, String mail_patient, String url_photo_patient, String sex_patient, Date date_n_patient) {
        this.id_patient = id_patient;
        this.nom_patient = nom_patient;
        this.prenom_patient = prenom_patient;
        this.cin_patient = cin_patient;
        this.adresse = adresse;
        this.tel_patient = tel_patient;
        this.mail_patient = mail_patient;
        this.url_photo_patient = url_photo_patient;
        this.sex_patient = sex_patient;
        this.date_n_patient = date_n_patient;
    }
    
    public Patient(String nom_patient, String prenom_patient, int cin_patient, String adresse, String tel_patient, String mail_patient, String url_photo_patient, String sex_patient, Date date_n_patient) {
        this.nom_patient = nom_patient;
        this.prenom_patient = prenom_patient;
        this.cin_patient = cin_patient;
        this.adresse = adresse;
        this.tel_patient = tel_patient;
        this.mail_patient = mail_patient;
        this.url_photo_patient = url_photo_patient;
        this.sex_patient = sex_patient;
        this.date_n_patient = date_n_patient;
    }
    
     public Patient(String nom_patient, String prenom_patient, int cin_patient, String adresse, String tel_patient, String mail_patient, String url_photo_patient, String sex_patient) {
      
        this.nom_patient = nom_patient;
        this.prenom_patient = prenom_patient;
        this.cin_patient = cin_patient;
        this.adresse = adresse;
        this.tel_patient = tel_patient;
        this.mail_patient = mail_patient;
        this.url_photo_patient = url_photo_patient;
        this.sex_patient = sex_patient;
     
    }
     
     
        public Patient(int id_patient,String nom_patient, String prenom_patient, int cin_patient, String adresse, String tel_patient, String mail_patient) {
      
        this.id_patient = id_patient;    
        this.nom_patient = nom_patient;
        this.prenom_patient = prenom_patient;
        this.cin_patient = cin_patient;
        this.adresse = adresse;
        this.tel_patient = tel_patient;
        this.mail_patient=mail_patient;
            
    }
     
      public Patient(int id_patient, String nom_patient, String prenom_patient, int cin_patient, String adresse, String tel_patient, String mail_patient, String url_photo_patient, String sex_patient) {
      
        this.id_patient=id_patient;
        this.nom_patient = nom_patient;
        this.prenom_patient = prenom_patient;
        this.cin_patient = cin_patient;
        this.adresse = adresse;
        this.tel_patient = tel_patient;
        this.mail_patient = mail_patient;
        this.url_photo_patient = url_photo_patient;
        this.sex_patient = sex_patient;
     
    }
    
  

    public int getId_patient() {
        return id_patient;
    }

    public void setId_patient(int id_patient) {
        this.id_patient = id_patient;
    }

    public String getNom_patient() {
        return nom_patient;
    }

    public void setNom_patient(String nom_patient) {
        this.nom_patient = nom_patient;
    }

    public String getPrenom_patient() {
        return prenom_patient;
    }

    public void setPrenom_patient(String prenom_patient) {
        this.prenom_patient = prenom_patient;
    }

    public int getCin_patient() {
        return cin_patient;
    }

    public void setCin_patient(int cin_patient) {
        this.cin_patient = cin_patient;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTel_patient() {
        return tel_patient;
    }

    public void setTel_patient(String tel_patient) {
        this.tel_patient = tel_patient;
    }

    public String getMail_patient() {
        return mail_patient;
    }

    public void setMail_patient(String mail_patient) {
        this.mail_patient = mail_patient;
    }

    public String getUrl_photo_patient() {
        return url_photo_patient;
    }

    public void setUrl_photo_patient(String url_photo_patient) {
        this.url_photo_patient = url_photo_patient;
    }

    public String getSex_patient() {
        return sex_patient;
    }

    public void setSex_patient(String sex_patient) {
        this.sex_patient = sex_patient;
    }

    public Date getDate_n_patient() {
        return date_n_patient;
    }

    public void setDate_n_patient(Date date_n_patient) {
        this.date_n_patient = date_n_patient;
    }

    public Date getDate_d_patient() {
        return date_d_patient;
    }

    public void setDate_d_patient(Date date_d_patient) {
        this.date_d_patient = date_d_patient;
    }

   
    public String toString() {
        return "Patient{" + "id_patient=" + id_patient + ", nom_patient=" + nom_patient + ", prenom_patient=" + prenom_patient + ", cin_patient=" + cin_patient + ", adresse=" + adresse + ", tel_patient=" + tel_patient + ", mail_patient=" + mail_patient + ", url_photo_patient=" + url_photo_patient + ", sex_patient=" + sex_patient + ", date_n_patient=" + date_n_patient+ '}';
    }
    
    
}
