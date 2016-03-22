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

import edu.stickybit.technique.MyConnectionGroup;
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
public class ReportController {

    public ReportController(int idFacture, int idPatient) {
        Connection connection;
        try {

            // - Connexion à la base
            connection = MyConnectionGroup.getInstance();
            // - Chargement et compilation du rapport
            JasperDesign jasperDesign = JRXmlLoader.load("E:\\Facture.jrxml");
            String sql = "select patient.nom_patient, patient.prenom_patient,patient.adresse,patient.cin_patient,patient.tel_patient, patient.id_patient,facture.date_facturation, facture.etat, facture.total_facture, facture.id_patient, facture.id_facture,ligne_facture.quantite, ligne_facture.prix, ligne_facture.lib_ligne from patient, facture, ligne_facture where patient.id_patient = '" + idPatient + "' and facture.id_patient = '" + idPatient + "' and facture.id_facture = '" + idFacture + "' and ligne_facture.id_facture = '" + idFacture + "'";

            JRDesignQuery query = new JRDesignQuery();
            query.setText(sql);
            jasperDesign.setQuery(query);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            // - Paramètres à envoyer au rapport

            // - Execution du rapport
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, connection);
            JasperViewer.viewReport(jasperPrint);
            // - Création du rapport au format PDF
            JasperExportManager.exportReportToPdfFile(jasperPrint, "E:\\Facture.pdf");
            System.out.println("success");

        } catch (JRException e) {
            System.out.println("erreur de compilation" + e.getMessage());
        }
    }

}
