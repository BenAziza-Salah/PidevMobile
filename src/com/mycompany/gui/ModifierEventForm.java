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
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.entites.Event;
import com.mycompany.services.ServiceEvent;
import java.util.Date;

/**
 *
 * @author yassin
 */
public class ModifierEventForm extends BaseForm {

    Form current;

    public ModifierEventForm(Resources res, Event rec) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        current = this;
        setToolbar(tb);
        getTitleArea().setUIID("container");
        setTitle("Ajouter Reclamation");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);
        //TextField Sujetrec = new TextField(rec.getSujetRec(), "Sujet", 20, TextField.ANY);
        TextField idCategorie = new TextField(rec.getCategoriesId(), "idCategorie", 20, TextField.ANY);
        TextField Nom = new TextField(String.valueOf(rec.getNom()), "Sujet", 20, TextField.ANY);
        TextField Description = new TextField(rec.getDescription(), "Description", 20, TextField.ANY);
        TextField Lieux = new TextField(String.valueOf(rec.getLieuEvent()), "Lieux", 20, TextField.ANY);
        String dateInString = rec.getDateEvent();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        Picker Date = new Picker();
        try {
            Date.setDate(format.parse(dateInString));
        } catch (ParseException ex) {
          //Logger.getLogger(ModifierEventForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        TextField Prix = new TextField(String.valueOf(rec.getPrix()), "Prix", 20, TextField.ANY);

        idCategorie.setUIID("NewsTopLine");
        idCategorie.setSingleLineTextArea(true);
        Nom.setUIID("NewsTopLine");
        Nom.setSingleLineTextArea(true);
        Description.setUIID("NewsTopLine");
        Description.setSingleLineTextArea(true);
        Lieux.setUIID("NewsTopLine");
        Lieux.setSingleLineTextArea(true);
        Date.setUIID("NewsTopLine");
        // Date.setSingleLineTextArea(true);
        Prix.setUIID("NewsTopLine");
        Prix.setSingleLineTextArea(true);

        /*   try {
         Date   date111 = new SimpleDateFormat("dd/MM/yyyy").parse(Date.getText());
         */
        Button btnModifier = new Button("Modifier");

        //Evenet onClick btnModifier
        btnModifier.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent l) {
                DateFormat ddd = new SimpleDateFormat("yyyy-MM-dd");
                Date ddebut = Date.getDate();
                String dated = ddd.format(ddebut);
                // rec.setSujetRec(Sujetrec.getText());
                rec.setCategoriesId(idCategorie.getText());
                rec.setNom(Nom.getText());
                rec.setDescription(Description.getText());
                rec.setLieuEvent(Lieux.getText());
                rec.setDateEvent(ddd.format(dated));
                rec.setPrix((float) Float.parseFloat(Prix.getText()));
                
               if (ServiceEvent.getInstance().ModifierEvent(rec)) {
                    new ListeEventForm(res).show();
                }
            }
        });

        Button btnAnnuler = new Button("Annuler");
        btnAnnuler.addActionListener(l -> {
            new ListeEventForm(res).show();
        });

        Label l1 = new Label();
        Label l2 = new Label();
        Label l3 = new Label();
        Label l4 = new Label();
        Label l5 = new Label();

        rec.setCategoriesId(idCategorie.getText());
        rec.setNom(Nom.getText());
        rec.setDescription(Description.getText());
        rec.setLieuEvent(Lieux.getText());
        rec.setDateEvent(Date.getText());
        rec.setPrix((float) Float.parseFloat(Prix.getText()));

        Container content = BoxLayout.encloseY(
                l1, l2,
                idCategorie,
                createLineSeparator(),
                Nom,
                createLineSeparator(),
                Description,
                createLineSeparator(),
                Lieux,
                createLineSeparator(),
                Date,
                createLineSeparator(),
                Prix,
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
