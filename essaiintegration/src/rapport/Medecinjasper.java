/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rapport;

import edu.stickybit.technique.MyConnection;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Tlili Mohamed Ali
 */
public class Medecinjasper {

   // public final Connection conn;
    /**
     * Petite classe utilitaire pour lancer une impression d'un état
     * JasperReport On suppose que cette classe est placée avec les différents
     * rapports dans le package "rapports".
     */
    /**
     * @param nomDuRapport Le nom du rapport sans son extension .jasper
     * @param conn La connexion java.sql à la base de donnée
     * @param formatSortie "Aperçu","Imprimante","Tableur","HTML","PDF"
     * @param params Map des différents paramètres passés au rapport (ID_CLIENT
     * par exemple) pour utilisation par le rapport
     * @param cheminFichierDeSortie chemin complet (avec extension) du fichier
     * de sortie (HTML, Tableur, PDF). Indiquer null si Aperçu ou Imprimante
     * @throws java.sql.SQLException
     */
    public Medecinjasper(String nomDuRapport, Connection conn, String formatSortie, Map<String, Object> params, String cheminFichierDeSortie) throws SQLException {

        MyConnection cnx = new MyConnection();

        

        params.put("SUBREPORT_DIR", "rapport/");
        //  on suppose que le package dans lequel se trouve le rapport est nommé "rapport"
         
        URL cheminRapport = getClass().getResource("Cherry"+".jasper");
        conn = cnx.dbCnx();
        try {

            // chargement du rapport :
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(cheminRapport);

            // suppression de la pagination si format HTML (sinon on est au format A4 !!!)
            if (formatSortie.equals("HTML")) {
                params.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.TRUE);
            }

            // calcul et mise en page du rapport avec utilisation des paramètres et de la connexion
            // l'objet jasperPrint contient le résultat
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, conn);

            // si le format de sortie est "Aperçu", le résultat est placé dans un JFrame (JasperViewer) qui fait un EXIT_ON_CLOSE par défaut
            // il faut donc passer le paramètre false pour empêcher l'arrêt de l'application après un aperçu
            if (formatSortie.equals("Aperçu")) {
                final JasperViewer Jv = new JasperViewer(jasperPrint, false); // le false empèche l'arrêt de l'application
                JFrame j=new JFrame();
               Jv.show();
            }

            if (formatSortie.equals("PDF")) {
                JasperExportManager.exportReportToPdfFile(jasperPrint, cheminFichierDeSortie);
            }

            // pour exporter au format HTML JasperExportManager crée un sous répertoire.
            // en cas de ré-exportation du rapport, il faut supprimer le sous-répertoire sinon ça plante
            if (formatSortie.equals("HTML")) {
                String NomRepHtml = cheminFichierDeSortie.subSequence(0, cheminFichierDeSortie.lastIndexOf(".")) + "_files";
                File RepHtm = new File(NomRepHtml);
                if (RepHtm.exists()) {
                    File[] Fichiers = RepHtm.listFiles();
                    for (File fichier : Fichiers) {
                        fichier.delete();
                    }
                }
                RepHtm.delete();
                JasperExportManager.exportReportToHtmlFile(jasperPrint, cheminFichierDeSortie);
            }

            if (formatSortie.equals("Imprimante")) {
                JasperPrintManager.printReport(jasperPrint, false);
            }

            if (formatSortie.equals("Tableur")) {
                JRXlsExporter exporter = new JRXlsExporter();

                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, cheminFichierDeSortie);
                exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);

                exporter.exportReport();

            }

        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    /* 
     public void testImpression() {
	 
     // exemple de sortie au format PDF du rapport "interventions" au format PDF pour l'équipement N°123
     DbCnx cnx = new DbCnx();
     Connection conn = cnx.connection_a_postgresql();      // la connexion à la base de données
     Map<String, Object> params =  new HashMap<String, Object>();
     params.put("id_equipement",123);
     new ImprJasper("interventions", conn, "PDF", params, "c:/Users/MaPomme/kika.pdf");
     }    
	 
     */
    /*
     public void testttt() {
	        
     try{
     DbCnx cnx = new DbCnx();
     Connection conn = cnx.connection_a_postgresql();
     JasperDesign jasperDesign = JRXmlLoader.load("rapport/fact.jrxml");
     String sql = "select * from sh_caisse.commande";
     JRDesignQuery newQuery = new JRDesignQuery();
     newQuery.setText(sql);
     jasperDesign.setQuery(newQuery);
     JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
     JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, conn);
     JasperViewer.viewReport(jasperPrint);

     }catch (Exception e){
     JOptionPane.showMessageDialog(null, e);
     }
		
     }
     */
}
