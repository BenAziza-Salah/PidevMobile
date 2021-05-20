/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.InfiniteProgress;

import com.codename1.messaging.Message;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Terrain;
import com.mycompany.services.ServiceTerrain;
import com.sun.mail.smtp.SMTPTransport;
import java.sql.Date;
import java.util.Properties;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author OussKh
 */
public class AjouterTerrainForm extends BaseForm {

    Form current;

    public AjouterTerrainForm(Form current) {
        
        
        Toolbar.setGlobalToolbar(true);
        Form tempForm = new Form("Toolbar", new BoxLayout(BoxLayout.Y_AXIS));
        Toolbar tb = tempForm.getToolbar();
        Container topBar = BorderLayout.east(new Label(""));
        topBar.add(BorderLayout.SOUTH, new Label("Menu", "SidemenuTagline"));
        topBar.setUIID("SideCommand");
        tb.addComponentToSideMenu(topBar);

        tb.addMaterialCommandToSideMenu("Gestion terrain", FontImage.MATERIAL_HOME, e -> {
            new AfficherTerrainForm(current);
        });
        tb.addMaterialCommandToSideMenu("Reservation", FontImage.MATERIAL_HOME, e -> {
            new AfficherReservationForm(current);
        });
//        tb.addMaterialCommandToSideMenu("Theme", FontImage.MATERIAL_ADD_TASK, e -> {
//            new DisplayThemeForm(current).show();
//        });
//        tb.addMaterialCommandToSideMenu("Questions", FontImage.MATERIAL_LIST, e -> {
//            new TabQuestionForm(current);
//        });
//        tb.addMaterialCommandToSideMenu("Event", FontImage.MATERIAL_INFO, e -> {
//        });
       setTitle("Ajouter un terrain");
        setLayout(BoxLayout.y());
        
        TextField nomTerrain = new TextField("", "Nom du terrain", 20, TextArea.ANY);

        TextField adresse = new TextField("", "adresse", 20, TextArea.ANY);

        TextField description = new TextField("", "Description", 200, TextArea.ANY);

        TextField photo = new TextField("", "Url image", 20, TextArea.ANY);

        ComboBox etat = new ComboBox();
        etat.addItem("disponible ");
        etat.addItem("réservé");
        
        Button btnSubmit = new Button("Ajouter");
        Button cancel = new Button("Annuler ");
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
                                       
                        Dialog.show("Success", "Terrain ajouté ", new Command("OK"));
                        iDialog.dispose();
                        refreshTheme();
                        
                        //local notification api
                        LocalNotification ln = new LocalNotification();
                        ln.setId("LnMessage");
                        ln.setAlertTitle("Notice!");
                        ln.setAlertBody("A new Theme"+ t.getNomterrain()+ "has been added! Check it out");
                        ln.setAlertSound("/notification_sound_notif.mp3");
                        Display.getInstance().scheduleLocalNotification(ln, System.currentTimeMillis() + 10 * 1000, LocalNotification.REPEAT_NONE);
                        
                        //email sending
                        try {
                            Properties props = new Properties();
                            props.put("mail.transport.protocol", "smtp");
                            props.put("mail.smtps.host", "smtp.gmail.com");
                            //props.put("mail.smtps.port", "587");
                            props.put("mail.smtps.auth", "true");
                            //props.put("mail.smtp.starttls.enable", "true");
                            
                            
                            System.out.println("bech yhel essession");
                            
                            
                            Session session = Session.getInstance(props, null);
                            MimeMessage msg = new MimeMessage(session);
                            msg.setFrom(new InternetAddress("Email sent <monEmail@domain.com>"));
                            System.out.println("1111");
//                            msg.setRecipients(Message.RecipientType.TO, Email.getText().toString());
                            msg.setSubject("Welcome to the team!");
                            msg.setSentDate(new Date(System.currentTimeMillis()));
                            //String mdp = "2info2hamidou";
                            
                            
                            String txt = "bienvenue merci beaucoup d'être un membre de notre famille ";
                            msg.setText(txt);
                            SMTPTransport st = (SMTPTransport) session.getTransport("smtps");
                            st.connect("smtp.gmail.com",465, "oussema.khorchani@esprit.tn","203JMT1891");
                            st.sendMessage(msg, msg.getAllRecipients());
                            System.out.println("server response : " + st.getLastServerResponse());

                        } catch (Exception e) {
                            System.out.println(e);
                        }
                        //load after submuitting
                        new AfficherTerrainForm(current);

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
                new AfficherTerrainForm(current);
            };

        });

        addAll(nomTerrain, adresse,etat,description ,photo, btnSubmit, cancel);
    }

}
