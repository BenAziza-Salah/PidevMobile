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
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycompany.entites.Admin;
import com.mycompany.gui.AfficherAdmin;
import com.mycompany.gui.AjouterAdmin;
import com.mycompany.gui.AjouterClient;

import com.mycompany.gui.ModifierAdmin;
import com.mycompany.gui.ProfileForm;
import com.mycompany.gui.WalkthruForm;

import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author SALAH
 */
public class ServiceAdmin {
    
    public static ServiceAdmin instance = null ;
    private ConnectionRequest req;
    public boolean resultOK;
    public String result = "";
    public ArrayList<Admin> list;
      String json;
    
    public static ServiceAdmin getInstance(){
        if (instance == null)
        {
            instance = new ServiceAdmin();
        }
        return instance;
    }
    
    public ServiceAdmin (){
        req = new ConnectionRequest();
    }  
        //Affichage
    public ArrayList<Admin> AffichageAdmin() {
        ArrayList<Admin> result = new ArrayList<>();
        String url = Statics.BASE_URL + "/admin/liste/json";
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
                        Admin re = new Admin();

                        int id = (int) Float.parseFloat(obj.get("id").toString());
                        String nom = obj.get("nom").toString();
                        String prenom = obj.get("prenom").toString();
                        String username = obj.get("username").toString();
                        String email = obj.get("email").toString();
                        


                        /////////////////////////////://////////////////:///////:Lel Date tawww //////////////////://////////////////://////////////////:                       
                        re.setId(id);
                        re.setNom(nom);
                        re.setPrenom(prenom);
                        re.setUsername(username);
                        re.setEmail(email);
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
    
    //**************
    public boolean AjoutAdmin (Admin admin)
    {
        String url =Statics.BASE_URL +"/admin/add/json"+ "?nom=" + admin.getNom() + "&prenom=" + admin.getPrenom() + "&username=" + admin.getUsername()
                + "&email=" + admin.getEmail() + "&mdp=" +admin.getMdp();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
        ToastBar.showMessage("Admin est ajoutée", FontImage.MATERIAL_ACCESS_TIME);
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
        
    }
    
        //Delete Reclamation
    public boolean DeleteAdmin(int id) {
        String url = Statics.BASE_URL + "/admin/delete/json?id="+id;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                req.removeResponseListener(this);
            }
        });
        ToastBar.showMessage("Admin est supprimé", FontImage.MATERIAL_ACCESS_TIME);
        NetworkManager.getInstance().addToQueueAndWait(req);//execution te3 request
        return resultOK;
    }

        
    //Modifier 
    public boolean ModifierAdmin(Admin admin) {
         String url = Statics.BASE_URL + "/admin/update/json?id=" + admin.getId() + "&nom=" + admin.getNom() + "&prenom=" + admin.getPrenom() + "&username=" + admin.getUsername()
                + "&email=" + admin.getEmail() + "&mdp=" +admin.getMdp(); //création de l'UR
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK= req.getResponseCode() == 200; //code success  http 200 ok
                req.removeResponseListener(this);
            }
        });
        System.out.println(url);
        req.setUrl(url);
        req.addResponseListener(e -> {
            String str = new String(req.getResponseData());//reponse jason 
            System.out.println("data ==> " + str);
        });
        ToastBar.showMessage("Admin est modifié", FontImage.MATERIAL_ACCESS_TIME);
        NetworkManager.getInstance().addToQueueAndWait(req);//execution te3 request
        return resultOK;

    }

        
        
        
      public String getPasswordByEmail(String email,Resources rs){
            String url = Statics.BASE_URL+"/user/getPasswordByEmail?email="+email;
            req = new ConnectionRequest(url,false); // false url mazel matabaatech l server 
            req.setUrl(url);
            req.addResponseListener((e) ->{
                
                JSONParser j = new JSONParser();
                
                String json = new String(req.getResponseData()) + "";
                
                try{
                    
                    System.out.println("data =="+json);
                        Map<String,Object> password = j.parseJSON(new CharArrayReader(json.toCharArray()));
                        
                 
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            
            });
            
            NetworkManager.getInstance().addToQueueAndWait(req);
            return json;
        }
          public boolean getByEmail(String email){
            
            String url = Statics.BASE_URL+"/user/getEmailAdmin?email="+email;
          //  req = new ConnectionRequest(url,false); // false url mazel matabaatech l server 
           // req.setUrl(url);
            if (email.equals(email))
            {
                return true;
            }
            
            return false;
             
           
               
           
         
        }
         public void signin(TextField username,TextField password,Resources rs){
            String url = Statics.BASE_URL+"/user/signin?email="+username.getText().toString()+"&mdp="+password.getText().toString();
            req = new ConnectionRequest(url,false); // false url mazel matabaatech l server 
            req.setUrl(url);
            req.addResponseListener((e) ->{
                
                JSONParser j = new JSONParser();
                
                 json = new String(req.getResponseData()) + "";
                
                try{
                    if(json.equals("failed")) {
                       // Dialog.show("Echec d'authentification","Username ou mot de passe eronné","OK",null);
                       ServiceClient.getInstance().signin(username, password, rs);
                    }
                    else {
                        System.out.println("data =="+json);
                        Map<String,Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
                        
                        if(user.size() > 0) //l9a user 
                            new ProfileForm(rs).show();//yemchi l list experience
                    }
                    
                }catch(Exception ex){
                    ex.printStackTrace();
                }
           
            });
        NetworkManager.getInstance().addToQueueAndWait(req);
        }
    
}
