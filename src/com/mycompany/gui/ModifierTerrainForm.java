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
import com.mycompany.entities.Terrain;
import com.mycompany.services.ServiceTerrain;

/**
 *
 * @author Matoussi
 */
public class ModifierTerrainForm extends BaseForm {
 Form current;
    ModifierTerrainForm(Terrain t) {
        setTitle("update theme");
        setLayout(BoxLayout.y());

        TextField tfID = new TextField(String.valueOf(t.getIdterrain()), "ID du terrain");
        tfID.setVisible(false);
        TextField nomTerrain = new TextField(t.getNomterrain(), "Nom du terrain", 20, TextArea.ANY);

        TextField adresse = new TextField(t.getAdresse(), "adresse", 20, TextArea.ANY);

        TextField description = new TextField(t.getDescription(), "Description", 200, TextArea.ANY);

        TextField photo = new TextField(t.getPhoto(), "Url image", 20, TextArea.ANY);
        
        ComboBox etat = new ComboBox();
        etat.addItem("disponible ");
        etat.addItem("réservé");

        Button btnSubmit = new Button("Modifier");
        Button cancel = new Button("Annuler");
        btnSubmit.addActionListener(new ActionListener() {
             @Override
            public void actionPerformed(ActionEvent evt) {
               if ((nomTerrain.getText().length() == 0) || (adresse.getText().length() == 0) || (description.getText().length() == 0) || (etat.getSelectedItem().toString().length() == 0)) {
                    Dialog.show("Alert", "Veuillez remplir tous les champs ", new Command("OK"));
                } else {

                    InfiniteProgress ip = new InfiniteProgress();
                    final Dialog iDialog = ip.showInfiniteBlocking();
                    Terrain t = new Terrain(nomTerrain.getText(), adresse.getText(), etat.getSelectedItem().toString(), description.getText(), photo.getText());
                    if (ServiceTerrain.getInstance().ajoutTerrain(t)) {
                          Dialog.show("Success", "Terrain modifié ", new Command("OK"));
                        iDialog.dispose();
                        refreshTheme();
                        
                        //local notification api
                        LocalNotification ln = new LocalNotification();
                        ln.setId("LnMessage");
                        ln.setAlertTitle("Notice!");
                        ln.setAlertBody("A new Theme"+ t.getNomterrain()+ "has been added! Check it out");
                        ln.setAlertSound("/notification_sound_notif.mp3");
                        Display.getInstance().scheduleLocalNotification(ln, System.currentTimeMillis() + 10 * 1000, LocalNotification.REPEAT_NONE);
                        
                        //load after submuitting
                        new AfficherTerrainForm(current);

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
                new AfficherTerrainForm(current);
            };

        });

        addAll(tfID, nomTerrain, adresse,etat,description ,photo, btnSubmit,cancel);
    }

}




  