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
public class Article {

    private int id_article;
    private String libelle;
    private String idcat;
    private String image_article;
    private String prix;
    private String qt_article;
    private String ref;
    private Double rate;

    public Article() {
    }
    
    public Article(int id_article, String libelle, String image_article, String prix,String qt_article , String ref,Double rate, String idcat) {
        this.id_article = id_article;
        this.libelle = libelle;
        this.idcat = idcat;
        this.image_article = image_article;
        this.prix = prix;
        this.qt_article = qt_article;
        this.ref = ref;
        this.rate = rate;

    }

    public Article(String libelle, String image_article, String prix,String qt_article , String ref,Double rate, String idcat) {
        this.libelle = libelle;
        this.idcat = idcat;
        this.image_article = image_article;
        this.prix = prix;
        this.qt_article = qt_article;
        this.ref = ref;
        this.rate = rate;


    }

    public Article(String ref) {
        this.ref = ref;
    }

    public Article(String text, String toString, String text0, Integer valueOf, Integer valueOf0) {
    }

    public Article(Integer valueOf) {
    }

    public Article(String text, String text0, int parseInt, int parseInt0, int parseInt1, double parseDouble) {
    }

   

    
   
    
   


    public void setImage_article(String image_article) {
        this.image_article = image_article;
    }

    public String getImage_article() {
        return image_article;
    }

    public int getId_article() {
        return id_article;
    }

    public String getLibelle() {
        return libelle;
    }

    public String getIdcat() {
        return idcat;
    }
    public Double getRate() {
        return rate ;
    }

    public String getPrix() {
        return prix;
    }

    public void setId_article(int id_article) {
        this.id_article = id_article;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public void setIdcat(String idcat) {
        this.idcat = idcat;
    }
  
     
    public void setPrix(String prix) {
        this.prix = prix;
    }

    public String getQt_article() {
        return qt_article;
    }

    public void setQt_article(String qt_article) {
        this.qt_article = qt_article;
    }

    @Override
    public String toString() {
        return "Article{" + "id_article=" + id_article + ", libelle=" + libelle + ", categorie=" + idcat + ", image_article=" + image_article + ", prix=" + prix + ", qt_article=" + qt_article +", ref=" + ref + ", rate=" + rate +'}';
    }

    public String getPath() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Article other = (Article) obj;
        if (this.prix != other.prix) {
            return false;
        }
        return true;
    }

   

}
