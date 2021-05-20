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
public class Client {
    private int idc,numtelc ;
    private String nom,prenom,address,email,mdp,reset_token;

    public Client(int idc, String nom, String prenom, String address,int numtelc,String email, String mdp, String reset_token) {
        this.idc = idc;
        this.nom = nom;
        this.prenom = prenom;
        this.address = address;
        this.numtelc = numtelc;
        this.email = email;
        this.mdp = mdp;
        this.reset_token = reset_token;
    }

    public Client(int idc, String nom, String prenom, String address, int numtelc, String email, String mdp) {
            this.idc = idc;
        this.nom = nom;
        this.prenom = prenom;
        this.address = address;
        this.numtelc = numtelc;
        this.email = email;
        this.mdp = mdp;
    }

    public Client( String nom, String prenom, String address,int numtelc, String email, String mdp) {
       
        this.nom = nom;
        this.prenom = prenom;
        this.address = address;
        this.numtelc = numtelc;
        this.email = email;
        this.mdp = mdp;
    
    }

    public Client() {
    }

    public int getIdc() {
        return idc;
    }

    public void setIdc(int idc) {
        this.idc = idc;
    }

    public int getNumtelc() {
        return numtelc;
    }

    public void setNumtelc(int numtelc) {
        this.numtelc = numtelc;
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
    
    
    
    
    
    
}
