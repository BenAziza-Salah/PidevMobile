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
import com.mycompany.entites.Categorie;
import com.mycompany.entites.Event;
import com.mycompany.services.ServiceCategorie;
import com.mycompany.services.ServiceEvent;
import java.util.Date;

/**
 *
 * @author yassin
 */
public class ModifierCategorieForm extends BaseForm {

    Form current;

    public ModifierCategorieForm(Resources res, Categorie rec) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        current = this;
        setToolbar(tb);
        getTitleArea().setUIID("container");
        setTitle("Ajouter Reclamation");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);
        //TextField Sujetrec = new TextField(rec.getSujetRec(), "Sujet", 20, TextField.ANY);
       
        TextField Type = new TextField(String.valueOf(rec.getType()), "Type", 20, TextField.ANY);
       

        
       Type.setUIID("NewsTopLine");
        Type.setSingleLineTextArea(true);
       
        Button btnModifier = new Button("Modifier");

        //Evenet onClick btnModifier
        btnModifier.addPointerPressedListener(l -> {
         
            // rec.setSujetRec(Sujetrec.getText());
           
            rec.setType(Type.getText());
            

            if (ServiceCategorie.getInstance().ModifierCategorie(rec)) {
                new ListeCategorieForm(res).show();
            }

        });

        Button btnAnnuler = new Button("Annuler");
        btnAnnuler.addActionListener(l -> {
            new ListeCategorieForm(res).show();
        });

        Label l1 = new Label();
        Label l2 = new Label();
       

        
        rec.setType(Type.getText());
       
        Container content = BoxLayout.encloseY(
                l1, l2,
              
              Type,
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
