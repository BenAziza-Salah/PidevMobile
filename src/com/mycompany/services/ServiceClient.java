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
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycompany.entites.Admin;
import com.mycompany.entites.Client;
import com.mycompany.gui.AjouterClient;
import com.mycompany.gui.ProfileForm;
import com.mycompany.utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author SALAH
 */
public class ServiceClient {
    
        
    public static ServiceClient instance = null ;
    private ConnectionRequest req;
    public boolean resultOK;
    public String result = "";
    public ArrayList<Client> list;
      String json;
    
    public static ServiceClient getInstance(){
        if (instance == null)
        {
            instance = new ServiceClient();
        }
        return instance;
    }
    
    public ServiceClient (){
        req = new ConnectionRequest();
    }
    
    
            //Affichage
    public ArrayList<Client> AffichageClient() {
        ArrayList<Client> result = new ArrayList<>();
        String url = Statics.BASE_URL + "/client/liste/json";
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
                        Client re = new Client();

                        int id = (int) Float.parseFloat(obj.get("idc").toString());
                        String nom = obj.get("nom").toString();
                        String prenom = obj.get("prenom").toString();
                        String address = obj.get("address").toString();
                        int numtel =(int) Float.parseFloat(obj.get("numtelc").toString());
                        String email = obj.get("email").toString();
                        


                        /////////////////////////////://////////////////:///////:Lel Date tawww //////////////////://////////////////://////////////////:                       
                        re.setIdc(id);
                        re.setNom(nom);
                        re.setPrenom(prenom);
                        re.setAddress(address);
                        re.setNumtelc(numtel);
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
    public boolean AjoutClient (Client admin)
    {
        String url =Statics.BASE_URL +"/client/add/json"+ "?nom=" + admin.getNom() + "&prenom=" + admin.getPrenom() + "&address=" + admin.getAddress()
                + "&numtelc=" + admin.getNumtelc()+ "&email=" + admin.getEmail() + "&mdp=" +admin.getMdp();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
        ToastBar.showMessage("Client est ajoutée", FontImage.MATERIAL_ACCESS_TIME);
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
        //Delete Reclamation
    public boolean DeleteClient(int id) {
        String url = Statics.BASE_URL + "/client/delete/json?idc="+id;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                req.removeResponseListener(this);
            }
        });
        ToastBar.showMessage("Admin est supprimée", FontImage.MATERIAL_ACCESS_TIME);
        NetworkManager.getInstance().addToQueueAndWait(req);//execution te3 request
        return resultOK;
    }

        
    //Modifier 
    public boolean ModifierClient(Client admin) {
         String url = Statics.BASE_URL + "/client/update/json?idc=" + admin.getIdc() + "&nom=" + admin.getNom() + "&prenom=" + admin.getPrenom() + "&address=" + admin.getAddress()
                +"&numtelc=" + admin.getNumtelc()+ "&email=" + admin.getEmail(); //création de l'UR
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
        ToastBar.showMessage("Admin est Modifié", FontImage.MATERIAL_ACCESS_TIME);
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
    
            public void signin(TextField username,TextField password,Resources rs){
            String url = Statics.BASE_URL+"/user/client?email="+username.getText().toString()+"&mdp="+password.getText().toString();
            req = new ConnectionRequest(url,false); // false url mazel matabaatech l server 
            req.setUrl(url);
            req.addResponseListener((e) ->{
                
                JSONParser j = new JSONParser();
                
                 json = new String(req.getResponseData()) + "";
                
                try{
                    if(json.equals("failed")) {
                        Dialog.show("Echec d'authentification","Username ou mot de passe eronné","OK",null);
                    }
                    else {
                        System.out.println("data =="+json);
                        Map<String,Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
                        
                        if(user.size() > 0) //l9a user 
                            new AjouterClient(rs).show();//yemchi l list experience
                    }
                    
                }catch(Exception ex){
                    ex.printStackTrace();
                }
           
            });
        NetworkManager.getInstance().addToQueueAndWait(req);
        }
    
    
    
    
    
        public void signup(TextField nom,TextField prenom,TextField adresse,TextField numtel,TextField email,TextField password,TextField confirmPassword,Resources res){
        
       
        
        String url = Statics.BASE_URL+"/user/signup?nom="+nom.getText().toString()+"&prenom="+prenom.getText().toString()+"&address="+adresse.getText().toString()+
                "&numtelc="+numtel.getText().toString()+"&email="+email.getText().toString()+"&mdp="+password.getText().toString();
        
        req.setUrl(url);
        
        //controler saisie
        if(nom.getText().equals(" ")  && prenom.getText().equals(" ")  &&adresse.getText().equals(" ")  && numtel.getText().equals(" ")  &&
                password.getText().equals(" ") && email.getText().equals(" ")) {
            
            Dialog.show("Erreur","Veuillez remplir les champs","ok",null);
        }
        //hethy wa9t tsir execution ta3 url
        req.addResponseListener((e)-> {
            
            // njib data ly7atithom fi form
            byte[]data = (byte[]) e.getMetaData(); //lazm awl haja nhathrhom fe meta dat ya3ni nekhdou id ta3 kol textfield
            String responseData = new String(data);//va3dika nakhou content
            
            System.out.println("data ===>"+responseData);
            
            
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
         }
        
            public boolean getByEmail(String email){
            
            String url = Statics.BASE_URL+"/user/getEmailClient?email="+email;
            req = new ConnectionRequest(url,false); // false url mazel matabaatech l server 
            req.setUrl(url);
            if (email.equals(email))
            {
                return true;
            }else
            {
                return false;
            } 
            }
    
}
