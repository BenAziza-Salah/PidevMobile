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
public class ServiceTerrain {

    public static ServiceTerrain instance;
    public String result = "";
    private ConnectionRequest req;
    public boolean resultOK;
    public ArrayList<Terrain> list;

    public static ServiceTerrain getInstance() {

        if (instance == null) {
            instance = new ServiceTerrain();
        }

        return instance;

    }

    public ServiceTerrain() {

        req = new ConnectionRequest();
    }

    public ArrayList<Terrain> parseTerrain(String jsonText) throws IOException {
        try {
            list = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> MapListTerrain = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> listeM = (List<Map<String, Object>>) MapListTerrain.get("root");
            for (Map<String, Object> obj : listeM) {
                Terrain t = new Terrain();
                float id = Float.parseFloat(obj.get("idterrain").toString());
                t.setIdterrain((int) id);
                t.setNomterrain(obj.get("nomterrain").toString());
                t.setAdresse(obj.get("adresse").toString());
                t.setEtat(obj.get("etat").toString());
                t.setDescription(obj.get("description").toString());
                t.setPhoto(obj.get("photo").toString());
                list.add(t);
            }
        } catch (IOException ex) {

        }

        return list;
    }

    public ArrayList<Terrain> getAllTerrain() {

        ArrayList<Terrain> result = new ArrayList<>();
        String url = Statics.BASE_URL + "/terrain/liste/json";
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
                        Terrain t = new Terrain();
                        float id = Float.parseFloat(obj.get("idterrain").toString());
                        String Nom = obj.get("nomterrain").toString();
                        String adresse = obj.get("adresse").toString();
                        String etat = obj.get("etat").toString();
                        String Description = obj.get("description").toString();
                        t.setIdterrain((int) id);
                        t.setNomterrain(Nom);
                        t.setAdresse(adresse);
                        t.setEtat(etat);
                        t.setDescription(Description);
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

    public boolean ajoutTerrain(Terrain t) {
        String url = Statics.BASE_URL +"/terrain/add/json"+ "?nomterrain=" + t.getNomterrain() + "&adresse=" + t.getAdresse() + "&etat=" + t.getEtat()
                + "&description=" + t.getDescription() + "photo=" + t.getPhoto();
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

    public boolean DeleteTerrain(Terrain t) {
        String url = Statics.BASE_URL + "/terrain/delete/json/?idterrain=" +t.getIdterrain();
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

    public boolean editTerrain(Terrain t) {
        String url = Statics.BASE_URL + "/terrain/update/json?idterrain=" + t.getIdterrain() + "nomterrain=" + t.getNomterrain() + "&adresse=" + t.getAdresse() + "&etat="
                + t.getEtat() + "&description=" + t.getDescription() + "photo=" + t.getPhoto(); //création de l'URL
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
