/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stickybit.interfaceDAO;

import edu.stickybit.entity.Medecin;
import java.util.List;

/**
 *
 * @author Tlili Mohamed Ali
 */
public interface InterfaceMedecinDAO {

    public int ajouterCompteMedecin(Medecin medecin);

    public void supprimerCompteMedecin(int id_medecin);

    public void modifierCompteMedecin(Medecin medecin);

    public Medecin rechercherCompteMedecinParId(int id_medecin);

    public List<Medecin> rechercherCompteMedecinParSpecialite(String specialite);

    public List<Medecin> rechercherCompteMedecinParNom(String nomMedecin);
    public int rechercherCompteMedecinParNom2(String nomMedecin);

    public List<Medecin> rechercherCompteMedecinParPrenom(String nomMedecin);

    public List<Medecin> rechercherCompteMedecinParNumTel(String numMedecin);
    
    public List<Medecin> rechercherToutCompteMedecin();

    public List<Medecin> rechercherToutCompteMedecinParService(int id_service);
}
