/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.FloatingHint;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.entites.Admin;
import com.mycompany.services.ServiceAdmin;
import java.util.Date;

/**
 *
 * @author yassin
 */
public class ModifierAdmin extends BaseForm {

    Form current;

    public ModifierAdmin(Resources res, Admin rec) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        current = this;
        setToolbar(tb);
        getTitleArea().setUIID("container");
        setTitle("Ajouter Reclamation");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);
        //TextField Sujetrec = new TextField(rec.getSujetRec(), "Sujet", 20, TextField.ANY);
        TextField idAdmin = new TextField(String.valueOf(rec.getId()), "id", 20, TextField.ANY);
        TextField nom = new TextField(String.valueOf(rec.getNom()), "Nom", 20, TextField.ANY);
        TextField prenom = new TextField(rec.getPrenom(), "Prenom", 20, TextField.ANY);
        TextField username = new TextField(String.valueOf(rec.getUsername()), "Lieux", 20, TextField.ANY);
        TextField email = new TextField(String.valueOf(rec.getEmail()), "Lieux", 20, TextField.ANY);
       
  
      
        idAdmin.setUIID("NewsTopLine");
        idAdmin.setSingleLineTextArea(true);
        nom.setUIID("NewsTopLine");
        nom.setSingleLineTextArea(true);
        prenom.setUIID("NewsTopLine");
        prenom.setSingleLineTextArea(true);
        username.setUIID("NewsTopLine");
        username.setSingleLineTextArea(true);
        email.setUIID("NewsTopLine");
        email.setSingleLineTextArea(true);

        /*   try {
         Date   date111 = new SimpleDateFormat("dd/MM/yyyy").parse(Date.getText());
         */
        Button btnModifier = new Button("Modifier");

        //Evenet onClick btnModifier
        btnModifier.addPointerPressedListener(l -> {
         
            rec.setId(Integer.parseInt(idAdmin.getText()));
            rec.setNom(nom.getText());
            rec.setPrenom(prenom.getText());
            rec.setUsername(username.getText());
            rec.setEmail(email.getText());
           

            if (ServiceAdmin.getInstance().ModifierAdmin(rec)) {
                new AfficherAdmin(res).show();
            }

        });

        Button btnAnnuler = new Button("Annuler");
        btnAnnuler.addActionListener(l -> {
            new AfficherAdmin(res).show();
        });

        Label l1 = new Label();
        Label l2 = new Label();
        Label l3 = new Label();
        Label l4 = new Label();
        Label l5 = new Label();

            rec.setId(Integer.parseInt(idAdmin.getText()));
            rec.setNom(nom.getText());
            rec.setPrenom(prenom.getText());
            rec.setUsername(username.getText());
            rec.setEmail(email.getText());

        Container content = BoxLayout.encloseY(
                l1, l2,
                idAdmin,
                createLineSeparator(),
                nom,
                createLineSeparator(),
                prenom,
                createLineSeparator(),
                username,
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
