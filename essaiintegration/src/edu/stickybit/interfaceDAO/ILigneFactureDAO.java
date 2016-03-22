/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stickybit.interfaceDAO;

import edu.stickybit.entity.LigneFacture;
import java.util.List;

/**
 *
 * @author Dontey
 */
public interface ILigneFactureDAO {

    void createLigneFacture(LigneFacture ligne);

    void updateLigneFacture(LigneFacture ligne);

    void deleteLigneFacture(int id);

    public List<LigneFacture> DisplayAllligne();

}
