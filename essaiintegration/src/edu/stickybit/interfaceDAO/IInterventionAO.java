/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stickybit.interfaceDAO;

import edu.stickybit.entity.Intervention;
import java.util.List;

/**
 *
 * @author Dontey
 */
public interface IInterventionAO {
    
    public void createIntervention(Intervention inter);
     public Intervention findIntervBylibelle(String lib);
     public List<Intervention> DisplayAllInterventions();
    
}
