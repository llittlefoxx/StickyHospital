/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stickybit.dao.classes;

import edu.stickybit.entity.Chambre;
import edu.stickybit.entity.Lit;
import edu.stickybit.interfaceDAO.ILit;
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
public class LitDao implements ILit {
    
   private Connection connection;
   
   public LitDao(){
       connection = MyConnectionGroup.getInstance();
   }

    @Override
    public void insertLit(Lit lit) {
        
         String req1="insert into lit (id_serv, id_chambre, etat) values ( ?, ?, ?)";
        try{
            PreparedStatement ps = connection.prepareStatement(req1);
            ps.setInt(1, lit.getIdservice());
            ps.setInt(2, lit.getIdchambre());
            ps.setString(3, lit.getEtat());
            ps.executeUpdate();
            System.out.println("Création établie");
        }catch (SQLException ex) {
            Logger.getLogger(LitDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateLit(int idLit, String etat) {
        Lit litest = new Lit();
         String req1="update lit set etat=? where id_lit=?";
         String req2="Select id_chambre from lit where id_lit=?";
        try{
            PreparedStatement ps = connection.prepareStatement(req1);
            ps.setString(1, etat);
            ps.setInt(2, idLit);
            ps.executeUpdate();
            System.out.println("Mise à jour établie");
            PreparedStatement ps1 = connection.prepareStatement(req2);
            ps1.setInt(1, idLit);
             ResultSet  resultat =  ps1.executeQuery();
            while (resultat.next()) {
                litest.setIdlit(resultat.getInt(1));
            }
            int idChambre = litest.getIdchambre();
            if(etat.equals("Libre")){
                Chambre cham= new Chambre();
                ChambreDao daoCham = new ChambreDao();
                cham = daoCham.findChambrebyId(idChambre);
               int  newcap = cham.getCapacite()+1;
               int idC = cham.getIdchambre();
                daoCham.updateCapaciteChambre(newcap, idC);
            }
            else{
                Chambre cham= new Chambre();
                ChambreDao daoCham = new ChambreDao();
                cham = daoCham.findChambrebyId(idChambre);
               int  newcap = cham.getCapacite()-1;
               int idC = cham.getIdchambre();
                daoCham.updateCapaciteChambre(newcap, idC);
            }
        }catch (SQLException ex) {
            Logger.getLogger(LitDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteLit(int idchambre) {
         String req1="delete from lit where id_chambre=?";
        try{
            PreparedStatement ps = connection.prepareStatement(req1);
            ps.setInt(1,idchambre);
            ps.executeUpdate();
            System.out.println("Suppression établie");
        }catch (SQLException ex) {
            Logger.getLogger(ChambreDao.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }

    @Override
    public Lit FindLitById(int idlit) {
         Lit lit = new Lit();
        String requete = "select * from lit where id_lit=?";
         try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, idlit);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
               lit.setIdservice(resultat.getInt(2));
               lit.setIdchambre(resultat.getInt(3));
               lit.setIdlit(resultat.getInt(1));
               lit.setEtat(resultat.getString(4));
                
            }
            return lit;

        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la recherche du depot " + ex.getMessage());
            return null;
        }
    }
    @Override
    public List<Lit> FindLitByIdChambre(int idChambre) {
        String requete = "select * from lit where  id_chambre=?";
        
                List<Lit> listeLits = new ArrayList<Lit>();

        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, idChambre);
            ResultSet resultat = ps.executeQuery();

            while (resultat.next()) {
                Lit lit = new Lit();
                lit.setIdlit(resultat.getInt(1));
                lit.setIdservice(resultat.getInt(2));
                lit.setIdchambre(resultat.getInt(3));
                lit.setEtat(resultat.getString(4));
                System.err.println(lit.toString());
                listeLits.add(lit);
            }
            return listeLits;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des lits " + ex.getMessage());
            return null;
        }
    }
    @Override
    public List<Lit> DisplatAllLit() {
        String requete = "select * from lit";
        
                List<Lit> listeLits = new ArrayList<Lit>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Lit lit = new Lit();
                lit.setIdservice(resultat.getInt(2));
                lit.setIdchambre(resultat.getInt(3));
                lit.setIdlit(resultat.getInt(1));
                lit.setEtat(resultat.getString(4));
                System.err.println(lit.toString());
                listeLits.add(lit);
            }
            return listeLits;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des lits " + ex.getMessage());
            return null;
        }
 
    }
    
    @Override
    public List<String> DisplayAllEtat() {
        String requete = "select distinct  etat  from lit ";
         List<String> listeEtatLit = new ArrayList<String>();
         try {
             PreparedStatement ps = connection.prepareStatement(requete);
             
             ResultSet resultat = ps.executeQuery();

            while (resultat.next()) {
                 
                listeEtatLit .add(resultat.getString(1));
            }
            return  listeEtatLit;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des chambres " + ex.getMessage());
            return null;
    }
    }
     @Override
    public List<Lit> DisplayAllLitDisponible(int idChambre) {
        String requete = "select * from lit where id_chambre=? and etat=?";
        
                List<Lit> listeLits = new ArrayList<Lit>();

        try {
             PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, idChambre);
            ps.setString(2, "Libre");
            ResultSet resultat = ps.executeQuery();

            while (resultat.next()) {
                Lit lit = new Lit();
                lit.setIdlit(resultat.getInt(1));
                lit.setIdservice(resultat.getInt(2));
                lit.setIdchambre(resultat.getInt(3));
                lit.setEtat(resultat.getString(4));
                System.err.println(lit.toString());
                listeLits.add(lit);
            }
            return listeLits;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des lits " + ex.getMessage());
            return null;
        }
    }
    @Override
    public List<Lit> DisplayAllLitByRoom(int idChambre) {
        String requete = "select * from lit where id_chambre=?";
        
                List<Lit> listeLits = new ArrayList<Lit>();

        try {
             PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, idChambre);
            ResultSet resultat = ps.executeQuery();

            while (resultat.next()) {
                Lit lit = new Lit();
                lit.setIdlit(resultat.getInt(1));
                lit.setIdservice(resultat.getInt(2));
                lit.setIdchambre(resultat.getInt(3));
                lit.setEtat(resultat.getString(4));
                System.err.println(lit.toString());
                listeLits.add(lit);
            }
            return listeLits;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des lits " + ex.getMessage());
            return null;
        }
    }
    private static ILit iLit;

    public static ILit getInstance() {
        if (iLit == null) {
            iLit= new LitDao();
        }
        return iLit;
    }

     @Override
    public List<Lit> DisplatAllLitbyetat() {
      String requete = "select * from lit where etat='Occupé' ";
        
                List<Lit> listeLits = new ArrayList<Lit>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                Lit lit = new Lit();
                lit.setIdservice(resultat.getInt(2));
                lit.setIdchambre(resultat.getInt(3));
                lit.setIdlit(resultat.getInt(1));
                lit.setEtat(resultat.getString(4));
                listeLits.add(lit);
            }
            return listeLits;
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des lits " + ex.getMessage());
            return null;
        }
    }

   
}

