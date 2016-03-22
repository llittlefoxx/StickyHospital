/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.stickybit.interfaceDAO;


import edu.stickybit.entity.DossierMedical;
import java.util.List;

/**
 *
 * @author Asma Boussabat
 */
public interface IDossierMedicalDAO {
    
    void insertDossierMedical(DossierMedical dossier);
    
    void deleteDossierMedical(int id_dossierPatient);
    
    DossierMedical findDossierMedicaltById(int id_dossierPatient);
    
    List<DossierMedical> DisplayAllDossierMedical();
}

