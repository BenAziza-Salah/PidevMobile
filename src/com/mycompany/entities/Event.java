/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entites;

import java.util.Date;

/**
 *
 * @author maram
 */
public class Event {
    private int id ; 
    private  String categoriesId;
    private  String nom;
    private  String description ;
    private  String lieuEvent ;
    private String dateEvent ;
    private float prix ;
    private int etat ;

    public Event(int id, String categoriesId, String nom, String description, String lieuEvent, String dateEvent, float prix) {
        this.id = id;
        this.categoriesId = categoriesId;
        this.nom = nom;
        this.description = description;
        this.lieuEvent = lieuEvent;
        this.dateEvent = dateEvent;
        this.prix = prix;
    }

    public Event(String nom, String description, String lieuEvent, String dateEvent, float prix, int etat) {
        this.nom = nom;
        this.description = description;
        this.lieuEvent = lieuEvent;
        this.dateEvent = dateEvent;
        this.prix = prix;
        this.etat = etat;
    }

    public Event(String text, String text0, String text1, String text2, String format, String text3) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Event(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "Event{" + "id=" + id + ", categoriesId=" + categoriesId + ", nom=" + nom + ", description=" + description + ", lieuEvent=" + lieuEvent + ", dateEvent=" + dateEvent + ", prix=" + prix + ", etat=" + etat + '}';
    }

    public Event(String categoriesId, String nom, String description, String lieuEvent, String dateEvent, float prix) {
        this.categoriesId = categoriesId;
        this.nom = nom;
        this.description = description;
        this.lieuEvent = lieuEvent;
        this.dateEvent = dateEvent;
        this.prix = prix;
        
    }

    public Event(int id, String categoriesId, String nom, String description, String lieuEvent, String dateEvent, float prix, int etat) {
        this.id = id;
        this.categoriesId = categoriesId;
        this.nom = nom;
        this.description = description;
        this.lieuEvent = lieuEvent;
        this.dateEvent = dateEvent;
        this.prix = prix;
        this.etat = etat;
    }
    
    public Event()
    {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(String categoriesId) {
        this.categoriesId = categoriesId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLieuEvent() {
        return lieuEvent;
    }

    public void setLieuEvent(String lieuEvent) {
        this.lieuEvent = lieuEvent;
    }

    public String getDateEvent() {
        return dateEvent;
    }

    public void setDateEvent(String dateEvent) {
        this.dateEvent = dateEvent;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }
       
} 

