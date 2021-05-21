/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Equipe;
import com.mycompany.entities.Terrain;
import com.mycompany.services.ServiceEquipe;

/**
 *
 * @author Matoussi
 */
public class ModifierEquipeForm extends BaseForm {
 Form current;
    ModifierEquipeForm(Equipe d) {
        setTitle("update theme");
        setLayout(BoxLayout.y());

        TextField tfID = new TextField(String.valueOf(d.getIdequipe()), "idequipe");
        tfID.setVisible(false);
        TextField nom = new TextField(d.getNom(), "Nom", 20, TextArea.ANY);
        TextField nombre = new TextField(String.valueOf(d.getNombre()), "Nombre", 20, TextArea.ANY);
       

     

        
        
        

        Button btnSubmit = new Button("Modifier");
        Button cancel = new Button("Annuler");
        btnSubmit.addActionListener(new ActionListener() {
             @Override
            public void actionPerformed(ActionEvent evt) {
               if ((nom.getText().length() == 0) || (nombre.getText().length() == 0) ) {
                    Dialog.show("Alert", "Veuillez remplir tous les champs ", new Command("OK"));
                } else {

                    InfiniteProgress ip = new InfiniteProgress();
                    final Dialog iDialog = ip.showInfiniteBlocking();
                    Equipe d = new Equipe(nom.getText(), Integer.parseInt((nombre.getText())));
                    if (ServiceEquipe.getInstance().editEquipe(d)) {
                          Dialog.show("Success", "Equipe modifié ", new Command("OK"));
                        iDialog.dispose();
                        refreshTheme();
                        
                        //local notification api
                        LocalNotification ln = new LocalNotification();
                        ln.setId("LnMessage");
                        ln.setAlertTitle("Notice!");
                        ln.setAlertBody("A new Theme"+ d.getNom()+ "has been added! Check it out");
                        ln.setAlertSound("/notification_sound_notif.mp3");
                        Display.getInstance().scheduleLocalNotification(ln, System.currentTimeMillis() + 10 * 1000, LocalNotification.REPEAT_NONE);
                        
                        //load after submuitting
                        new AfficherDemandeForm(current);

                    } else {
                        Dialog.show("ERROR", "Connection Failed", new Command("OK"));

                    }

                }
            }
        });
        cancel.addPointerPressedListener(l -> {

            Dialog dig = new Dialog("Cancel");

            if (dig.show("Cancel", "Would you like to exit without submitting?, some data maybe lost", "Cancel", "OK")) {
                dig.dispose();
            } else {
                dig.dispose();
                new AfficherEquipeForm(current);
            };

        });

        addAll(tfID,nom,nombre, btnSubmit,cancel);
    }

}




  