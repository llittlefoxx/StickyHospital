/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.stickybit.interfaceDAO;

import edu.stickybit.entity.DossierPatient;
import java.util.List;

/**
 *
 * @author Asma Boussabat
 */
public interface IDossierPatientDAO {

  int insertDossierPatient(DossierPatient dossier_p);

    void deleteDossierPatient(int id_dossier_p);

    DossierPatient findDossierPatientById(int id_dossier_p);

    List<DossierPatient> DisplayAllDossierPatient();
}