/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entites;

/**
 *
 * @author SALAH
 */
public class Adherant {
     private int ida,numtel,cin ;
    private String nom,prenom,address,email,mdp,reset_token ,nomTerrain;

    public Adherant(int ida, String nom, String prenom,int cin , String email, String nomTerrain, String address,int numtel, String mdp, String reset_token) {
        this.ida = ida;
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.address = address;
        this.numtel = numtel;
        this.nomTerrain=nomTerrain;
        this.email = email;
        this.mdp = mdp;
        this.reset_token = reset_token;
    }

    public Adherant(int ida, String nom, String prenom,int cin , String email, String nomTerrain, String address,int numtel, String mdp) {
        this.ida = ida;
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.address = address;
        this.numtel = numtel;
        this.nomTerrain=nomTerrain;
        this.email = email;
        this.mdp = mdp;
    }

    public Adherant(String nom, String prenom, String email, String nomTerrain, String address,int numtel, String mdp) {
        this.nom = nom;
        this.prenom = prenom;
        this.address = address;
        this.numtel = numtel;
        this.nomTerrain=nomTerrain;
        this.email = email;
        this.mdp = mdp;
    }

    public Adherant() {
    }

    
    public int getIda() {
        return ida;
    }

    public void setIda(int ida) {
        this.ida = ida;
    }

    public int getNumtel() {
        return numtel;
    }

    public void setNumtel(int numtel) {
        this.numtel = numtel;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getReset_token() {
        return reset_token;
    }

    public void setReset_token(String reset_token) {
        this.reset_token = reset_token;
    }

    public String getNomTerrain() {
        return nomTerrain;
    }

    public void setNomTerrain(String nomTerrain) {
        this.nomTerrain = nomTerrain;
    }
    
    
     
}
