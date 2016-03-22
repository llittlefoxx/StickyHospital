/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stickybit.general;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Tlili Mohamed Ali
 */
public class Hopital {

    private String nomHopital;
    private String adresse;
    private String telHopital;
    private String mailHopital;

    Properties properties;

    public Hopital() {
        properties = new Properties();
        try {
            properties.load(new FileInputStream(new File("hopital.properties")));
            nomHopital = properties.getProperty("nom");
            adresse = properties.getProperty("adresse");
            telHopital = properties.getProperty("numtel");
            mailHopital = properties.getProperty("mail");
            

        } catch (IOException e) {
            
        }

    }

    public String getNomHopital() {
        return nomHopital;
    }

    public void setNomHopital(String nomHopital) {
        this.nomHopital = nomHopital;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelHopital() {
        return telHopital;
    }

    public void setTelHopital(String telHopital) {
        this.telHopital = telHopital;
    }

    public String getMailHopital() {
        return mailHopital;
    }

    public void setMailHopital(String mailHopital) {
        this.mailHopital = mailHopital;
    }

    @Override
    public String toString() {
        return "Hopital{" + "nomHopital=" + nomHopital + ", adresse=" + adresse + ", telHopital=" + telHopital + ", mailHopital=" + mailHopital + ", properties=" + properties + '}';
    }

}
