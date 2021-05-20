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
//import com.sun.xml.internal.org.jvnet.mimepull;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entites.Joueurs;
import com.mycompany.utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lenovo
 */
public class ServiceJoueurs {

    public ArrayList<Joueurs> joueurs;
    Joueurs j = new Joueurs();
    public static ServiceJoueurs instance = null;

    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceJoueurs() {
        req = new ConnectionRequest();
    }

    public static ServiceJoueurs getInstance() {
        if (instance == null) {
            instance = new ServiceJoueurs();
        }
        return instance;
    }

    public boolean addJoueurs(Joueurs t) {
        String url = Statics.BASE_URL + "/joueurs/newjson?nom=" + t.getNom() + "&prenom=" + t.getPrenom() + "&age=" + t.getAge() + "&email=" + t.getEmail() + "&idClub=" + t.getIdClub();//création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener((NetworkEvent evt) -> {
            resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        ToastBar.showMessage("Joueur Ajouter avec succes! Un email est envoyer avec les coordonnées!", FontImage.MATERIAL_ACCESS_TIME);
        return resultOK;
       
    }

    public ArrayList<Joueurs> getAllJoueurs() {

        ArrayList<Joueurs> result = new ArrayList<>();
        String url = Statics.BASE_URL + "/joueurs/display";
        req.setUrl(url);

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();

                try {
                    Map<String, Object> mapThemetest = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapThemetest.get("root");

                    for (Map<String, Object> obj : listOfMaps) {
//                //Création des tâches et récupération de leurs données
                        Joueurs t = new Joueurs();
                        float id = Float.parseFloat(obj.get("id").toString());
                        float age = Float.parseFloat(obj.get("age").toString());
                        String Nom = obj.get("nom").toString();
                        String Prenom = obj.get("prenom").toString();
                        //int age = Integer.parseInt(obj.get("age").toString());
                        String Email = obj.get("email").toString();
                        t.setId((int) id);
                        t.setNom(Nom);
                        t.setPrenom(Prenom);
                        t.setAge((int) age);
                        t.setEmail(Email);
                        result.add(t);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();

                }
            }
        }
        );
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        return result;
    }

    public boolean Deletejr(Joueurs j) {
        String url = Statics.BASE_URL + "/joueurs/delete/json?id=" + j.getId();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                req.removeResponseCodeListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        ToastBar.showMessage("Joueur Supprimer avec succes!", FontImage.MATERIAL_ACCESS_TIME);
        return resultOK;
    }

    
}
