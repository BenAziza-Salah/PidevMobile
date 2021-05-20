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
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Reservation;
import com.mycompany.entities.Terrain;
import com.mycompany.services.ServiceReservation;

/**
 *
 * @author OussKh
 */
public class ModifierReservationForm extends BaseForm {
Form current;
    ModifierReservationForm(Reservation r ) {
        setTitle("update theme");
        setLayout(BoxLayout.y());

        Picker date = new Picker();
        date.setType(Display.PICKER_TYPE_DATE);

        Picker heuredebut = new Picker();
        heuredebut.setType(Display.PICKER_TYPE_TIME);

        Picker heurefin = new Picker();
        heurefin.setType(Display.PICKER_TYPE_TIME);

        TextField idterrain = new TextField("", "id du terrain", 20, TextArea.ANY);
        idterrain.setUIID("TextFieldBlack");

        Button modifier = new Button("Modifier");
        modifier.addActionListener((ActionListener) (ActionEvent evt) -> {
            if ((idterrain.getText().length() == 0)) {
                Dialog.show("Alert", "Veuillez remplir tous les champs ", new Command("OK"));

            } else {
                InfiniteProgress ip = new InfiniteProgress();
                final Dialog iDialog = ip.showInfiniteBlocking();

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-DD");
                SimpleDateFormat formatH = new SimpleDateFormat("HH:mm:ss");

                Reservation re = new Reservation(format.format(date.getDate()), formatH.format(heuredebut.getTime()),
                        formatH.format(heurefin.getTime()), Integer.parseInt(idterrain.getText()));
                if (ServiceReservation.getInstance().ajoutReservation(re)) {
                    Dialog.show("Success", "Reservation modifiée ", new Command("OK"));
                    iDialog.dispose();
                    refreshTheme();
                } else {
                    Dialog.show("ERROR", "Server error", new Command("OK"));
                    iDialog.dispose();
                    refreshTheme();
                }

            }
        });
        addAll(date, heuredebut, heurefin, idterrain, modifier);
    }

}
