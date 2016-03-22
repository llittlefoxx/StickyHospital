package edu.stickybit.dao.classes;

import edu.stickybit.dao.classes.ServicesDao;
import edu.stickybit.entity.Services;
import edu.stickybit.interfaceDAO.IServices;
import edu.stickybit.technique.MyConnectionGroup;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Manel Rhaiem <manel.rhaiemat esprit.tn>
 */
public class ServicesDao implements IServices {

    private Connection connection;

    public ServicesDao() {
        connection = MyConnectionGroup.getInstance();
    }

    @Override
    public void insertService(Services service) {
       List<Services> listerv= new ArrayList<>();
        listerv =DisplayAllServices();
       int  i=0;
        for(Services  service1: listerv){
            if(service.equals(service1)){
                i++;
                System.err.println("Service existant "+i);
            }
        }
        if(i==0){
            String req1 = "insert into service ( nom_serv) values ( ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(req1);
            ps.setString(1, service.getNomService());
            ps.executeUpdate();
            System.out.println("service ajouté");
        } catch (SQLException ex) {
            Logger.getLogger(LitDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
    }

    @Override
    public void updateService(Services service) {
     String req1 = "update  service  set  nom_serv=? where  id_serv =? ";
        try {
            PreparedStatement ps = connection.prepareStatement(req1);
            ps.setString(1, service.getNomService());
            ps.setInt(2, service.getIdService());
            ps.executeUpdate();
            System.out.println("Mise à jour du service  établie");
        } catch (SQLException ex) {
            Logger.getLogger(LitDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteService(int idService) {
        String req1 = "delete from service where id_serv=?";
        try {
            PreparedStatement ps = connection.prepareStatement(req1);
            ps.setInt(1, idService);
            ps.executeUpdate();
            System.out.println("suppression du service établie");
        } catch (SQLException ex) {
            Logger.getLogger(ChambreDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public Services findById(int idService) {
         Services serv = new Services();
        String requete = "select  *  from service  where id_serv=?";
        
            PreparedStatement ps;
        try {
            ps = connection.prepareStatement(requete);
            ps.setInt(1, idService);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                serv.setIdService(resultat.getInt(1));
                serv.setNomService(resultat.getString(2));
            }
            return serv;
        } catch (SQLException ex) {
            Logger.getLogger(ServicesDao.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return null;
    }

    @Override
    public int findServiceByName(String nomService) {
         Services serv = new Services();
        String requete = "select  id_serv  from service  where nom_serv=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, nomService);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                serv.setIdService(resultat.getInt(1));
            }
            return serv.getIdService();
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la recherche du Service " + ex.getMessage());
            return 0;
        }
    }

    @Override
    public List<Services> DisplayAllServices() {
            List<Services> listeServices = new ArrayList<Services>();

        String requete = "select * from service";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Services  service = new Services();
                service.setIdService(resultat.getInt(1));
                service.setNomService(resultat.getString(2));
                System.err.println(service.toString());
                listeServices.add(service);
            }
            return listeServices;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des chambres " + ex.getMessage());
            return null;
        }
    }
     @Override
    public List<String> DisplayAllServicesName() {
                   List<String> listeServices = new ArrayList<String>();

        String requete = "select nom_serv from service";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
               String  name = resultat.getString("nom_serv");
                System.err.println(name.toString());
                listeServices.add(name.toString());
            }
            return listeServices;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des chambres " + ex.getMessage());
            return null;
        }
    }
    private static IServices iServices;

    public static IServices getInstance() {
        if (iServices == null) {
            iServices = new ServicesDao();
        }
        return iServices;
    }

}
