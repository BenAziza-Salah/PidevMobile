/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Equipe;
import com.mycompany.entities.Terrain;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author OussKh
 */
public class ServiceEquipe {

    public static ServiceEquipe instance;
    public String result = "";
    private ConnectionRequest req;
    public boolean resultOK;
    public ArrayList<Equipe> list;

    public static ServiceEquipe getInstance() {

        if (instance == null) {
            instance = new ServiceEquipe();
        }

        return instance;

    }

    public ServiceEquipe() {

        req = new ConnectionRequest();
    }

//    public ArrayList<Demande> parseTerrain(String jsonText) throws IOException {
//        try {
//            list = new ArrayList<>();
//            JSONParser j = new JSONParser();
//            Map<String, Object> MapListDemande = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
//            List<Map<String, Object>> listeM = (List<Map<String, Object>>) MapListDemande.get("root");
//            for (Map<String, Object> obj : listeM) {
//                Demande d = new Demande();
//                float id = Float.parseFloat(obj.get("idterrain").toString());
//                d.setId((int) id);
//                d.setDate(obj.get("date").toString());
//                t.setAdresse(obj.get("adresse").toString());
//                t.setEtat(obj.get("etat").toString());
//                t.setDescription(obj.get("description").toString());
//                t.setPhoto(obj.get("photo").toString());
//                list.add(t);
//            }
//        } catch (IOException ex) {
//
//        }
//
//        return list;
//    }

    public ArrayList<Equipe> getAllEquipe() {

        ArrayList<Equipe> result = new ArrayList<>();
        String url = Statics.BASE_URL + "/getallequipe";
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
                        Equipe t = new Equipe();
                        float id = Float.parseFloat(obj.get("idequipe").toString());
                        String nom = obj.get("nom").toString();
                       float nombre = Float.parseFloat(obj.get("nombre").toString());
                        
                        t.setIdequipe((int) id);
                        t.setNom(nom);
                       t.setNombre((int) nombre);
                       
                      
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

    public boolean ajoutEquipe(Equipe t) {
        String url = Statics.BASE_URL +"/addequipe"+ "?nom=" + t.getNom() + "&nombre=" + t.getNombre() 
                ;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public boolean DeleteEquipe(Equipe t) {
        String url = Statics.BASE_URL + "/deleteequipe/" +t.getIdequipe();
        req.setUrl(url);
       

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
         public void actionPerformed(NetworkEvent evt){
              
             req.removeResponseCodeListener(this);
             
         }
         
         });
        NetworkManager.getInstance().addToQueueAndWait(req);

         return resultOK;
    }

    public boolean editEquipe(Equipe t ) {
        String url = Statics.BASE_URL + "/updateequipe?idequipe="+ t.getIdequipe() + "&nom=" + t.getNom() + "&nombre=" + t.getNombre();
                //création de l'URL
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

}
