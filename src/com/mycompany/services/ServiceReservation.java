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
import com.mycompany.entities.Reservation;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author OussKh
 */
public class ServiceReservation {

    public static ServiceReservation instance = null;

    private ConnectionRequest req;

    public String result = "";

    public boolean resultOK;
    public ArrayList<Reservation> list;

    public static ServiceReservation getInstance() {

        if (instance == null) {
            instance = new ServiceReservation();
        }

        return instance;

    }

    public ServiceReservation() {

        req = new ConnectionRequest();
    }

    public ArrayList<Reservation> parseReservation(String jsonText) throws IOException {
        try {
            list = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> MapListTerrain = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> listeM = (List<Map<String, Object>>) MapListTerrain.get("root");
            for (Map<String, Object> obj : listeM) {
                Reservation r = new Reservation();
                float id = Float.parseFloat(obj.get("idres").toString());
                r.setIdres((int) id);
                r.setDate(obj.get("date").toString());
                r.setHeuredebut(obj.get("heuredebut").toString());
                r.setHeurefin(obj.get("heurefin").toString());
                float idc = Float.parseFloat(obj.get("idclient").toString());
                r.setIdclient((int) idc);
                float idt = Float.parseFloat(obj.get("idterrain").toString());
                r.setIdterrain((int) idt);
                list.add(r);
            }
        } catch (IOException ex) {

        }

        return list;
    }

    public ArrayList<Reservation> getAllReservation() {

        ArrayList<Reservation> result = new ArrayList<>();
        String url = Statics.BASE_URL + "/reservation/liste/json";

//        req.setUrl(url);
//        req.setPost(false);
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                JSONParser jsonp;
//                jsonp = new JSONParser();
//
//                try {
//                    Map<String, Object> map = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
//                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) map.get("root");
//
//                    for (Map<String, Object> obj : listOfMaps) {
//                        Reservation r = new Reservation();
//                        float id = Float.parseFloat(obj.get("idres").toString());
//                        r.setIdres((int) id);
//                        r.setDate(obj.get("date").toString());
//                        r.setHeuredebut(obj.get("heuredebut").toString());
//                        r.setHeurefin(obj.get("heurefin").toString());
//                        float idc = Float.parseFloat(obj.get("idclient").toString());
//                        r.setIdclient((int) idc);
//                        float idt = Float.parseFloat(obj.get("idterrain").toString());
//                        r.setIdterrain((int) idt);
//                        list.add(r);
//                    }
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//
//                }
//            }
//        }
//        );
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
                        Reservation r = new Reservation();
                        float id = Float.parseFloat(obj.get("idres").toString());
                        r.setIdres((int) id);
                        String date = obj.get("date").toString();
                        String heuredebut = obj.get("heuredebut").toString();
                        String heurefin = obj.get("heuredebut").toString();
                        
                        r.setDate(date);
                        r.setHeuredebut(heuredebut);
                        r.setHeurefin(heurefin);
//                        float idt = Float.parseFloat(obj.get("idterrain").toString());
//                        r.setIdterrain((int) idt);
//                        float idc = Float.parseFloat(obj.get("idtclient").toString());
//                        r.setIdclient((int) idc);
                       
                        result.add(r);
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

    public boolean ajoutReservation(Reservation r) {
        String url = Statics.BASE_URL + "/reservation/add/json?date=" + r.getDate()+ "&heuredebut=" + r.getHeuredebut()+ "&heurefin=" + r.getHeurefin()
                + "&idclient=" + r.getIdclient()+ "idterrain=" + r.getIdterrain();
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

    public boolean supprimerReservation(Reservation r) {
        String url = Statics.BASE_URL + "/reservation/delete/json/?idres=" + r.getIdres();
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

    public boolean EditReservation(Reservation r) {
        String url = Statics.BASE_URL + "/reservation/update/json/?id=" + r.getIdres()+ "?date=" + r.getDate()+ "&heuredebut=" + r.getHeuredebut()+ "&heurefin=" + r.getHeurefin()
                + "&idclient=" + r.getIdclient()+ "idterrain=" + r.getIdterrain(); //création de l'URL
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
