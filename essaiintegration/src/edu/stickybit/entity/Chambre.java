package edu.stickybit.entity;


public class Chambre {
    private int idservice;
    private int idchambre;
    private int capacite;
    private String type;
    private String etat;

     public Chambre(){
        
    }
    public Chambre( int idchambre,int idservice,  int capacite, String type, String etat){
        this.idservice= idservice;
        this.idchambre = idchambre;
        this.capacite = capacite;
        this.type = type;
        this.etat = etat;
    }
      public Chambre( int idservice, int capacite, String type, String etat){
        this.idservice= idservice;
        this.capacite = capacite;
        this.type = type;
        this.etat = etat;
    }
    
    
    public int getIdservice() {
        return idservice;
    }

    public int getIdchambre() {
        return idchambre;
    }

    public int getCapacite() {
        return capacite;
    }

    public String getType() {
        return type;
    }

    public String getEtat() {
        return etat;
    }


    public void setIdservice(int idservice) {
        this.idservice = idservice;
    }

    public void setIdchambre(int idchambre) {
        this.idchambre = idchambre;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Chambre{" + "idservice=" + idservice + ", idchambre=" + idchambre + ", capacite=" + capacite + ", type=" + type + ", etat=" + etat + '}';
    }
    
    
}
