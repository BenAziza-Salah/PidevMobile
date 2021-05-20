/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entites.Admin;
import com.mycompany.entites.Client;
import com.mycompany.services.ServiceAdmin;
import com.mycompany.services.ServiceClient;

/**
 *
 * @author SALAH
 */
public class AjouterClient extends BaseForm{
    
        
     Form current ;
    
       public AjouterClient(Resources res) {
        super("Ajouter Client", BoxLayout.y());

        Toolbar tb = new Toolbar(true);
        current = this;
//        setToolbar(tb);
//        getTitleArea().setUIID("container");
//        setTitle("Ajouter un terrain");
//        getContentPane().setScrollVisible(false);
        
           TextField nom= new TextField("", "Nom", 20, TextArea.ANY);
           nom.setUIID("TextFieldBlack");
           //addStringValue("Nom du terrain" ,nomTerrain);
           
           TextField prenom = new TextField("", "Prenom", 20, TextArea.ANY);
           prenom.setUIID("TextFieldBlack");
          // addStringValue("Adrresse" ,adresse);
           
           TextField address = new TextField("", "Adresse", 200, TextArea.ANY);
           address.setUIID("TextFieldBlack");
          // addStringValue("Description" ,description);
           TextField numtel = new TextField("", "Numero de Telephone", 20, TextArea.ANY);
           address.setUIID("TextFieldBlack");
           
           TextField email = new TextField("", "Email", 20, TextArea.ANY);
           email.setUIID("TextFieldBlack");
           
           TextField mdp = new TextField("", "Mot de Passe ", 20, TextArea.PASSWORD);
           mdp.setUIID("TextFieldBlack");
           
         
           
          
           
            Button ajouter = new Button("Ajouter Client");
            
            ajouter.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {

                   if ((nom.getText().length() == 0) || (prenom.getText().length() == 0) || (address.getText().length() == 0) || (email.getText().length() == 0) || (mdp.getText().length() == 0)) {
                       Dialog.show("Alert", "Veuillez remplir tous les champs ", new Command("OK"));

                   } else {
                       InfiniteProgress ip= new InfiniteProgress();
                       final Dialog iDialog =ip.showInfiniteBlocking();
                       Client t = new Client(nom.getText(), prenom.getText(), address.getText(),(int) Integer.parseInt(numtel.getText()), email.getText(), mdp.getText());
                       if (ServiceClient.getInstance().AjoutClient(t)) {
                           Dialog.show("Success", "Client ajouté ", new Command("OK"));
                           iDialog.dispose();
                           refreshTheme();
                       } else {
                           Dialog.show("ERROR", "Server error", new Command("OK"));
                           iDialog.dispose();
                           refreshTheme();
                       }
                       

                   }
               }
           });
             addAll(nom, prenom, address,numtel, email,mdp, ajouter);

       
    }
    
}
