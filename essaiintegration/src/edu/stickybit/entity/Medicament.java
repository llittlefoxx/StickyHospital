package edu.stickybit.entity;


public class Medicament {
    public int code;
    public String libelle;
    public int quantite;
    public String effet ;
    public float prix ;
    public int quantite_min;
    
 public Medicament() {}

    public Medicament(int code, String libelle, int quantite, String effet, float prix, int quantite_min) {
        this.code = code;
        this.libelle = libelle;
        this.quantite = quantite;
        this.effet = effet;
        this.prix = prix;
        this.quantite_min = quantite_min;
    }

    public Medicament(String libelle, int quantite, String effet, float prix, int quantite_min) {
        this.libelle = libelle;
        this.quantite = quantite;
        this.effet = effet;
        this.prix = prix;
        this.quantite_min = quantite_min;
    }

    
      public Medicament(Integer code, String libelle, int quantite,float prix) {
        this.code=code;
        this.libelle = libelle;
        this.quantite = quantite;
         this.prix = prix;
    }

    
    
    
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getEffet() {
        return effet;
    }

    public void setEffet(String effet) {
        this.effet = effet;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getQuantite_min() {
        return quantite_min;
    }

    public void setQuantite_min(int quantite_min) {
        this.quantite_min = quantite_min;
    }

   
    
    
    
    
    
}
