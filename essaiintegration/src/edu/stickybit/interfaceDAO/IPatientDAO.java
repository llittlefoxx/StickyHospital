/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.stickybit.interfaceDAO;


import edu.stickybit.entity.Patient;
import java.util.List;

/**
 *
 * @author Asma Boussabat
 */
public interface IPatientDAO {

    int insertPatient(Patient patient);
    void updatePatient(Patient patient);
    int updatePatientG(Patient patient);
    int deletePatient(int id);
    void deletePatient2(String nom_patient);
    Patient findPatientById(int id);
    List<Patient> findPatientByName(String nom_patient);
    List<Patient> DisplayAllPatient();
    public List<Patient> findPatientByCin(int cin);
}
