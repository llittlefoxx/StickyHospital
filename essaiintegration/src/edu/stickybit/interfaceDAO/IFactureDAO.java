/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.stickybit.interfaceDAO;

import edu.stickybit.entity.Facture;
import java.util.List;

/**
 *
 * @author Dontey
 */
public interface IFactureDAO {
    
    void createFacture(Facture Facture);
    public void ajoutFacture(Facture facture);
    public int ajoutFacture2(Facture facture);

    void updateFacture(Facture f);

    void deleteFacture(int id);

    Facture findFactureById(int id);

    List<Facture> DisplayAllFactures();
    public int retourIdFact(int id_patient);
}
