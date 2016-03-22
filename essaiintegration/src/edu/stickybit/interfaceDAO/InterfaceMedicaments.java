/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stickybit.interfaceDAO;

import edu.stickybit.entity.Medicament;
import java.util.List;



public interface InterfaceMedicaments {
    void insertmed(Medicament med);

    void updatemed(Medicament med);

    void deletemed(int code);
    void deletemedlib(String lib);
    Medicament findmedById(int code);

    Medicament findmedBylibelle(String lib);
    
    
    List<Medicament> Displaystock();
   

    List<Medicament> DisplayAllmed();
}
