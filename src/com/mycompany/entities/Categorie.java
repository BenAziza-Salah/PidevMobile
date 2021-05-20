/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entites;

/**
 *
 * @author maram
 */
public class Categorie { 
     private int idCategorie ; 
    private String type;
public Categorie(){
    
}
    public Categorie(String type) {
        this.type = type;
    }

    public Categorie(int idCategorie, String type) {
        this.idCategorie = idCategorie;
        this.type = type;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

