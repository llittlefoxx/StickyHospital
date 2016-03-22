/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rapport;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import edu.stickybit.technique.MyConnection;
import java.sql.Connection;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Dontey
 */
public class DossierRapport {

    public DossierRapport(int idMedecin,int idPatient) {
        Connection connection;
        try {
            
            // - Connexion à la base
            connection = MyConnection.getInstance();
           // - Chargement et compilation du rapport
            JasperDesign jasperDesign = JRXmlLoader.load("E:\\Dossier.jrxml");
            String sql= "select * from patient, hospitalisation, medecin, service where patient.id_patient = '"+idPatient+"'and medecin.id_medecin = '" +idMedecin+"' and hospitalisation.id_patient = '"+idPatient+"' and medecin.id_service = service.id_serv";
                      
            JRDesignQuery query = new JRDesignQuery();
            query.setText(sql);
            jasperDesign.setQuery(query);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

            // - Paramètres à envoyer au rapport
           

            // - Execution du rapport
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, connection);
            JasperViewer.viewReport(jasperPrint);
            // - Création du rapport au format PDF
            JasperExportManager.exportReportToPdfFile(jasperPrint, "E:\\Dossier.pdf");
            System.out.println("success");
            
          
           
            
            
        } catch (JRException e) {
            System.out.println("erreur de compilation" + e.getMessage());
        }
    }
    
   
            
    
}
