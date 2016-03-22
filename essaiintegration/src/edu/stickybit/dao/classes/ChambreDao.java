/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stickybit.dao.classes;

import edu.stickybit.entity.Chambre;
import edu.stickybit.entity.Lit;
import edu.stickybit.interfaceDAO.IChambre;
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
 * @author mermi
 */
public class ChambreDao implements IChambre {

    private Connection connection;

    public ChambreDao() {
        connection = MyConnectionGroup.getInstance();
    }

    @Override
    public void insertChambre(Chambre chambre) {
       String req1="insert  into  chambre  (id_serv, capacite, type,etat)  values  (?, ?, ?,?)";
        String requete2="select last_insert_id() as id_chambre from chambre";
        try{
            PreparedStatement ps = connection.prepareStatement(req1);
            PreparedStatement ps1 = connection.prepareStatement(requete2);
            ps.setInt(1, chambre.getIdservice());
            ps.setInt(2, chambre.getCapacite());
            ps.setString(3, chambre.getType());
            ps.setString(4, chambre.getEtat());
            ps.executeUpdate();
            System.out.println("création d'une chambre établie");
            ResultSet rs = ps1.executeQuery();
            int lastid=0;
           if(rs.next()){           
                lastid = rs.getInt(1);
          }
            for (int i = 0; i < chambre.getCapacite(); i++) {
                
                LitDao lit = new LitDao();
                Lit litnew = new Lit(chambre.getIdservice() ,lastid,chambre.getEtat());
                lit.insertLit(litnew);
            }
        }catch (SQLException ex) {
            Logger.getLogger(ChambreDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateChambre(Chambre chambre) {
       String req1="update chambre set id_serv=?, capacite=?, type=?, etat=?  where  id_chambre=?";
        try{
            PreparedStatement ps = connection.prepareStatement(req1);
            ps.setInt(1, chambre.getIdservice());
            ps.setInt(2, chambre.getCapacite());
            ps.setString(3, chambre.getType());
            ps.setString(4, chambre.getEtat());
            ps.setInt(5, chambre.getIdchambre());
            ps.executeUpdate();
            System.out.println("Mise à jour établie");
        }catch (SQLException ex) {
            Logger.getLogger(ChambreDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateCapaciteChambre(int capacite, int idchambre) {
       String req1="update chambre set  capacite=?  where  id_chambre=?";
        try{
            PreparedStatement ps = connection.prepareStatement(req1);
            ps.setInt(1, capacite);
            ps.setInt(2, idchambre);
            ps.executeUpdate();
            System.out.println("Mise à jour établie");
        }catch (SQLException ex) {
            Logger.getLogger(ChambreDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteChambre(int idChambre) {
     String req1="delete from chambre where id_chambre=?";
        
        try{
            LitDao lit = new LitDao();
            lit.deleteLit(idChambre);
            PreparedStatement ps = connection.prepareStatement(req1);
            ps.setInt(1,idChambre);
            ps.executeUpdate();
            System.out.println("Suppression établie");
        }catch (SQLException ex) {
            Logger.getLogger(ChambreDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Chambre findChambrebyId(int idChambre) {
          Chambre chambre = new Chambre();
        String requete = "select * from chambre where id_chambre=?";
         try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, idChambre);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
               chambre.setIdchambre(resultat.getInt(1));
               chambre.setIdservice(resultat.getInt(2));
               chambre.setCapacite(resultat.getInt(3));
               chambre.setType(resultat.getString(4));
               chambre.setEtat(resultat.getString(5));
                
            }
            return chambre;

        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la recherche du depot " + ex.getMessage());
            return null;
        }
    }

    @Override
    public int findChambreByEtat(int etatChambre) {
         Chambre chambre = new Chambre();
        String requete = "select id_chambre from chambre where etat=?";
         try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, etatChambre);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
               chambre.setIdchambre(resultat.getInt(1));
                
            }
            return chambre.getIdchambre();

        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la recherche du chambre" + ex.getMessage());
            return 0;
        }
    }

    @Override
    public List<Chambre> DisplayAllChambre() {
       List<Chambre> listeChambres = new ArrayList<Chambre>();

        String requete = "select * from chambre";
        try {
            Statement statement = connection .createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Chambre chambre = new Chambre();
                chambre.setIdchambre(resultat.getInt(1));
                chambre.setIdservice(resultat.getInt(2));
                chambre.setCapacite(resultat.getInt(3));
                chambre.setType(resultat.getString(4));
                chambre.setEtat(resultat.getString(5));
                System.err.println(chambre.toString());
                listeChambres.add(chambre);
            }
            return listeChambres;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des chambres " + ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Chambre> findOccupedChambreByService(int idService) {
        String requete = "select capacite, type, etat  from chambre where id_serv=? and etat=?";
        List<Chambre> listeChambres = new ArrayList<Chambre>();
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, "%" + idService + "%");
            ps.setString(2, "Occupé");
            ResultSet resultat = ps.executeQuery();

            while (resultat.next()) {
                Chambre chambre = new Chambre();
                chambre.setIdservice(resultat.getInt(1));
                chambre.setIdchambre(resultat.getInt(2));
                chambre.setCapacite(resultat.getInt(3));
                chambre.setType(resultat.getString(4));
                chambre.setEtat(resultat.getString(5));
                System.err.println(chambre.toString());
                listeChambres.add(chambre);
            }
            return listeChambres;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des chambres " + ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Chambre> DisplayAllChambreByService(int idService) {
        String requete = "select  id_chambre, id_serv, capacite, type, etat  from chambre  where id_serv=? ";
        List<Chambre> listeChambres = new ArrayList<Chambre>();
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, idService);
            ResultSet resultat = ps.executeQuery();

            while (resultat.next()) {
                Chambre chambre = new Chambre();
                chambre.setIdchambre(resultat.getInt(1));
                chambre.setIdservice(resultat.getInt(2));
                chambre.setCapacite(resultat.getInt(3));
                chambre.setType(resultat.getString(4));
                chambre.setEtat(resultat.getString(5));
                System.err.println(chambre.toString());
                listeChambres.add(chambre);
            }
            return listeChambres;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des chambres " + ex.getMessage());
            return null;
        }
    }

    @Override
    public int findCapaciteByIDChambre(int idChambre) {
        Chambre chambre = new Chambre();
        String requete = "select capacite from chambre where id_chambre=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, idChambre);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                chambre.setCapacite(resultat.getInt(1));

            }
            return chambre.getCapacite();

        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la recherche du chambre" + ex.getMessage());
            return 0;
        }
    }

    @Override
    public List<String> DisplayIdChambreByService(int idService) {

        String requete = "select  id_chambre  from chambre  where id_serv=? ";
        List<String> listeIDChambres = new ArrayList<String>();
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, idService);
            ResultSet resultat = ps.executeQuery();

            while (resultat.next()) {

                listeIDChambres.add(resultat.getInt(1) + "");
            }
            return listeIDChambres;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des chambres " + ex.getMessage());
            return null;
        }
    }

    @Override
    public List<String> DisplayAllEtat() {
        String requete = "select distinct  etat  from chambre ";
        List<String> listeEtatChambres = new ArrayList<String>();
        try {
            PreparedStatement ps = connection.prepareStatement(requete);

            ResultSet resultat = ps.executeQuery();

            while (resultat.next()) {

                listeEtatChambres.add(resultat.getString(1));
            }
            return listeEtatChambres;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des chambres " + ex.getMessage());
            return null;
        }
    }
    private static IChambre iChambre;

    public static IChambre getInstance() {
        if (iChambre == null) {
            iChambre = new ChambreDao();
        }
        return iChambre;
    }

    @Override
    public List<Chambre> findAvailableChambreByService(int idService) {
     String requete = "select *  from chambre where id_serv=? and etat=?";
         List<Chambre> listeChambres = new ArrayList<Chambre>();
         try {
             PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1,  idService );
            ps.setString(2,  "Libre" );
            ResultSet resultat = ps.executeQuery();

            while (resultat.next()) {
                Chambre chambre = new Chambre();
                chambre.setIdchambre(resultat.getInt(1));
                chambre.setIdservice(resultat.getInt(2));
                chambre.setCapacite(resultat.getInt(3));
                chambre.setType(resultat.getString(4));
                chambre.setEtat(resultat.getString(5));
                System.err.println(chambre.toString());
                listeChambres.add(chambre);
            }
            return listeChambres;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des chambres " + ex.getMessage());
            return null;
        }
    }
}
