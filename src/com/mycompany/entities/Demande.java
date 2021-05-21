/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author ABS1
 */
public class Demande {
     private int id;
   private  String date;
    private String  nomterrain;
    private String nomequipe;
    private String email;
    private String etat;

    public String getEtat() {
        return etat;
    }

    public Demande(String date, String nomterrain, String nomequipe, String email, String etat) {
        this.date = date;
        this.nomterrain = nomterrain;
        this.nomequipe = nomequipe;
        this.email = email;
        this.etat = etat;
    }

    public Demande(int id, String date, String nomterrain, String nomequipe, String email, String etat) {
        this.id = id;
        this.date = date;
        this.nomterrain = nomterrain;
        this.nomequipe = nomequipe;
        this.email = email;
        this.etat = etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Demande(String date, String nomterrain, String nomequipe, String email) {
        this.date = date;
        this.nomterrain = nomterrain;
        this.nomequipe = nomequipe;
        this.email = email;
    }
    
    public Demande (int id, String date, String nomterrain, String nomequipe,String email) {
        this.id = id;
        this.date =date;
        this.nomterrain = nomterrain;
        this.nomequipe= nomequipe;
        this.email=email;
        
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public Demande(int id) {
        this.id = id;
    }
    

    public Demande() {
    }

    public Demande(int id, String nomterrain, String nomequipe) {
        this.id = id;
        this.nomterrain = nomterrain;
        this.nomequipe = nomequipe;
    }

    
    

    public Demande(String date, String nomterrain, String nomequipe) {
        this.date = date;
        this.nomterrain = nomterrain;
        this.nomequipe = nomequipe;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getNomterrain() {
        return nomterrain;
    }

    public String getNomequipe() {
        return nomequipe;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setNomterrain(String nomterrain) {
        this.nomterrain = nomterrain;
    }

    public void setNomequipe(String nomequipe) {
        this.nomequipe = nomequipe;
    }

    @Override
    public String toString() {
        return "Demande{" + "id=" + id + ", date=" + date + ", nomterrain=" + nomterrain + ", nomequipe=" + nomequipe + '}';
    }
    
    
    
     
}

