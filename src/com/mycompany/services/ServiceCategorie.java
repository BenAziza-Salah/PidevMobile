/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.ConnectionRequest;
import java.util.ArrayList;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
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
 * @author maram
 */
public class ServiceCategorie { public static ServiceCategorie instance = null;
    public static boolean resultOk = true;

    private ConnectionRequest req;

    public static ServiceCategorie getInstance() {
        if (instance == null) {
            instance = new ServiceCategorie();
        }
        return instance;
    }

    public ServiceCategorie() {
        req = new ConnectionRequest();
    }

    //Ajout
    public void addCategorie(Categorie categorie) throws Exception {

        String url = Statics.BASE_URL + "/categorie/addCategorie/ok?type=" + categorie.getType();
             
        System.out.println(url);
        req.setUrl(url);
        req.addResponseListener(e -> {
            String str = new String(req.getResponseData());//reponse jason 
            System.out.println("data ==> " + str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);//execution te3 request
    }

    //Affichage
    public ArrayList<Categorie> AffichageCategorie() {
        ArrayList<Categorie> result = new ArrayList<>();
        String url = Statics.BASE_URL + "/categorie/Affichage/ok";
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
                        Categorie re = new Categorie();

                        int idCategorie = (int) Float.parseFloat(obj.get("idCategorie").toString());
                       
                        String Type = obj.get("type").toString();
                     

                        /////////////////////////////://////////////////:///////:Lel Date tawww //////////////////://////////////////://////////////////:                       
                        re.setIdCategorie(idCategorie);
                        re.setType(Type);
                      
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

    //Delete Reclamation
    public boolean deleteCategorie(int id_categorie) {
        String url = Statics.BASE_URL + "/categorie/Suprimercategorie/ok?id_categorie=" + id_categorie;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                req.removeResponseListener(this);
            }
        });
         System.out.println(url);
        req.setUrl(url);
        req.addResponseListener(e -> {
            String str = new String(req.getResponseData());//reponse jason 
            System.out.println("data ==> " + str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);//execution te3 request
        return resultOk;
    }

    //Modifier Reclamation
    public boolean ModifierCategorie(Categorie Categorie) {
        String url = Statics.BASE_URL + "/categorie/updateCategorie/ok?id_categorie=" + Categorie.getIdCategorie()+"&type="+ Categorie.getType();
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
        });
        NetworkManager.getInstance().addToQueueAndWait(req);//execution te3 request
        return resultOk;

    }   
}
