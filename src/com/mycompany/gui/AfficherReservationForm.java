/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Reservation;
import com.mycompany.services.ServiceReservation;
import java.util.ArrayList;

/**
 *
 * @author OussKh
 */
public class AfficherReservationForm {
     Form current;
    Resources res;

    public AfficherReservationForm(Form previous ) {

        current = createForm();

    }

    public Form createForm() {
//        Toolbar tb = new Toolbar(true);
//        Toolbar.setGlobalToolbar(true);
//        setToolbar(tb);
//        getTitleArea().setUIID("Container");
//        getContentPane().setScrollVisible(false);
        Form tempForm = new Form("Toolbar", new BoxLayout(BoxLayout.Y_AXIS));
      
     Toolbar.setGlobalToolbar(true);
//        Form tempForm = new Form("Toolbar", new BoxLayout(BoxLayout.Y_AXIS));
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
        
        tempForm.setTitle("Reservation");
        tempForm.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        tempForm.setTransitionOutAnimator(CommonTransitions.createEmpty());
        GridLayout gridLayout = new GridLayout(1, 5);

        SpanLabel labelt = new SpanLabel("Date");
        SpanLabel labeldesc = new SpanLabel("heuredebut");
        SpanLabel labelde = new SpanLabel("heurefin");
        SpanLabel labelup = new SpanLabel("id terrain");

        Container HeadConainter = new Container(gridLayout);
        HeadConainter.add(labelt);
        HeadConainter.add(labeldesc);
        HeadConainter.add(labelde);
        HeadConainter.add(labelup);
        tempForm.add(HeadConainter);


        ArrayList<Reservation> themes = ServiceReservation.getInstance().getAllReservation();
        for (Reservation r : themes) {
            //delete button
            Label lSupprimer = new Label(" ");
            lSupprimer.setUIID("NewsTopLine");
            Style supprimerStyle = new Style(lSupprimer.getUnselectedStyle());
            supprimerStyle.setFgColor(0xf21f1f);
            FontImage supprimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprimerStyle);
            lSupprimer.setIcon(supprimerImage);
            lSupprimer.setTextPosition(RIGHT);
            //click delete icon
            lSupprimer.addPointerPressedListener(l -> {

                Dialog dig = new Dialog("supprimer");

                if (dig.show("supprimer", "would you liike to delete this Theme? this action will not be reverted", "Cancel", "OK")) {
                    dig.dispose();
                } else {
                    dig.dispose();
                    // appel de la fonction delete du service Experience
                    if (ServiceReservation.getInstance().supprimerReservation(r)) {

                        new AfficherReservationForm(current);
                    }
                }

            });

            //update Button
            Label lModifier = new Label(" ");
            lModifier.setUIID("NewsTopLine");
            Style modifierStyle = new Style(lModifier.getUnselectedStyle());
            modifierStyle.setFgColor(0xf7ad02);
            FontImage mFontImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, modifierStyle);
            lModifier.setIcon(mFontImage);
            lModifier.setTextPosition(LEFT);
            //click update button
            lModifier.addPointerPressedListener(l -> {
                new ModifierReservationForm(r).show();
            });

            Container BodyConainter = new Container(gridLayout);
            BodyConainter.add(new Label(r.getDate()));
            BodyConainter.add(new Label(r.getHeuredebut()));
            BodyConainter.add(new Label(r.getHeurefin()));
//            String description = String.valueOf(t.getDescription());
//            BodyConainter.add(new Label(description));
            BodyConainter.add(lSupprimer);
            BodyConainter.add(lModifier);

            tempForm.add(BodyConainter);

        }

        //recherche
        tempForm.getToolbar().addSearchCommand(e -> {
            String text = (String) e.getSource();
            if (text == null || text.length() == 0) {
                // clear search
                for (Component cmp : tempForm.getContentPane()) {
                    cmp.setHidden(false);
                    cmp.setVisible(true);
                }
                tempForm.getContentPane().animateLayout(150);
            } else {
                text = text.toLowerCase();
                for (Component cmp : tempForm.getContentPane()) {
                    Container mb = (Container) cmp;
                    if (!(mb.getComponentAt(0) instanceof Button)) {
                        Label label1 = (Label) mb.getComponentAt(0);
                        String line1 = label1.getText();
                        Label label2 = (Label) mb.getComponentAt(1);
                        String line2 = label2.getText();
                        Label label3 = (Label) mb.getComponentAt(2);
                        String line3 = label3.getText();
                        Label label4 = (Label) mb.getComponentAt(3);
                        String line4 = label4.getText();
                        boolean show = line1 != null && line1.toLowerCase().indexOf(text) > -1
                                || line2 != null && line2.toLowerCase().indexOf(text) > -1
                                || line3 != null && line3.toLowerCase().indexOf(text) > -1
                                || line4 != null && line4.toLowerCase().indexOf(text) > -1;
                        mb.setHidden(!show);
                        mb.setVisible(show);
                    }
                }
                tempForm.getContentPane().animateLayout(150);

            }
        }, 4);
        Button Addnew = new Button("Add new");
        Addnew.addActionListener(e -> new AjouterReservationForm(current).show());
        tempForm.add(Addnew);
        tempForm.show();
        tempForm.setTransitionOutAnimator(CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, false, 250));
        tempForm.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                e -> new BaseForm().showBack()); // Revenir vers l'interface précédente
        return tempForm;
        
        
       
    }


}
