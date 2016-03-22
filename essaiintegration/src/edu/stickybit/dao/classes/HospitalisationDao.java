/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stickybit.dao.classes;

import edu.stickybit.interfaceDAO.IHospitalisation;
import edu.stickybit.technique.MyConnectionGroup;
import edu.stickybit.entity.Hospitalisation;
import static java.lang.Math.abs;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tlili Mohamed Ali
 */
public class HospitalisationDao implements IHospitalisation {

    private final Connection conn;

    /**
     *
     * @throws SQLException
     */
    public HospitalisationDao() throws SQLException {
        conn = MyConnectionGroup.getInstance();
    }

    @Override
    public int creerHospitalisation(Hospitalisation hospitalisation) {
        String requete = "insert into hospitalisation (id_patient,adm_login,id_lit,date_h,id_med,type_admisssion,etat_patient) values (?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(requete);

            ps.setInt(1, hospitalisation.getId_patient());
            ps.setString(2, hospitalisation.getAdm_login());
            ps.setInt(3, hospitalisation.getId_lit());
            ps.setString(4, hospitalisation.getDate_h());
            ps.setInt(5, hospitalisation.getId_med());
            ps.setString(6, hospitalisation.getType_admisssion());
            ps.setString(7, hospitalisation.getEtat_patient());
            ps.executeUpdate();
            MedecinDAO med = new MedecinDAO();

            System.out.println("Hospitalisation du patient avec l'id : " + med.rechercherCompteMedecinParId(hospitalisation.getId_patient()).toString() + " par le medecin : " + hospitalisation.getId_med() + " ajouté a : " + hospitalisation.getDate_h());
       return 1;
        } catch (SQLException ex) {
            Logger.getLogger(MedecinDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public void supprimerHospitalisatio(int id_hospitalisation) {
        String requete = "delete from hospitalisation where id_hos=?";
        try {
            PreparedStatement ps = conn.prepareStatement(requete);

            ps.setInt(1, id_hospitalisation);
            ps.executeUpdate();

            System.out.println("Hospitalisation supprimée !");
        } catch (SQLException ex) {
            Logger.getLogger(MedecinDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
/*
    @Override
    public void modifierHospitalisatio(int id_hos,String d) {
        String requete = "UPDATE hospitalisation SET date_s=? WHERE id_hos=?";
        try {
            PreparedStatement ps = conn.prepareStatement(requete);

            ps.setString(1,d);
            ps.setInt(2,id_hos);
           
            ps.executeUpdate();
            System.out.println("* Hospitalisation mise a jour ");
            System.out.println(hospitalisation.toString());
        } catch (SQLException ex) {
            Logger.getLogger(MedecinDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
*/
    @Override
    public Hospitalisation rechercherHospitalisatioParCinPatient(int cinPatient) {
        String requete = "SELECT * FROM hospitalisation WHERE id_patient=(select id_patient from patient where cin_patient=(?))";
        Hospitalisation hospitalisation = new Hospitalisation();
        try {
            PreparedStatement ps = conn.prepareStatement(requete);
            ps.setInt(1, cinPatient);
            ResultSet resu = ps.executeQuery();

            while (resu.next()) {
                hospitalisation.setId_hos(resu.getInt(1));
                hospitalisation.setId_patient(resu.getInt(2));
                hospitalisation.setAdm_login(resu.getString(3));
                hospitalisation.setId_lit(resu.getInt(4));
                hospitalisation.setDate_h(resu.getString(5));
             //   hospitalisation.setDate_s(resu.getDate(6));
                hospitalisation.setId_med(resu.getInt(7));
                hospitalisation.setType_admisssion(resu.getString(8));
                hospitalisation.setEtat_patient(resu.getString(9));
                System.out.println(hospitalisation.toString());
            }
            return hospitalisation;
        } catch (SQLException ex) {
            Logger.getLogger(MedecinDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Hospitalisation introuvable" + ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Hospitalisation> rechercherHospitalisatioParNomPatient(String nomPatient) {
        String requete = "SELECT * FROM hospitalisation WHERE id_patient=(select id_patient from patient where nom_patient=(?))";
        Hospitalisation hospitalisation = new Hospitalisation();
        List<Hospitalisation> listeHos = new ArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement(requete);
            ps.setString(1, nomPatient);
            ResultSet resu = ps.executeQuery();

            while (resu.next()) {
                hospitalisation.setId_hos(resu.getInt(1));
                hospitalisation.setId_patient(resu.getInt(2));
                hospitalisation.setAdm_login(resu.getString(3));
                hospitalisation.setId_lit(resu.getInt(4));
                hospitalisation.setDate_h(resu.getString(5));
               // hospitalisation.setDate_s(resu.getDate(6));
                hospitalisation.setId_med(resu.getInt(7));
                hospitalisation.setType_admisssion(resu.getString(8));
                hospitalisation.setEtat_patient(resu.getString(9));
                listeHos.add(hospitalisation);
                System.out.println(hospitalisation.toString());
            }
            return listeHos;
        } catch (SQLException ex) {
            Logger.getLogger(MedecinDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Hospitalisation introuvable" + ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Hospitalisation> rechercherHospitalisatioParPrenom(String prenomPatient) {
        String requete = "SELECT * FROM hospitalisation WHERE id_patient=(select id_patient from patient where prenom_patient=(?))";
        Hospitalisation hospitalisation = new Hospitalisation();
        List<Hospitalisation> listeHos = new ArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement(requete);
            ps.setString(1, prenomPatient);
            ResultSet resu = ps.executeQuery();

            while (resu.next()) {
                hospitalisation.setId_hos(resu.getInt(1));
                hospitalisation.setId_patient(resu.getInt(2));
                hospitalisation.setAdm_login(resu.getString(3));
                hospitalisation.setId_lit(resu.getInt(4));
                hospitalisation.setDate_h(resu.getString(5));
           //     hospitalisation.setDate_s(resu.getDate(6));
                hospitalisation.setId_med(resu.getInt(7));
                hospitalisation.setType_admisssion(resu.getString(8));
                hospitalisation.setEtat_patient(resu.getString(9));
                listeHos.add(hospitalisation);
                System.out.println(hospitalisation.toString());
            }
            return listeHos;
        } catch (SQLException ex) {
            Logger.getLogger(MedecinDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Hospitalisation introuvable" + ex.getMessage());
            return null;
        }
    }

    @Override
    public Hospitalisation rechercherHospitalisatioParNumTel(String numTelPatient) {
        String requete = "SELECT * FROM hospitalisation WHERE id_patient=(select id_patient from patient where tel_patient=(?))";
        Hospitalisation hospitalisation = new Hospitalisation();
        try {
            PreparedStatement ps = conn.prepareStatement(requete);
            ps.setString(1, numTelPatient);
            ResultSet resu = ps.executeQuery();

            while (resu.next()) {
                hospitalisation.setId_hos(resu.getInt(1));
                hospitalisation.setId_patient(resu.getInt(2));
                hospitalisation.setAdm_login(resu.getString(3));
                hospitalisation.setId_lit(resu.getInt(4));
                hospitalisation.setDate_h(resu.getString(5));
              //  hospitalisation.setDate_s(resu.getDate(6));
                hospitalisation.setId_med(resu.getInt(7));
                hospitalisation.setType_admisssion(resu.getString(8));
                hospitalisation.setEtat_patient(resu.getString(9));
                System.out.println(hospitalisation.toString());
            }
            return hospitalisation;
        } catch (SQLException ex) {
            Logger.getLogger(MedecinDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Hospitalisation introuvable" + ex.getMessage());
            return null;
        }

    }

    @Override
    public List<Hospitalisation> rechercherHospitalisatioParDate(Date dateH) {
        String requete = "SELECT * FROM hospitalisation WHERE Date_h=(?)";
        Hospitalisation hospitalisation = new Hospitalisation();
        List<Hospitalisation> listeHos = new ArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement(requete);
            ps.setDate(1, (java.sql.Date) dateH);
            ResultSet resu = ps.executeQuery();

            while (resu.next()) {
                hospitalisation.setId_hos(resu.getInt(1));
                hospitalisation.setId_patient(resu.getInt(2));
                hospitalisation.setAdm_login(resu.getString(3));
                hospitalisation.setId_lit(resu.getInt(4));
                hospitalisation.setDate_h(resu.getString(5));
            //    hospitalisation.setDate_s(resu.getDate(6));
                hospitalisation.setId_med(resu.getInt(7));
                hospitalisation.setType_admisssion(resu.getString(8));
                hospitalisation.setEtat_patient(resu.getString(9));
                listeHos.add(hospitalisation);
                System.out.println(hospitalisation.toString());
            }
            return listeHos;
        } catch (SQLException ex) {
            Logger.getLogger(MedecinDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Hospitalisation introuvable" + ex.getMessage());
            return null;
        }
    }

    
  
  
    @Override
    public List<Hospitalisation> rechercherHospitalisatioParID(int id_patient) {
        String requete = "SELECT * FROM hospitalisation WHERE id_patient=(?) and date_s is null ";
        
        Hospitalisation hospitalisation = new Hospitalisation();
        List<Hospitalisation> listeHos = new ArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement(requete);
            ps.setInt(1,id_patient);
            ResultSet resu = ps.executeQuery();

            while (resu.next()) {
                hospitalisation.setId_hos(resu.getInt(1));
                hospitalisation.setId_patient(resu.getInt(2));
                hospitalisation.setAdm_login(resu.getString(3));
                hospitalisation.setId_lit(resu.getInt(4));
                hospitalisation.setDate_h(resu.getString(5));
                hospitalisation.setDate_s(resu.getString(6));
                hospitalisation.setId_med(resu.getInt(7));
                hospitalisation.setType_admisssion(resu.getString(8));
                hospitalisation.setEtat_patient(resu.getString(9));
                listeHos.add(hospitalisation);
                System.out.println(hospitalisation.toString());
            }
            return listeHos;
        } catch (SQLException ex) {
            Logger.getLogger(MedecinDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Hospitalisation introuvable" + ex.getMessage());
            return null;
        }
    }

    @Override
    public void modifierHospitalisatio(int id_hos, String d) {
         String requete = "UPDATE hospitalisation SET date_s=? WHERE id_hos=?";
        try {
            PreparedStatement ps = conn.prepareStatement(requete);

            ps.setString(1,d);
            ps.setInt(2,id_hos);           
            ps.executeUpdate();
            System.out.println("* Hospitalisation mise a jour (cloturée)");
            
        } catch (SQLException ex) {
            Logger.getLogger(MedecinDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  @Override
    public double calculeDeJours(int id_hos) {
        String requete = "SELECT date_h, date_s FROM hospitalisation WHERE id_hos=(?)";
        String date2=null;
        String date=null;
        long x = 0;
        try {
            PreparedStatement ps = conn.prepareStatement(requete);
            ps.setInt(1,id_hos);
            ResultSet resu = ps.executeQuery();
            while (resu.next()) {                
                date=resu.getString(1);
                date2=resu.getString(2);
                System.out.println(date);
                
                System.out.println(date2);
        int a =Integer.parseInt(date.substring(0,4));
        int b =Integer.parseInt(date.substring(5,7));
        int c =Integer.parseInt(date.substring(8,10));
         int a1 =Integer.parseInt(date2.substring(0,4));
        int b1 =Integer.parseInt(date2.substring(5,7));
        int c1 =Integer.parseInt(date2.substring(8,10));
           
         Date ss1 = new Date();
         Date ss = new Date();
         ss.setYear(a);
         ss.setMonth(b);
         ss.setDate(c);
           ss1.setYear(a1);
         ss1.setMonth(b1);
         ss1.setDate(c1);
         
     
        //System.out.println(differenceDate(ss1, ss) );
x=differenceDate(ss1, ss);
                System.err.println(x);         
            }
            return x;
        } catch (Exception e) {
        }
    
    
    return x;
    }

   
      static long differenceDate(Date date1, Date date2)
  {
	long UNE_HEURE = 60 * 60 * 1000L;
    return ((date1.getTime() - date2.getTime() + UNE_HEURE) / (UNE_HEURE * 24));
  }

}
