/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stickybit.entity;

import java.util.Objects;

/**
 *
 * @author Tlili Mohamed Ali
 */
public class Medecin {

    private int id_medecin;

    private String nom_medecin;

    private String prenom_medecin;

    private String adresse_medecin;

    private String mail_medecin;

    private String tel_medecin;

    private String specialite_medecin;
    
    private int id_service;

    private String cin_medecin;

    public Medecin(int id_medecin, String nom_medecin, String prenom_medecin, String adresse_medecin, String mail_medecin, String tel_medecin, String specialite_medecin,int id_service, String cin_medecin) {
        this.id_medecin = id_medecin;
        this.nom_medecin = nom_medecin;
        this.prenom_medecin = prenom_medecin;
        this.adresse_medecin = adresse_medecin;
        this.mail_medecin = mail_medecin;
        this.tel_medecin = tel_medecin;
        this.specialite_medecin = specialite_medecin;
        this.id_service=id_service;
        this.cin_medecin = cin_medecin;
    }

    public Medecin(String nom_medecin, String prenom_medecin, String adresse_medecin, String mail_medecin, String tel_medecin, String specialite_medecin,int id_service, String cin_medecin) {
        this.nom_medecin = nom_medecin;
        this.prenom_medecin = prenom_medecin;
        this.adresse_medecin = adresse_medecin;
        this.mail_medecin = mail_medecin;
        this.tel_medecin = tel_medecin;
        this.specialite_medecin = specialite_medecin;
        this.id_service=id_service;
        this.cin_medecin = cin_medecin;
    }

    public Medecin() {
    }

    public int getId_medecin() {
        return id_medecin;
    }

    public void setId_medecin(int id_medecin) {
        this.id_medecin = id_medecin;
    }

    public String getNom_medecin() {
        return nom_medecin;
    }

    public void setNom_medecin(String nom_medecin) {
        this.nom_medecin = nom_medecin;
    }

    public String getPrenom_medecin() {
        return prenom_medecin;
    }

    public void setPrenom_medecin(String prenom_medecin) {
        this.prenom_medecin = prenom_medecin;
    }

    public String getAdresse_medecin() {
        return adresse_medecin;
    }

    public void setAdresse_medecin(String adresse_medecin) {
        this.adresse_medecin = adresse_medecin;
    }

    public String getMail_medecin() {
        return mail_medecin;
    }

    public void setMail_medecin(String mail_medecin) {
        this.mail_medecin = mail_medecin;
    }

    public String getTel_medecin() {
        return tel_medecin;
    }

    public void setTel_medecin(String tel_medecin) {
        this.tel_medecin = tel_medecin;
    }

    public String getSpecialite_medecin() {
        return specialite_medecin;
    }

    public int getId_service() {
        return id_service;
    }

    public void setId_service(int id_service) {
        this.id_service = id_service;
    }
    

    public void setSpecialite_medecin(String specialite_medecin) {
        this.specialite_medecin = specialite_medecin;
    }

    public String getCin_medecin() {
        return cin_medecin;
    }

    public void setCin_medecin(String cin_medecin) {
        this.cin_medecin = cin_medecin;
    }

    @Override
    public String toString() {
        return "Medecin{" + "id_medecin=" + id_medecin + ", nom_medecin=" + nom_medecin + ", prenom_medecin=" + prenom_medecin + ", adresse_medecin=" + adresse_medecin + ", mail_medecin=" + mail_medecin + ", tel_medecin=" + tel_medecin + ", specialite_medecin=" + specialite_medecin + ", cin_medecin=" + cin_medecin + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.nom_medecin);
        hash = 97 * hash + Objects.hashCode(this.prenom_medecin);
        hash = 97 * hash + Objects.hashCode(this.adresse_medecin);
        hash = 97 * hash + Objects.hashCode(this.mail_medecin);
        hash = 97 * hash + Objects.hashCode(this.tel_medecin);
        hash = 97 * hash + Objects.hashCode(this.specialite_medecin);
        hash = 97 * hash + Objects.hashCode(this.cin_medecin);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Medecin other = (Medecin) obj;
        if (!Objects.equals(this.nom_medecin, other.nom_medecin)) {
            return false;
        }
        if (!Objects.equals(this.prenom_medecin, other.prenom_medecin)) {
            return false;
        }
        if (!Objects.equals(this.adresse_medecin, other.adresse_medecin)) {
            return false;
        }
        if (!Objects.equals(this.mail_medecin, other.mail_medecin)) {
            return false;
        }
        if (!Objects.equals(this.tel_medecin, other.tel_medecin)) {
            return false;
        }
        if (!Objects.equals(this.specialite_medecin, other.specialite_medecin)) {
            return false;
        }
        if (!Objects.equals(this.cin_medecin, other.cin_medecin)) {
            return false;
        }
        return true;
    }

}
