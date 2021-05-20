/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author ArwaBj
 */
public class Categoriearticle {
 private int idcat;
 private String nomcat;

    public int getIdcat() {
        return idcat;
    }

    public String getNomcat() {
        return nomcat;
    }

    public void setIdcat(int idcat) {
        this.idcat = idcat;
    }

    public void setNomcat(String nomcat) {
        this.nomcat = nomcat;
    }

    public Categoriearticle(int idcat, String nomcat) {
        this.idcat = idcat;
        this.nomcat = nomcat;
    }

    public Categoriearticle() {
    }

    @Override
    public String toString() {
        return "CategorieArticle{" + "idcat=" + idcat + ", nomcat=" + nomcat + '}';
    }
 
}
