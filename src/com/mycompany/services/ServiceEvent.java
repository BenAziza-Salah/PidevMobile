/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.components.ToastBar;
import com.codename1.io.ConnectionRequest;
import java.util.ArrayList;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.FontImage;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entites.Categorie;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.mycompany.utils.Statics;
import com.mycompany.entites.Event;
import java.util.Date;

/**
 *
 * @author yassin
 */
public class ServiceEvent {

    public static ServiceEvent instance = null;
    public static boolean resultOk = true;

    private ConnectionRequest req;

    public static ServiceEvent getInstance() {
        if (instance == null) {
            instance = new ServiceEvent();
        }
        return instance;
    }

    public ServiceEvent() {
        req = new ConnectionRequest();
    }

    //Ajout
    public void addEvent(Event event) throws Exception {

        String url = Statics.BASE_URL + "/event/addEvent/ok?categories_id=" + event.getCategoriesId()
                + "&nom=" + event.getNom()
                + "&Description=" + event.getDescription()
                + "&Lieu_event=" + event.getLieuEvent()
                + "&Date_event=" + event.getDateEvent()
                + "&Prix=" + event.getPrix();
        //Na9esa el DateTraitement,NomPrenomCoach,imgRec
        System.out.println(url);
        req.setUrl(url);
        req.addResponseListener(e -> {
            String str = new String(req.getResponseData());//reponse jason 
            System.out.println("data ==> " + str);
        ToastBar.showMessage("Votre evenement est bien ajoutee", FontImage.MATERIAL_ACCESS_TIME);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);//execution te3 request
    }
      public ArrayList<Event> AffichageEvent() {
        ArrayList<Event> result = new ArrayList<>();
        String url = Statics.BASE_URL + "/event/Affichage/ok";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();
                try {
                    Map<String, Object> mapReclamation = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String, Object>> listofMaps = (List<Map<String, Object>>) mapReclamation.get("root");

                    for (Map<String, Object> obj : listofMaps) {
                        Event re = new Event();

                        int id = (int) Float.parseFloat(obj.get("id").toString());
                        String idCategorie = obj.get("categoriesId").toString();
                        String Nom = obj.get("nom").toString();
                        String Description = obj.get("description").toString();
                        String LieuxEvent = obj.get("lieuEvent").toString();
                        String DateEvent = obj.get("dateEvent").toString();
                        float Prix = (float) Float.parseFloat(obj.get("prix").toString());

                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
                        String dateString = formatter.format(DateEvent);

                        /////////////////////////////://////////////////:///////:Lel Date tawww //////////////////://////////////////://////////////////:                       
                        re.setId(id);
                        re.setCategoriesId(idCategorie);
                        re.setNom(Nom);
                        re.setDescription(Description);
                        re.setLieuEvent(LieuxEvent);
                        re.setDateEvent(dateString);

                        re.setPrix(Prix);

                        result.add(re);

                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);//execution te3 request
        return result;
    }


    //Affichage
   /* public ArrayList<Event> AffichageEvent() {
        ArrayList<Event> result = new ArrayList<>();
        String url = Statics.BASE_URL + "/event/Affichage/ok";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();
                try {
                    Map<String, Object> mapReclamation = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String, Object>> listofMaps = (List<Map<String, Object>>) mapReclamation.get("root");

                    for (Map<String, Object> obj : listofMaps) {
                        Event re = new Event();

                        int id = (int) Float.parseFloat(obj.get("id").toString());
                        String idCategorie = obj.get("categoriesId").toString();
                        String Nom = obj.get("nom").toString();
                        String Description = obj.get("description").toString();
                        String LieuxEvent = obj.get("lieuEvent").toString();
                        String DateEvent = obj.get("dateEvent").toString();
                        float Prix = (float) Float.parseFloat(obj.get("prix").toString());

                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
                        String dateString = formatter.format(DateEvent);

                        /////////////////////////////://////////////////:///////:Lel Date tawww //////////////////://////////////////://////////////////:                       
                        re.setId(id);
                        re.setCategoriesId(idCategorie);
                        re.setNom(Nom);
                        re.setDescription(Description);
                        re.setLieuEvent(LieuxEvent);
                        re.setDateEvent(dateString);

                        re.setPrix(Prix);

                        result.add(re);

                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);//execution te3 request
        return result;
    }*/

    //Delete Reclamation
    public boolean deleteEvent(int id) {
        String url = Statics.BASE_URL + "/event/SuprimerEvent/ok?id=" + id;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                req.removeResponseListener(this);
            }
        });
          ToastBar.showMessage("Votre evenement est bien ajoutee", FontImage.MATERIAL_ACCESS_TIME);
        NetworkManager.getInstance().addToQueueAndWait(req);//execution te3 request
        return resultOk;
        
    }
    
//Modifier Reclamation
    public boolean ModifierEvent(Event event) {
        String url = Statics.BASE_URL + "/event/updateEvent/ok?id=" + event.getId() + "&categories_id=" + event.getCategoriesId() + "&nom=" + event.getNom() + "&Description=" + event.getDescription() + "&Lieu_event=" + event.getLieuEvent() + "&Date_event=" + event.getDateEvent() + "&Prix=" + event.getPrix();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200; //code success  http 200 ok
                req.removeResponseListener(this);
            }
        });
        System.out.println(url);
        req.setUrl(url);
        req.addResponseListener(e -> {
            String str = new String(req.getResponseData());//reponse jason 
            System.out.println("data ==> " + str);
        });  ToastBar.showMessage("Votre evenement est bien ajoutee", FontImage.MATERIAL_ACCESS_TIME);
        NetworkManager.getInstance().addToQueueAndWait(req);//execution te3 request
        return resultOk;

    }


    
    

}
