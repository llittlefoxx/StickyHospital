/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.stickybit.entity;

/**
 *
 * @author Dontey
 */
public class LigneFacture {
    private int id_ligne;
    private int id_facture;
    private int id_medicament;
    private int id_intervention;
    private int quantite;
    private float prix;
    private String lib_ligne;

    public LigneFacture() {
    }

    public LigneFacture(int id_ligne, int id_facture, int id_medicament, int id_intervention, int quantite, float prix, String lib_lignbe) {
        this.id_ligne = id_ligne;
        this.id_facture = id_facture;
        this.id_medicament = id_medicament;
        this.id_intervention = id_intervention;
        this.quantite = quantite;
        this.prix = prix;
        this.lib_ligne = lib_lignbe;
    }

    public LigneFacture(int id_facture, int id_medicament, int id_intervention, int quantite, float prix, String lib_lignbe) {
        this.id_facture = id_facture;
        this.id_medicament = id_medicament;
        this.id_intervention = id_intervention;
        this.quantite = quantite;
        this.prix = prix;
        this.lib_ligne = lib_lignbe;
    }

    public LigneFacture(int id_facture, int id_medicament, int quantite, float prix, String lib_ligne) {
        this.id_facture = id_facture;
        this.id_medicament = id_medicament;
        this.quantite = quantite;
        this.prix = prix;
        this.lib_ligne = lib_ligne;
    }

    public LigneFacture(int id_facture,int quantite,float prix, int id_intervention, String lib_ligne) {
        this.id_facture = id_facture;
        this.id_intervention = id_intervention;
        this.quantite = quantite;
        this.prix = prix;
        this.lib_ligne = lib_ligne;
    }

   
    
    

    public int getId_ligne() {
        return id_ligne;
    }

    public int getId_facture() {
        return id_facture;
    }

    public int getId_medicament() {
        return id_medicament;
    }

    public int getId_intervention() {
        return id_intervention;
    }

    public int getQuantite() {
        return quantite;
    }

    public float getPrix() {
        return prix;
    }

    public String getLib_ligne() {
        return lib_ligne;
    }

    public void setId_ligne(int id_ligne) {
        this.id_ligne = id_ligne;
    }

    public void setId_facture(int id_facture) {
        this.id_facture = id_facture;
    }

    public void setId_medicament(int id_medicament) {
        this.id_medicament = id_medicament;
    }

    public void setId_intervention(int id_intervention) {
        this.id_intervention = id_intervention;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public void setLib_ligne(String lib_ligne) {
        this.lib_ligne = lib_ligne;
    }

   @Override
    public String toString() {
        return "LigneFacture{" + "id_ligne=" + id_ligne + ", id_facture=" + id_facture + ", id_medicament=" + id_medicament + ", id_intervention=" + id_intervention + ", quantite=" + quantite + ", prix=" + prix + ", lib_ligne=" + lib_ligne + '}';
    } 
    
    
    
    
}
