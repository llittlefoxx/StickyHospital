
package edu.stickybit.entity;

public class Lit {
    private int idservice;
    private int idchambre;
    private int idlit;
    private int idhost;
    private String etat;

   public Lit(){
       
   }
public Lit( int idservice, int idchambre,int idlit, String etat){
       this.idservice=idservice;
       this.idchambre=idchambre;
       this.idlit=idlit;
       this.etat=etat;
   }
public Lit(int idservice, int idchambre,  String etat){
       this.idservice=idservice;
       this.idchambre=idchambre;
       this.etat=etat;
   }
    public int getIdservice() {
        return idservice;
    }

    public int getIdchambre() {
        return idchambre;
    }

    public int getIdlit() {
        return idlit;
    }

    public int getIdhost() {
        return idhost;
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


    public void setIdlit(int idlit) {
        this.idlit = idlit;
    }

    public void setIdhost(int idhost) {
        this.idhost = idhost;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Lit{" + "idservice=" + idservice + ", idchambre=" + idchambre + ", idlit=" + idlit + ", idhost=" + idhost + ", etat=" + etat + '}';
    }

}
