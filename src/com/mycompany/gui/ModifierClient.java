/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
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
public class ModifierClient extends BaseForm {
     
        Form current;

    public ModifierClient(Resources res, Client rec) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        current = this;
        setToolbar(tb);
        getTitleArea().setUIID("container");
        setTitle("Ajouter Reclamation");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);
        //TextField Sujetrec = new TextField(rec.getSujetRec(), "Sujet", 20, TextField.ANY);
        TextField idClient = new TextField(String.valueOf(rec.getIdc()), "idc", 20, TextField.ANY);
        TextField nom = new TextField(String.valueOf(rec.getNom()), "Nom", 20, TextField.ANY);
        TextField prenom = new TextField(rec.getPrenom(), "Prenom", 20, TextField.ANY);
        TextField address = new TextField(rec.getAddress(), "address", 20, TextField.ANY);
        TextField numtel = new TextField(String.valueOf(rec.getNumtelc()), "numtelc", 20, TextField.ANY);
        TextField email = new TextField(String.valueOf(rec.getEmail()), "Lieux", 20, TextField.ANY);
       
  
      
        idClient.setUIID("NewsTopLine");
        idClient.setSingleLineTextArea(true);
        nom.setUIID("NewsTopLine");
        nom.setSingleLineTextArea(true);
        prenom.setUIID("NewsTopLine");
        prenom.setSingleLineTextArea(true);
        address.setUIID("NewsTopLine");
        address.setSingleLineTextArea(true);
        numtel.setUIID("NewsTopLine");
        numtel.setSingleLineTextArea(true);
        email.setUIID("NewsTopLine");
        email.setSingleLineTextArea(true);

        /*   try {
         Date   date111 = new SimpleDateFormat("dd/MM/yyyy").parse(Date.getText());
         */
        Button btnModifier = new Button("Modifier");

        //Evenet onClick btnModifier
        btnModifier.addPointerPressedListener(l -> {
         
            rec.setIdc(Integer.parseInt(idClient.getText()));
            rec.setNom(nom.getText());
            rec.setPrenom(prenom.getText());
            rec.setAddress(address.getText());
            rec.setNumtelc(Integer.parseInt(numtel.getText()));
            rec.setEmail(email.getText());
           

            if (ServiceClient.getInstance().ModifierClient(rec)) {
                new AfficherClient(res).show();
            }

        });

        Button btnAnnuler = new Button("Annuler");
        btnAnnuler.addActionListener(l -> {
            new AfficherClient(res).show();
        });

        Label l1 = new Label();
        Label l2 = new Label();
        Label l3 = new Label();
        Label l4 = new Label();
        Label l5 = new Label();
        Label l6 = new Label();

            rec.setIdc(Integer.parseInt(idClient.getText()));
            rec.setNom(nom.getText());
            rec.setPrenom(prenom.getText());
            rec.setAddress(address.getText());
            rec.setNumtelc(Integer.parseInt(numtel.getText()));
            rec.setEmail(email.getText());

        Container content = BoxLayout.encloseY(
                l1, l2,
                idClient,
                createLineSeparator(),
                nom,
                createLineSeparator(),
                prenom,
                createLineSeparator(),
                address,
                createLineSeparator(),
                numtel,
                createLineSeparator(),
                email,
                createLineSeparator(),
                btnModifier,
                btnAnnuler
        );
        add(content);
        show();

        /*    } catch (ParseException ex) {
           ex.getStackTrace()  ;    
                   }*/
    
    }
}
