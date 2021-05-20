/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.MultiButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Terrain;
import com.mycompany.gui.BaseForm;
import com.mycompany.services.ServiceTerrain;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.mycompany.gui.SideMenuBaseForm;
import java.util.ArrayList;

/**
 *
 * @author OussKh
 */
public class AfficherTerrainForm extends BaseForm {

    Form current;
    Resources res;

    public AfficherTerrainForm(Form previous ) {

        current = createForm();

    }

    public Form createForm() {
//        Toolbar tb = new Toolbar(true);
//        Toolbar.setGlobalToolbar(true);
        
//        setToolbar(tb);
        getTitleArea().setUIID("Container");
        getContentPane().setScrollVisible(false);
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
//        super.addSideMenu(res);
//        tb.addSearchCommand(e -> {});
//          Image img = res.getImage("profile-background.jpg");
//        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
//            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
//        }
//        ScaleImageLabel sl = new ScaleImageLabel(img);
//        sl.setUIID("BottomPad");
//        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        tempForm.setTitle("Terrain");
        tempForm.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        tempForm.setTransitionOutAnimator(CommonTransitions.createEmpty());
        GridLayout gridLayout = new GridLayout(1, 5);

        SpanLabel labelt = new SpanLabel("Nom du terrain");
        SpanLabel labeldesc = new SpanLabel("adresse");
        SpanLabel labelde = new SpanLabel("etat");
//        SpanLabel labelup = new SpanLabel("description");

        Container HeadConainter = new Container(gridLayout);
        HeadConainter.add(labelt);
        HeadConainter.add(labeldesc);
        HeadConainter.add(labelde);
//        HeadConainter.add(labelup);
        tempForm.add(HeadConainter);


        ArrayList<Terrain> themes = ServiceTerrain.getInstance().getAllTerrain();
        for (Terrain t : themes) {
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

                Dialog dig = new Dialog("Supprimer");

                if (dig.show("Supprimer", "aimeriez-vous supprimer ce terrain? cette action ne sera pas annulée !", "Cancel", "OK")) {
                    dig.dispose();
                } else {
                    dig.dispose();
                    // appel de la fonction delete du service Experience
                    if (ServiceTerrain.getInstance().DeleteTerrain(t)) {

                        new AfficherTerrainForm(current);
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
                new ModifierTerrainForm(t).show();
            });

            Container BodyConainter = new Container(gridLayout);
            BodyConainter.add(new Label(t.getNomterrain()));
            BodyConainter.add(new Label(t.getAdresse()));
            BodyConainter.add(new Label(t.getEtat()));
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
                   Container  BodyConainter = (Container) cmp;
                    if (!(BodyConainter.getComponentAt(0) instanceof Container )) {
                        Label label1 = (Label) BodyConainter.getComponentAt(0);
                        String line1 = label1.getText();
                        Label label2 = (Label) BodyConainter.getComponentAt(1);
                        String line2 = label2.getText();
                        Label label3 = (Label) BodyConainter.getComponentAt(2);
                        String line3 = label3.getText();
//                        SpanLabel label4 = (SpanLabel) mb.getComponentAt(3);
//                        String line4 = label4.getText();
                        boolean show = line1 != null && line1.toLowerCase().contains(text)
                                || line2 != null && line2.toLowerCase().contains(text)
                                || line3 != null && line3.toLowerCase().contains(text);
//                                || line4 != null && line4.toLowerCase().contains(text);
                        BodyConainter.setHidden(!show);
                        BodyConainter.setVisible(show);
                    }
                }
                tempForm.getContentPane().animateLayout(150);

            }
        }, 4);
        
        
       
//        tempForm.getToolbar().addSearchCommand(e -> {
//            String text = (String) e.getSource();
//            if (text == null || text.length() == 0) {
//                // clear search
//                for (Component cmp : tempForm.getContentPane()) {
//                    cmp.setHidden(false);
//                    cmp.setVisible(true);
//                }
//                tempForm.getContentPane().animateLayout(150);
//            } else {
//                text = text.toLowerCase();
//                for (Component cmp : tempForm.getContentPane()) {
//                    MultiButton mb = (MultiButton) cmp;
//                    String line1 = mb.getTextLine1();
//                    String line2 = mb.getTextLine2();
//                    boolean show = line1 != null && line1.toLowerCase().indexOf(text) > -1
//                            || line2 != null && line2.toLowerCase().indexOf(text) > -1;
//                    mb.setHidden(!show);
//                    mb.setVisible(show);
//                }
//                tempForm.getContentPane().animateLayout(150);
//            }
//        }, 4);
        
        
        Button Addnew = new Button("Ajouter");
        Addnew.addActionListener(e -> new AjouterTerrainForm(current).show());
        tempForm.add(Addnew);
        tempForm.show();
        tempForm.setTransitionOutAnimator(CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, false, 250));
        tempForm.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                e -> new BaseForm().showBack()); // Revenir vers l'interface précédente
        return tempForm;
        
        
       
    }
    
   



}
