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
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entites.Joueurs;
import com.mycompany.services.ServiceJoueurs;
import com.sun.mail.smtp.SMTPTransport;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author lenovo
 */
public class AjoutJoueurs extends BaseForm {

    Form current;

    public AjoutJoueurs(Resources res) {

        setTitle("Ajouter un Joueur");
        setLayout(BoxLayout.y());

        TextField Nom = new TextField("", "Nom ", 20, TextArea.ANY);

        TextField Prenom = new TextField("", "Prenom", 20, TextArea.ANY);

        TextField Age = new TextField("", "Age", 200, TextArea.ANY);

        TextField Email = new TextField("", "Email", 20, TextArea.ANY);

        ComboBox ic = new ComboBox();
        ic.addItem("13");
        ic.addItem("14");
        ic.addItem("15");
        ic.addItem("16");
        ic.addItem("17");

        Button btnSubmit = new Button("Ajouter");
        Button cancel = new Button("Annuler ");
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((Nom.getText().length() == 0) || (Prenom.getText().length() == 0) || (Age.getText().length() == 0) || (ic.getSelectedItem().toString().length() == 0)) {
                    Dialog.show("Alert", "Veuillez remplir tous les champs ", new Command("OK"));
                } else {

                    InfiniteProgress ip = new InfiniteProgress();
                    final Dialog iDialog = ip.showInfiniteBlocking();
                    Joueurs t = new Joueurs(Nom.getText(), Prenom.getText(), Integer.parseInt(Age.getText()), Email.getText(), Integer.parseInt(ic.getSelectedItem().toString()));
                    if (ServiceJoueurs.getInstance().addJoueurs(t)) {
                        Dialog.show("Success", "Joueur ajouté ", new Command("OK"));
                        iDialog.dispose();
                        
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
                            msg.setRecipients(Message.RecipientType.TO, Email.getText().toString());
                            msg.setSubject("Welcome to the team!");
                            msg.setSentDate(new Date(System.currentTimeMillis()));
                            //String mdp = "2info2hamidou";
                            
                            
                            String txt = "Inscription terminer. Bienvenue monsieur : " + Nom.getText() + " " + Prenom.getText() + " " + Age.getText()+ "ans  "+Email.getText()+".";
                            msg.setText(txt);
                            SMTPTransport st = (SMTPTransport) session.getTransport("smtps");
                            st.connect("smtp.gmail.com",465, "issaouihamidou@gmail.com","2info2D3CGK");
                            st.sendMessage(msg, msg.getAllRecipients());
                            System.out.println("server response : " + st.getLastServerResponse());

                        } catch (Exception e) {
                            System.out.println(e);
                        }
                        refreshTheme();

                        new AfficherForm(current);

                    } else {
                        Dialog.show("ERROR", "Connection Failed", new Command("OK"));

                    }

                }
            }
        });
        cancel.addPointerPressedListener(l -> {

            Dialog dig = new Dialog("Anuuler");

            if (dig.show("Cancel", "Would you like to exit without submitting?, some data maybe lost", "Cancel", "OK")) {
                dig.dispose();
            } else {
                dig.dispose();
                new AfficherForm(current);
            };

        });

        addAll(Nom, Prenom, Age, Email, ic, btnSubmit, cancel);

    }



}
