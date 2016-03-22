 package edu.stickybit.interfaceDAO;
import edu.stickybit.entity.Services;
import java.util.List;

/**
 * 
 * @author Manel Rhaiem <manel.rhaiemat esprit.tn>
 */


public interface IServices {
    
    void insertService (Services service);
    void updateService(Services service);
    void deleteService(int idService);
    Services findById(int idService);
    int findServiceByName(String nomService);
    List<Services> DisplayAllServices();
   List<String> DisplayAllServicesName();
}
