/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stickybit.interfaceDAO;

import edu.stickybit.entity.Hospitalisation;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Tlili Mohamed Ali
 */
public interface IHospitalisation {

    public int creerHospitalisation(Hospitalisation hospitalisation);

    public void supprimerHospitalisatio(int id_hospitalisation);

    public void modifierHospitalisatio(int id_hos,String d);

    public Hospitalisation rechercherHospitalisatioParCinPatient(int cinPatient);

    public List<Hospitalisation> rechercherHospitalisatioParNomPatient(String nomPatient);    
  

    public List<Hospitalisation> rechercherHospitalisatioParPrenom(String prenomPatient);

    public Hospitalisation rechercherHospitalisatioParNumTel(String numTelPatient);
    
    public List<Hospitalisation> rechercherHospitalisatioParID(int id_patient);

    public List<Hospitalisation> rechercherHospitalisatioParDate(Date dateH);
    
     public double calculeDeJours(int id_hos);
    
    

}
