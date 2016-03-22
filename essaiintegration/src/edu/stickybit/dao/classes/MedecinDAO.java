/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stickybit.dao.classes;

import edu.stikybit.ecxeptions.MedecinExistException;
import edu.stickybit.entity.Medecin;
import edu.stickybit.interfaceDAO.InterfaceMedecinDAO;
import edu.stickybit.technique.MyConnection;
import java.sql.Connection;
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
public class MedecinDAO implements InterfaceMedecinDAO {

    private final Connection conn;

    public MedecinDAO() throws SQLException {
        MyConnection cnx = new MyConnection();

        conn = cnx.dbCnx();
    }

    @Override
    public int ajouterCompteMedecin(Medecin medecin) {

        List<Medecin> lmr = new ArrayList();
        lmr = rechercherToutCompteMedecin();
        int i = 0;
        for (Medecin medecin1 : lmr) {
            if (medecin.equals(medecin1)) {

                i++;
                System.err.println("Medecin existant " + i);
            }
        }
        if (i == 0) {
            String requete = "insert into medecin (nom_medecin,prenom_medecin,adresse_medecin,mail_medecin,tel_medecin,specialite_medecin,id_service,cin_medecin) values (?,?,?,?,?,?,?,?)";
            try {

                PreparedStatement ps = conn.prepareStatement(requete);

                ps.setString(1, medecin.getNom_medecin());
                ps.setString(2, medecin.getPrenom_medecin());
                ps.setString(3, medecin.getAdresse_medecin());
                ps.setString(4, medecin.getMail_medecin());
                ps.setString(5, medecin.getTel_medecin());
                ps.setString(6, medecin.getSpecialite_medecin());
                ps.setInt(7, medecin.getId_service());
                ps.setString(8, medecin.getCin_medecin());
                ps.executeUpdate();
                System.out.println("medecin ajouté (M/Mme) : " + medecin.getNom_medecin());
                return 1;
            } catch (SQLException ex) {
                Logger.getLogger(MedecinDAO.class.getName()).log(Level.SEVERE, null, ex);
                return 2;

            }
        } else {
            try {
                throw new MedecinExistException();
            } catch (MedecinExistException ex) {
                Logger.getLogger(MedecinDAO.class.getName()).log(Level.SEVERE, null, ex);
                return 0;
            }
        }
    }

