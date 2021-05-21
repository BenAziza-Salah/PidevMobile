/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

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
import com.mycompany.services.ServiceDemande;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Toolbar;
import com.mycompany.entities.Equipe;
import com.mycompany.services.ServiceEquipe;

import java.util.ArrayList;

/**
 *
 * @author OussKh
 */
public class AfficherEquipeForm extends BaseForm {

    Form current;
    Resources res;

    public AfficherEquipeForm(Form previous ) {

        current = createForm();

    }

    public Form createForm() {
        Toolbar tb = new Toolbar(true);
        Toolbar.setGlobalToolbar(true);
        
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        getContentPane().setScrollVisible(false);
        Form tempForm = new Form("Toolbar", new BoxLayout(BoxLayout.Y_AXIS));
      
//        super.addSideMenu(res);
//        tb.addSearchCommand(e -> {});
//          Image img = res.getImage("profile-background.jpg");
//        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
//            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
//        }
//        ScaleImageLabel sl = new ScaleImageLabel(img);
//        sl.setUIID("BottomPad");
//        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        tempForm.setTitle("Equipe");
        tempForm.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        tempForm.setTransitionOutAnimator(CommonTransitions.createEmpty());
        GridLayout gridLayout = new GridLayout(1, 5);

        SpanLabel labelt = new SpanLabel("nom");
        SpanLabel labeldesc = new SpanLabel("nombre");
        
        
//        SpanLabel labelup = new SpanLabel("description");

        Container HeadConainter = new Container(gridLayout);
        HeadConainter.add(labelt);
        HeadConainter.add(labeldesc);

//        HeadConainter.add(labelup);
        tempForm.add(HeadConainter);


        ArrayList<Equipe> themes = ServiceEquipe.getInstance().getAllEquipe();
        for (Equipe d : themes) {
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

                if (dig.show("Supprimer", "aimeriez-vous supprimer equipe? cette action ne sera pas annulée !", "Cancel", "OK")) {
                    dig.dispose();
                } else {
                    dig.dispose();
                    // appel de la fonction delete du service Experience
                    if (ServiceEquipe.getInstance().DeleteEquipe(d)) {

                        new AfficherEquipeForm(current);
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
                new ModifierEquipeForm(d).show();
            });

            Container BodyConainter = new Container(gridLayout);
            BodyConainter.add(new Label(d.getNom()));
            BodyConainter.add(new Label(String.valueOf(d.getNombre())));
           
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
                        SpanLabel label1 = (SpanLabel) mb.getComponentAt(0);
                        String line1 = label1.getText();
                        SpanLabel label2 = (SpanLabel) mb.getComponentAt(1);
                        String line2 = label2.getText();
                        SpanLabel label3 = (SpanLabel) mb.getComponentAt(2);
                        String line3 = label3.getText();
//                        SpanLabel label4 = (SpanLabel) mb.getComponentAt(3);
//                        String line4 = label4.getText();
                        boolean show = line1 != null && line1.toLowerCase().contains(text)
                                || line2 != null && line2.toLowerCase().contains(text)
                                || line3 != null && line3.toLowerCase().contains(text);
//                                || line4 != null && line4.toLowerCase().contains(text);
                        mb.setHidden(!show);
                        mb.setVisible(show);
                    }
                }
                tempForm.getContentPane().animateLayout(150);

            }
        }, 4);
        Button Addnew = new Button("Ajouter");
        Addnew.addActionListener(e -> new AjouterEquipeForm(current).show());
        tempForm.add(Addnew);
        tempForm.show();
        tempForm.setTransitionOutAnimator(CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, false, 250));
        tempForm.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                e -> new BaseForm().showBack()); // Revenir vers l'interface précédente
        return tempForm;
        
        
       
    }
    
   



}
