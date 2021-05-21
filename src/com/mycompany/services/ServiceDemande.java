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
import com.mycompany.entities.Demande;
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
public class ServiceDemande {

    public static ServiceDemande instance;
    public String result = "";
    private ConnectionRequest req;
    public boolean resultOK;
    public ArrayList<Demande> list;

    public static ServiceDemande getInstance() {

        if (instance == null) {
            instance = new ServiceDemande();
        }

        return instance;

    }

    public ServiceDemande() {

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

    public ArrayList<Demande> getAllDemande() {

        ArrayList<Demande> result = new ArrayList<>();
        String url = Statics.BASE_URL + "/getalldemande";
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
                        Demande t = new Demande();
                        float id = Float.parseFloat(obj.get("id").toString());
                        String date = obj.get("date").toString();
                        String nomterrain = obj.get("nomterrain").toString();
                        String nomequipe = obj.get("nomequipe").toString();
                        String email = obj.get("email").toString();
                        String etat = obj.get("etat").toString();
                        t.setId((int) id);
                        t.setNomterrain(nomterrain);
                        t.setNomequipe(nomequipe);
                        t.setEmail(email);
                        t.setEtat(etat);
                      
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

    public boolean ajoutDemande(Demande t) {
        String url = Statics.BASE_URL +"/adddemande"+ "?date=" + t.getDate() + "&nomterrain=" + t.getNomterrain() + "&nomequipe=" + t.getNomequipe()+"&email=" + t.getEmail()+"&etat="+t.getEtat()
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

    public boolean DeleteDemande(Demande t) {
        String url = Statics.BASE_URL + "/deletedemande/" +t.getId();
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

    public boolean editDemande(Demande t ) {
        String url = Statics.BASE_URL + "/updatedemande/?id="+ t.getId() + "&date=" + t.getDate() + "&nomterrain=" + t.getNomterrain() + "&nomequipe=" + t.getNomequipe() + "&email="+ t.getEmail()+"&etat="+ t.getEtat();
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
