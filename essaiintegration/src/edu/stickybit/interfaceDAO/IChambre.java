/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stickybit.interfaceDAO;

import edu.stickybit.entity.Chambre;
import java.util.List;

/**
 *
 * @author mermi
 */
public interface IChambre {

    void insertChambre(Chambre chambre);

    void updateChambre(Chambre chambre);

    void updateCapaciteChambre(int capacite, int idchambre);

    void deleteChambre(int idChambre);

    Chambre findChambrebyId(int idChambre);

    List<Chambre> findAvailableChambreByService(int idService);

    List<Chambre> findOccupedChambreByService(int idService);

    int findChambreByEtat(int etatChambre);

    int findCapaciteByIDChambre(int idChambre);

    List<Chambre> DisplayAllChambre();

    List<String> DisplayAllEtat();

    List<Chambre> DisplayAllChambreByService(int idService);

    List<String> DisplayIdChambreByService(int idService);
}
