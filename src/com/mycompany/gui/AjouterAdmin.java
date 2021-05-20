/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entites.Admin;
import com.mycompany.services.ServiceAdmin;


/**
 *
 * @author SALAH
 */
public class AjouterAdmin extends BaseForm {
    
     Form current ;
    
       public AjouterAdmin(Resources res ) {
        super("Ajouter Admin", BoxLayout.y());

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
           
           TextField username = new TextField("", "Username", 200, TextArea.ANY);
           username.setUIID("TextFieldBlack");
          // addStringValue("Description" ,description);
           
           TextField email = new TextField("", "Email", 20, TextArea.ANY);
           email.setUIID("TextFieldBlack");
           
           TextField mdp = new TextField("", "Mot de Passe ", 20, TextArea.PASSWORD);
           mdp.setUIID("TextFieldBlack");
         
          
           
            Button ajouter = new Button("Ajouter Admin");
            
            ajouter.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {

                   if ((nom.getText().length() == 0) || (prenom.getText().length() == 0) || (username.getText().length() == 0) || (email.getText().length() == 0) || (mdp.getText().length() == 0)) {
                       Dialog.show("Alert", "Veuillez remplir tous les champs ", new Command("OK"));

                   } else {
                       InfiniteProgress ip= new InfiniteProgress();
                       final Dialog iDialog =ip.showInfiniteBlocking();
                       Admin t = new Admin(nom.getText(), prenom.getText(), username.getText(), email.getText(), mdp.getText());
                       if (ServiceAdmin.getInstance().AjoutAdmin(t)) {
                           Dialog.show("Success", "Admin ajouté ", new Command("OK"));
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
             addAll(nom, prenom, username, email,mdp, ajouter);
                                              
       
    }
    
}
