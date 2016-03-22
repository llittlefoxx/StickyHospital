package edu.stickybit.entity;

import java.util.Objects;

 /* 
 * @author Manel Rhaiem <manel.rhaiemat esprit.tn>
 */
public class Services {
    
    private int idService;
    private String nomService;

    
    public Services(){
        
    }
      public Services( int idService, String nomService){
        this.idService=idService;
        this.nomService=nomService;
    }
       public Services( String nomService){
        this.nomService=nomService;
    }
    public int getIdService() {
        return idService;
    }

    public String getNomService() {
        return nomService;
    }

    public void setIdService(int idService) {
        this.idService = idService;
    }

    public void setNomService(String nomService) {
        this.nomService = nomService;
    }

    @Override
    public String toString() {
        return "Services{" + "idService=" + idService + ", nomService=" + nomService + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash= 98 * hash + Objects.hashCode(this.idService);
        hash = 98 * hash + Objects.hashCode(this.nomService);
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
        final Services other = (Services) obj;
        if(Objects.equals(other.idService, this.idService)){
            return false;
        }
        if(Objects.equals(this.nomService, other.nomService)){
            return false;
        }
        return true;
    }
    
    

}
