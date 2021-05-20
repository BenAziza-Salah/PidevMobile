/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author OussKh
 */
public class Terrain {
    
    private int idterrain;
    private String  nomterrain,adresse,etat,description,photo;

    public Terrain() {
    }

    
    public Terrain(int idterrain, String nomerrain, String adresse, String etat, String description, String photo) {
        this.idterrain = idterrain;
        this.nomterrain = nomerrain;
        this.adresse = adresse;
        this.etat = etat;
        this.description = description;
        this.photo = photo;
    }

    public Terrain(String nomerrain, String adresse, String etat, String description, String photo) {
        this.nomterrain = nomerrain;
        this.adresse = adresse;
        this.etat = etat;
        this.description = description;
        this.photo = photo;
    }

    public int getIdterrain() {
        return idterrain;
    }

    public void setIdterrain(int idterrain) {
        this.idterrain = idterrain;
    }

    public String getNomterrain() {
        return nomterrain;
    }

    public void setNomterrain(String nomerrain) {
        this.nomterrain = nomerrain;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    
    
    
}