    @Override
    public void supprimerCompteMedecin(int id_medecin) {
        String requete = "delete from medecin where id_medecin=?";

        try {
            PreparedStatement ps = conn.prepareStatement(requete);
            ps.setInt(1, id_medecin);
            int resultat = ps.executeUpdate();
            if (resultat == 1) {

                System.out.println("le medecin avec l'id : ---" + id_medecin + "--- supprimée");
            } else {
                System.err.println("Medecin non supprimé !");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MedecinDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
    }

    @Override
    public void modifierCompteMedecin(Medecin medecin) {
        String requete = "UPDATE medecin SET nom_medecin=?, prenom_medecin=?, adresse_medecin=?, mail_medecin=?, tel_medecin=?, specialite_medecin=?, id_service=?, cin_medecin=? WHERE id_medecin=? ";
        try {
            PreparedStatement ps = conn.prepareStatement(requete);

            ps.setString(1, medecin.getNom_medecin());
            ps.setString(2, medecin.getPrenom_medecin());
            ps.setString(3, medecin.getAdresse_medecin());
            ps.setString(4, medecin.getMail_medecin());
            ps.setString(5, medecin.getTel_medecin());
            ps.setString(6, medecin.getSpecialite_medecin());
            ps.setInt(7, medecin.getId_service());
            ps.setString(8, medecin.getCin_medecin());
            ps.setInt(9, medecin.getId_medecin());
            ps.executeUpdate();
            System.out.println("* medecin mis a jour (M/Mme) : " + medecin.getNom_medecin());
            System.err.println(medecin.toString());
        } catch (SQLException ex) {
            Logger.getLogger(MedecinDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public Medecin rechercherCompteMedecinParId(int id_medecin) {
        String requete = "select * from medecin where id_medecin=?";
        Medecin medecin = new Medecin();

        try {
            PreparedStatement ps = conn.prepareStatement(requete);
            ps.setInt(1, id_medecin);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                medecin.setId_medecin(resultat.getInt(1));
                medecin.setNom_medecin(resultat.getString(2));
                medecin.setPrenom_medecin(resultat.getString(3));
                medecin.setAdresse_medecin(resultat.getString(4));
                medecin.setMail_medecin(resultat.getString(5));
                medecin.setTel_medecin(resultat.getString(6));
                medecin.setSpecialite_medecin(resultat.getString(7));
                medecin.setId_service(resultat.getInt(8));
                medecin.setCin_medecin(resultat.getString(9));
                System.out.println(medecin.toString());
            }
            return medecin;
        } catch (SQLException ex) {
            Logger.getLogger(MedecinDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("medecin introuvable" + ex.getMessage());
            return null;
        }

    }

    @Override
    public List<Medecin> rechercherCompteMedecinParNumTel(String numMedecin) {
        String requete = "SELECT * FROM medecin WHERE UPPER(tel_medecin) LIKE UPPER(?)";
        List<Medecin> listeMedecin = new ArrayList();

        try {
            PreparedStatement ps = conn.prepareStatement(requete);
            ps.setString(1, "%" + numMedecin + "%");
            ResultSet resultat = ps.executeQuery();

            while (resultat.next()) {
                Medecin medecin = new Medecin();

                medecin.setId_medecin(resultat.getInt(1));
                medecin.setNom_medecin(resultat.getString(2));
                medecin.setPrenom_medecin(resultat.getString(3));
                medecin.setAdresse_medecin(resultat.getString(4));
                medecin.setMail_medecin(resultat.getString(5));
                medecin.setTel_medecin(resultat.getString(6));
                medecin.setSpecialite_medecin(resultat.getString(7));
                medecin.setId_service(resultat.getInt(8));
                medecin.setCin_medecin(resultat.getString(9));
                System.err.println(medecin.toString());
                listeMedecin.add(medecin);

            }
            return listeMedecin;
        } catch (SQLException ex) {
            Logger.getLogger(MedecinDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("aucun numéro correspond ! " + ex.getMessage());
            return null;
        }

    }

    @Override
    public List<Medecin> rechercherCompteMedecinParSpecialite(String specialite) {
        String requete = "select * from medecin where specialite_medecin=?";
        List<Medecin> listeMedecin = new ArrayList();
        Medecin medecin = new Medecin();

        try {
            PreparedStatement ps = conn.prepareStatement(requete);
            ps.setString(1, specialite);
            ResultSet resultat = ps.executeQuery();
            while (resultat.next()) {
                medecin.setId_medecin(resultat.getInt(1));
                medecin.setNom_medecin(resultat.getString(2));
                medecin.setPrenom_medecin(resultat.getString(3));
                medecin.setAdresse_medecin(resultat.getString(4));
                medecin.setMail_medecin(resultat.getString(5));
                medecin.setTel_medecin(resultat.getString(6));
                medecin.setSpecialite_medecin(resultat.getString(7));
                medecin.setId_service(resultat.getInt(8));
                medecin.setCin_medecin(resultat.getString(9));
                listeMedecin.add(medecin);
                System.err.println(medecin.toString());

            }
            return listeMedecin;
        } catch (SQLException ex) {
            Logger.getLogger(MedecinDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("medecin(s) introuvable(s)" + ex.getMessage());
            return null;
        }

    }

    @Override
    public List<Medecin> rechercherCompteMedecinParNom(String nomMedecin) {
        String requete = "SELECT * FROM medecin WHERE UPPER(nom_medecin) LIKE UPPER(?)";
        List<Medecin> listeMedecin = new ArrayList();

        try {
            PreparedStatement ps = conn.prepareStatement(requete);
            ps.setString(1, "%" + nomMedecin + "%");
            ResultSet resultat = ps.executeQuery();

            while (resultat.next()) {
                Medecin medecin = new Medecin();

                medecin.setId_medecin(resultat.getInt(1));
                medecin.setNom_medecin(resultat.getString(2));
                medecin.setPrenom_medecin(resultat.getString(3));
                medecin.setAdresse_medecin(resultat.getString(4));
                medecin.setMail_medecin(resultat.getString(5));
                medecin.setTel_medecin(resultat.getString(6));
                medecin.setSpecialite_medecin(resultat.getString(7));
                medecin.setId_service(resultat.getInt(8));
                medecin.setCin_medecin(resultat.getString(9));
                System.err.println(medecin.toString());
                listeMedecin.add(medecin);

            }
            return listeMedecin;
        } catch (SQLException ex) {
            Logger.getLogger(MedecinDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur de recherche par nom ! " + ex.getMessage());
            return null;
        }

    }

    @Override
    public List<Medecin> rechercherCompteMedecinParPrenom(String PrenomMedecin) {
        String requete = "SELECT * FROM medecin WHERE UPPER(prenom_medecin) LIKE UPPER(?)";
        List<Medecin> listeMedecin = new ArrayList();

        try {
            PreparedStatement ps = conn.prepareStatement(requete);
            ps.setString(1, "%" + PrenomMedecin + "%");
            ResultSet resultat = ps.executeQuery();

            while (resultat.next()) {
                Medecin medecin = new Medecin();

                medecin.setId_medecin(resultat.getInt(1));
                medecin.setNom_medecin(resultat.getString(2));
                medecin.setPrenom_medecin(resultat.getString(3));
                medecin.setAdresse_medecin(resultat.getString(4));
                medecin.setMail_medecin(resultat.getString(5));
                medecin.setTel_medecin(resultat.getString(6));
                medecin.setSpecialite_medecin(resultat.getString(7));
                medecin.setId_service(resultat.getInt(8));
                medecin.setCin_medecin(resultat.getString(9));
                System.err.println(medecin.toString());;
                listeMedecin.add(medecin);
            }
            return listeMedecin;
        } catch (SQLException ex) {
            Logger.getLogger(MedecinDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur de recherche par prenom ! " + ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Medecin> rechercherToutCompteMedecin() {
        String requete = "SELECT * FROM medecin";
        List<Medecin> listeMedecin = new ArrayList();

        try {
            PreparedStatement ps = conn.prepareStatement(requete);

            ResultSet resultat = ps.executeQuery();

            while (resultat.next()) {
                Medecin medecin = new Medecin();

                medecin.setId_medecin(resultat.getInt(1));
                medecin.setNom_medecin(resultat.getString(2));
                medecin.setPrenom_medecin(resultat.getString(3));
                medecin.setAdresse_medecin(resultat.getString(4));
                medecin.setMail_medecin(resultat.getString(5));
                medecin.setTel_medecin(resultat.getString(6));
                medecin.setSpecialite_medecin(resultat.getString(7));
                medecin.setId_service(resultat.getInt(8));
                medecin.setCin_medecin(resultat.getString(9));
                System.err.println(medecin.toString());
                listeMedecin.add(medecin);

            }
            return listeMedecin;
        } catch (SQLException ex) {
            Logger.getLogger(MedecinDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erreur ! " + ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Medecin> rechercherToutCompteMedecinParService(int id_service) {
        String requete = "SELECT * FROM medecin where id_service=?";
        List<Medecin> listeMedecin = new ArrayList();

        try {
            PreparedStatement ps = conn.prepareStatement(requete);
            ps.setInt(1, id_service);
            ResultSet resultat = ps.executeQuery();

            while (resultat.next()) {
                Medecin medecin = new Medecin();

                medecin.setId_medecin(resultat.getInt(1));
                medecin.setNom_medecin(resultat.getString(2));
                medecin.setPrenom_medecin(resultat.getString(3));
                medecin.setAdresse_medecin(resultat.getString(4));
                medecin.setMail_medecin(resultat.getString(5));
                medecin.setTel_medecin(resultat.getString(6));
                medecin.setSpecialite_medecin(resultat.getString(7));
                medecin.setId_service(resultat.getInt(8));
                medecin.setCin_medecin(resultat.getString(9));
                System.err.println(medecin.toString());
                listeMedecin.add(medecin);

            }
            return listeMedecin;
        } catch (SQLException ex) {
            Logger.getLogger(MedecinDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erreur ! " + ex.getMessage());
            return null;
        }
    }

    @Override
    public int rechercherCompteMedecinParNom2(String nomMedecin) {
        Medecin med = new Medecin();
        String requete = "select id_medecin from medecin where nom_medecin=?";
        try {
            PreparedStatement ps = conn.prepareStatement(requete);
            ps.setString(1, nomMedecin);
            ResultSet resultat = ps.executeQuery();

            while (resultat.next()) {
                med.setId_medecin(resultat.getInt(1));
            }
            System.out.println(med.getId_medecin());
            return med.getId_medecin();

        } catch (SQLException ex) {
            //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("erreur lors de la recherche du chambre" + ex.getMessage());
            return 0;
        }
    }

}
