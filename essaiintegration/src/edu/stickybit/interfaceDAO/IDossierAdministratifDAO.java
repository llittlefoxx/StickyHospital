/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.stickybit.interfaceDAO;

import edu.stickybit.entity.DossierAdministratif;
import edu.stickybit.entity.DossierPatient;
import java.util.List;

/**
 *
 * @author Asma Boussabat
 */
public interface IDossierAdministratifDAO {
    void insertDossierAdministratif(DossierAdministratif dossier);

    void deleteDossierAdministratif(int id_dossierPatient);

    DossierAdministratif findDossierAdministratiftById(int id_dossierPatient);

    List<DossierAdministratif> DisplayAllDossierAdministratif();
}
