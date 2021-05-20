/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entites;

/**
 *
 * @author lenovo
 */
public class Joueurs {
    private int age;
    private int IdClub,id;
    private String nom,prenom,email,nom_club;
    
    
    
    public Joueurs(int id, String nom, String prenom, int age, String email, int IdClub) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.email = email;
        this.IdClub = IdClub;
    }

    public Joueurs(String nom, String prenom, int age, String email, int IdClub) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.email = email;
        this.IdClub = IdClub;
    }

    public Joueurs(int age, int id, String nom, String prenom, String email) {
        this.age = age;
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

    public Joueurs() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
     public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdClub() {
        return IdClub;
    }

    public void setIdClub(int IdClub) {
        this.IdClub = IdClub;
    }

    public String getNom_club() {
        return nom_club;
    }

    public void setNom_club(String nom_club) {
        this.nom_club = nom_club;
    }

    @Override
    public String toString() {
         return "Joueurs{" + "nom=" + nom + ", prenom=" + prenom + ", age=" + age + ", email=" + email + ", nom_club=" + nom_club + '}';
       /// return "Joueurs{" + "age=" + age + ", id_club=" + id_club + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", nom_club=" + nom_club + '}';
    }
}
