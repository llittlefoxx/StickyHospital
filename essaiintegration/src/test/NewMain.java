/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import edu.stickybit.dao.classes.Authentification;
import edu.stickybit.dao.classes.FactureDAO;
import edu.stickybit.dao.classes.HospitalisationDao;
import edu.stickybit.dao.classes.SendEmail;
import edu.stickybit.entity.Hospitalisation;
import edu.stickybit.general.Prix;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author Asma Boussabat
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        /* HospitalisationDao dao=new HospitalisationDao();
        ArrayList<Hospitalisation> arra=new ArrayList();
        arra=(ArrayList<Hospitalisation>) dao.rechercherHospitalisatioParID(2);
        for (Hospitalisation hospitalisation : arra) {
        System.out.println(hospitalisation.toString());
        }
        */
        /*java.util.Date dd=new java.util.Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy,MM,dd");
        String formattedDate = formatter.format(dd);
        String kk = String.valueOf(formattedDate);
        System.out.println(kk);*/
       /* 
        HospitalisationDao f=new HospitalisationDao();
        System.err.println("nbr de jours : "+f.calculeDeJours(37));
        
        Prix p=new Prix();
        double x=Double.valueOf(p.prix_nuit) * f.calculeDeJours(37);
        System.err.println("prix total : "+x);
               */
        
        /* FactureDAO f=new FactureDAO();
        System.out.println(+f.retourIdFact(1));*/
        
        Authentification a=new Authentification();
        System.out.println(a.auth("test", "test"));
    }
    
}
