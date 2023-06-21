/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uca.helpers;

import java.sql.Date;



/**
 *
 * @author lenovo
 */
public class Students {
    
    
    String cne;
    String nom;
    String prenom;
    String date;


    public Students(String cne, String nom, String prenom, String date) {
        this.cne = cne;
        this.nom = nom;
        this.prenom = prenom;
        this.date = date;
    }

    public String getCne() {
        return cne;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getDate() {
        return date;
    }

    
    
}
