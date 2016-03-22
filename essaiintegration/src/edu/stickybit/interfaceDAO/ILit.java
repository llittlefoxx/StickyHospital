/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stickybit.interfaceDAO;

import edu.stickybit.entity.Lit;
import java.util.List;

/**
 *
 * @author mermi
 */
public interface ILit {
    
    void insertLit(Lit lit);
    void updateLit(int idLit, String etat);
    void deleteLit(int idchambre);
    Lit FindLitById(int idlit);
    List<Lit> FindLitByIdChambre(int idChambre);
    List<Lit> DisplatAllLit();
    List<String> DisplayAllEtat();
    List<Lit> DisplayAllLitDisponible(int idChambre);
    List<Lit> DisplayAllLitByRoom(int idChambre);
    public List<Lit> DisplatAllLitbyetat();
}
