/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stickybit.dao.classes;

import edu.stickybit.entity.Medicament;
import edu.stickybit.interfaceDAO.InterfaceMedicaments;
import edu.stickybit.technique.MyConnectionGroup;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ClinicPc
 */
public class MedicamentDAO  implements InterfaceMedicaments{
      
    public   List<Medicament> listemed = new ArrayList<Medicament>();

   private Connection connection;
   public MedicamentDAO() {
    connection = MyConnectionGroup.getInstance();
    }

    @Override
    public void insertmed(Medicament med){        
        List<Medicament> lmr =new ArrayList<Medicament>();
        lmr=DisplayAllmed();
        int i=0;
        for (Medicament medicamnt : lmr) {
            if(med.getLibelle().equals(medicamnt.getLibelle()))
            {
                i++;
                System.err.println("Medicament deja existant" +i);
            }
        }
        if (i==0){
    String requete = "insert into medicament (lib_medic,qte_valable,effect_ind,prix,quant_min) values (?,?,?,?,?)";
          try{
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, med.getLibelle());
            ps.setInt(2, med.getQuantite());
            ps.setString(3, med.getEffet());
            ps.setFloat(4, med.getPrix());
            ps.setInt(5, med.getQuantite_min());
            ps.executeUpdate();
             System.out.println ("insertion avec succes");
          
        } catch (SQLException ex) {
            Logger.getLogger(MedicamentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }}
    }

    @Override
    public void updatemed(Medicament med) {
       String requete = "update  medicament set lib_medic=? ,qte_valable=? , effect_ind=? , prix=? ,quant_min=?  where id_medicament=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, med.getLibelle());
            ps.setInt(2, med.getQuantite());
            ps.setString(3, med.getEffet());
            ps.setFloat(4, med.getPrix());
             ps.setInt(5, med.getQuantite_min());
            ps.setInt(6, med.getCode());
            ps.executeUpdate();
            System.out.println("Mise à jour effectuée avec succès");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la mise à jour " + ex.getMessage());
        }
    }

    @Override
    public void deletemed(int code) {
       String requete = "delete from medicament where id_medicament=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1,code);
            ps.executeUpdate();
            System.out.println("medicament  supprimée");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }

    
        @Override
    public void deletemedlib(String lib) {
       String requete = "delete from medicament where lib_medic=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1,lib);
            ps.executeUpdate();
            System.out.println("medicament  supprimée");
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }


    
    
    
    @Override
    public Medicament findmedById(int code) {
        Medicament med = new Medicament();
        String requete = "select * from medicament where id_medicament=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setInt(1, code);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
               med.setCode(resultat.getInt(1));
               med.setLibelle(resultat.getString(2));
               med.setQuantite(resultat.getInt(3));
               med.setEffet(resultat.getString(4));
               med.setPrix(resultat.getInt(5));
               med.setQuantite_min(resultat.getInt(6));
            }
       System.out.println("on a trouver " +med.getLibelle());

            return med;
          
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la recherche du medicamnt " + ex.getMessage());
            return null;
        }
    }

    @Override
    public Medicament findmedBylibelle(String lib) {
         Medicament med = new Medicament();
        String requete = "select * from medicament where lib_medic=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1,lib);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
               med.setCode(resultat.getInt(1));
               med.setLibelle(resultat.getString(2));
               med.setQuantite(resultat.getInt(3));
               med.setEffet(resultat.getString(4));
               med.setPrix(resultat.getInt(5));
               med.setQuantite_min(resultat.getInt(6));
            }
       System.out.println("on a trouver " +med.getPrix());

            return med;
          
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la recherche du medicamnt " + ex.getMessage());
            return null;
        }
    }

    
    @Override
    public List<Medicament> DisplayAllmed() {
        String requete = "select * from medicament";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(requete);
            while (resultat.next()) {
                Medicament med = new Medicament();
                med.setCode(resultat.getInt(1));
                med.setLibelle(resultat.getString(2));
                 med.setQuantite(resultat.getInt(3));
                 med.setEffet(resultat.getString(4));
                 med.setPrix(resultat.getInt(5));
                  med.setQuantite_min(resultat.getInt(6));
                listemed.add(med);
            }
           return listemed;
           
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des depots " + ex.getMessage());
            return null;
        } 
    }

    @Override
    public List<Medicament> Displaystock() {
      List<Medicament> listemeds = new ArrayList<Medicament>();
        String requete = "select * from medicament where ( qte_valable < quant_min ) ";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultat = statement.executeQuery(requete);
            while (resultat.next()) {
                Medicament med = new Medicament();
                med.setCode(resultat.getInt(1));
                med.setLibelle(resultat.getString(2));
                med.setQuantite(resultat.getInt(3));
                med.setEffet(resultat.getString(4));
                med.setPrix(resultat.getInt(5));
                med.setQuantite_min(resultat.getInt(6));
                listemeds.add(med);
            return listemeds;
            }
           /* Iterator <medicament> it = listemed.iterator();
            while(it.hasNext()){
             System.out.println(it.next().getLibelle());
            }*/
            
        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors du chargement des depots " + ex.getMessage());       
        } 
        return listemeds;
    }
    
    
    
    
    }
        
    
