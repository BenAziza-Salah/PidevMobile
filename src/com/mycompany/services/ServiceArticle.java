/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.components.ToastBar;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.FontImage;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Article;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author  ArwaBj
 */
public class ServiceArticle{

    public ArrayList<Article> articles;
    
    public static ServiceArticle instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceArticle() {
         req = new ConnectionRequest();
    }

    public static ServiceArticle getInstance() {
        if (instance == null) {
            instance = new ServiceArticle();
        }
        return instance;
    }

    public boolean addArticle(Article t) {
       /* String url = Statics.BASE_URL + "/article?Libelle=" + t.getLibelle() + "&image_article" + t.getImage_article() + "&prix" + t.getPrix() + "&qt_article" + t.getQt_article() + "&ref" + t.getRef() + "&rate" + t.getRate() ; //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/
       //http://127.0.0.1:8000/addArticle?libelle="arwaaa"&image_article="arwaaaaaa"&qt_article=7&prix=6&ref=78&rate=1.2
                 String url = Statics.BASE_URL + "/addarticle?libelle=" + t.getLibelle() + "&image_article=" + t.getImage_article()  + "&qt_article=" + t.getQt_article()+ "&prix=" + t.getPrix() + "&ref=" + t.getRef() + "&rate=" + t.getRate() ; //création de l'URL

              req.setUrl(url);
               // req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
      
                
            
            
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
   ToastBar.showMessage("Article est ajoutée avec succée", FontImage.MATERIAL_ACCESS_TIME);

        return resultOK;
    }

    public ArrayList<Article> parseArticles(String jsonText){
        try {
            articles=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            /*
                On doit convertir notre réponse texte en CharArray à fin de
            permettre au JSONParser de la lire et la manipuler d'ou vient 
            l'utilité de new CharArrayReader(json.toCharArray())
            
            La méthode parse json retourne une MAP<String,Object> ou String est 
            la clé principale de notre résultat.
            Dans notre cas la clé principale n'est pas définie cela ne veux pas
            dire qu'elle est manquante mais plutôt gardée à la valeur par defaut
            qui est root.
            En fait c'est la clé de l'objet qui englobe la totalité des objets 
                    c'est la clé définissant le tableau de tâches.
            */
            Map<String,Object> articlesListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
              /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche.               
            
            Le format Json impose que l'objet soit définit sous forme
            de clé valeur avec la valeur elle même peut être un objet Json.
            Pour cela on utilise la structure Map comme elle est la structure la
            plus adéquate en Java pour stocker des couples Key/Value.
            
            Pour le cas d'un tableau (Json Array) contenant plusieurs objets
            sa valeur est une liste d'objets Json, donc une liste de Map
            */
            List<Map<String,Object>> list = (List<Map<String,Object>>)articlesListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Article t = new Article();
                float id_article = Float.parseFloat(obj.get("id_article").toString());
                t.setId_article((int)id_article);
                t.setImage_article((String)obj.get("imageArticle"));
                t.setLibelle(obj.get("libelle").toString());
                                t.setQt_article(obj.get("qtArticle").toString());
                t.setPrix(obj.get("prix").toString());
                t.setRef(obj.get("ref").toString());

//                float prix = Float.parseFloat(obj.get("prix").toString());
//                t.setPrix((int)prix);
//                float qt_article = Float.parseFloat(obj.get("qtArticle").toString());
//                t.setQt_article((int)qt_article);
               // t.setRef(((int)Float.parseFloat(obj.get("ref").toString())));
                t.setRate((Double.parseDouble(obj.get("rate").toString())));
                //t.setIdcat(obj.get("idcat").toString());

                //Ajouter la tâche extraite de la réponse Json à la liste
                articles.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return articles;
    }
    
    public ArrayList<Article> getAllArticles(){
        String url = Statics.BASE_URL+"/listArticle";
        req.setUrl(url);
        req.setPost(true);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                articles = parseArticles(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return articles;
    }
     public boolean deleteArticle(Article t) {
        String url = Statics.BASE_URL + "/deleteArticle/" + t.getId_article();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
     ToastBar.showMessage("Article supprimée", FontImage.MATERIAL_ACCESS_TIME);

        return resultOK;
    }
       public boolean editArticle(Article r) {
        String url = Statics.BASE_URL + "/updateArticle/" + r.getId_article()
                +"?libelle="+r.getLibelle()
                 +"&image_article="+r.getImage_article()
                +"&qt_article=" + r.getQt_article()
                +"&prix=" + r.getPrix()
                 +"&ref=" + r.getRef()
                +"&rate=" + r.getRate();
       
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
             ToastBar.showMessage("Article Modifiée", FontImage.MATERIAL_ACCESS_TIME);

        return resultOK;
    }
     

}
