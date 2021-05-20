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
public class Reservation {
    
    
    private int idres;
    private String date;
    private String  heuredebut,heurefin;
    private int idclient,idterrain;

    public Reservation() {
    }

    public Reservation(int idres, String date, String  heuredebut, String  heurefin, int idclient, int idterrain) {
        this.idres = idres;
        this.date = date;
        this.heuredebut = heuredebut;
        this.heurefin = heurefin;
        this.idclient = idclient;
        this.idterrain = idterrain;
    }

    public Reservation(String  date, String  heuredebut, String  heurefin, int idclient, int idterrain) {
        this.date = date;
        this.heuredebut = heuredebut;
        this.heurefin = heurefin;
        this.idclient = idclient;
        this.idterrain = idterrain;
    }

    public Reservation(String date, String heuredebut, String heurefin, int idterrain) {
        this.date = date;
        this.heuredebut = heuredebut;
        this.heurefin = heurefin;
        this.idterrain = idterrain;
    }
    

    public int getIdres() {
        return idres;
    }

    public void setIdres(int idres) {
        this.idres = idres;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeuredebut() {
        return heuredebut;
    }

    public void setHeuredebut(String heuredebut) {
        this.heuredebut = heuredebut;
    }

    public String getHeurefin() {
        return heurefin;
    }

    public void setHeurefin(String heurefin) {
        this.heurefin = heurefin;
    }

    public int getIdclient() {
        return idclient;
    }

    public void setIdclient(int idclient) {
        this.idclient = idclient;
    }

    public int getIdterrain() {
        return idterrain;
    }

    public void setIdterrain(int idterrain) {
        this.idterrain = idterrain;
    }
    
    
}
