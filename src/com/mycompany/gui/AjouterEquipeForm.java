/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ToastBar;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
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
 * @author OussKh
 */
public class AjouterEquipeForm extends BaseForm {

    Form current;

    public AjouterEquipeForm(Form current) {
        setTitle("Ajouter Equipe");
        setLayout(BoxLayout.y());
       
        
        TextField nom = new TextField("", "nom", 20, TextArea.ANY);

        TextField nombre = new TextField("", "nombre", 20, TextArea.ANY);

        

     
        
        Button btnSubmit = new Button("Ajouter");
        Button cancel = new Button("Annuler ");
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               if ((nom.getText().length() == 0) || (nombre.getText().length() == 0)  ) {
                    Dialog.show("Alert", "Veuillez remplir tous les champs ", new Command("OK"));
                } else {

                    InfiniteProgress ip = new InfiniteProgress();
                    final Dialog iDialog = ip.showInfiniteBlocking();
                    Equipe d = new Equipe(nom.getText(),Integer.parseInt(nombre.getText()));
                    if (ServiceEquipe.getInstance().ajoutEquipe(d)) {
                                       
                        Dialog.show("Success", "equipe ajouté ", new Command("OK"));
                        iDialog.dispose();
                        ToastBar.showMessage("Nouvelle Equipe : \n" + "equipe ajoutée avec succes", FontImage.MATERIAL_INFO);
                        refreshTheme();
                        
                        //local notification api
                        LocalNotification ln = new LocalNotification();
                        ln.setId("LnMessage");
                        ln.setAlertTitle("Notice!");
                        ln.setAlertBody("A new Theme"+ d.getNom()+ "has been added! Check it out");
                        ln.setAlertSound("/notification_sound_notif.mp3");
                        Display.getInstance().scheduleLocalNotification(ln, System.currentTimeMillis() + 10 * 1000, LocalNotification.REPEAT_NONE);
                        
                        //load after submuitting
                        new AfficherEquipeForm(current);

                    } else {
                        Dialog.show("ERROR", "Connection Failed", new Command("OK"));

                    }

                }
            }
        });
        cancel.addPointerPressedListener(l -> {

            Dialog dig = new Dialog("Anuuler");

            if (dig.show("Cancel", "Souhaitez-vous quitter sans soumettre?, Certaines données peuvent être perdues !", "Cancel", "OK")) {
                dig.dispose();
            } else {
                dig.dispose();
                new AfficherEquipeForm(current);
            };

        });

        addAll(nom, nombre, btnSubmit, cancel);
    }

}
