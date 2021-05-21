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
public class Equipe {
    private int idequipe;
    private String nom;
    private int  nombre;

    public Equipe(int idequipe, String nom, int nombre) {
        this.idequipe = idequipe;
        this.nom = nom;
        this.nombre = nombre;
    }
    public Equipe() {
       
    }

    public Equipe(int idequipe) {
        this.idequipe = idequipe;
    }

    public Equipe(String nom, int nombre) {
        this.nom = nom;
        this.nombre = nombre;
    }

    public int getIdequipe() {
        return idequipe;
    }

    public String getNom() {
        return nom;
    }

    public int getNombre() {
        return nombre;
    }

    public void setIdequipe(int idequipe) {
        this.idequipe = idequipe;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Equipe{" + "idequipe=" + idequipe + ", nom=" + nom + ", nombre=" + nombre + '}';
    }
    
    
    
  
    
}
