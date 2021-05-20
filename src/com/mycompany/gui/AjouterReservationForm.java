/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.entities.Reservation;
import com.mycompany.services.ServiceReservation;

/**
 *
 * @author Matoussi
 */
public class AjouterReservationForm extends BaseForm {

    Form current;

    public AjouterReservationForm(Form current) {
        setTitle("Ajouter un reservation");
        setLayout(BoxLayout.y());
        
        Picker date = new Picker();
        date.setType(Display.PICKER_TYPE_DATE);
        date.setUIID("TextFieldBlack");

        Picker heuredebut = new Picker();
        heuredebut.setType(Display.PICKER_TYPE_TIME);
       heuredebut.setUIID("TextFieldBlack");

        Picker heurefin = new Picker();
        heurefin.setType(Display.PICKER_TYPE_TIME);
        heurefin.setUIID("TextFieldBlack");

        TextField idterrain = new TextField("", "id du terrain", 20, TextArea.ANY);
        idterrain.setUIID("TextFieldBlack");

        Button ajouter = new Button("Ajouter");

        ajouter.addActionListener((ActionListener) (ActionEvent evt) -> {
            if ((idterrain.getText().length() == 0)) {
                Dialog.show("Alert", "Veuillez remplir tous les champs ", new Command("OK"));
                
            } else {
                InfiniteProgress ip = new InfiniteProgress();
                final Dialog iDialog = ip.showInfiniteBlocking();
                
                SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-DD");
                SimpleDateFormat hh = new SimpleDateFormat("HH:mm:ss");
//                String datee = dd.format( t.getDatedebut());
                
                Reservation r = new Reservation(dd.format(date.getText()), hh.format(heuredebut.getText()),
                        hh.format(heurefin.getText()), Integer.parseInt(idterrain.getText()));
                if (ServiceReservation.getInstance().ajoutReservation(r)) {
                    Dialog.show("Success", "Réservation ajouté ", new Command("OK"));
                    iDialog.dispose();
                    refreshTheme();
                } else {
                    Dialog.show("ERROR", "Server error", new Command("OK"));
                    iDialog.dispose();
                    refreshTheme();
                }
                
            }
       });
        addAll(date, heuredebut, heurefin, idterrain, ajouter);

    }
}
