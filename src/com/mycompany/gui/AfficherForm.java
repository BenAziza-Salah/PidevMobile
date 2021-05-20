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
import com.mycompany.services.ServiceJoueurs;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.mycompany.entites.Joueurs;
import com.codename1.components.SpanLabel;

import java.util.ArrayList;

/**
 *
 * @author Matoussi
 */
public class AfficherForm extends BaseForm {

    Form current;
    Resources res;

    public AfficherForm(Form f) {

        current = createForm();
    }
    

    public Form createForm() {
        Toolbar tb = new Toolbar(true);
        Toolbar.setGlobalToolbar(true);
        //Joueurs t = new Joueurs();
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        getContentPane().setScrollVisible(false);
        Form tempForm = new Form("Toolbar", new BoxLayout(BoxLayout.Y_AXIS));
        ////////////////////////////////////////////////////////////////////////

        tempForm.setTitle("Joueurs");
        tempForm.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        tempForm.setTransitionOutAnimator(null);
        GridLayout gridLayout = new GridLayout(1, 5);

        SpanLabel labelt = new SpanLabel("Nom ");
        SpanLabel labeldesc = new SpanLabel("Prenom");
        SpanLabel labelde = new SpanLabel("Age");
        SpanLabel labelup = new SpanLabel("Email");

        Container HeadConainter = new Container(gridLayout);
        HeadConainter.add(labelt);
        HeadConainter.add(labeldesc);
        HeadConainter.add(labelde);
        HeadConainter.add(labelup);
        tempForm.add(HeadConainter);

        ArrayList<Joueurs> themes = ServiceJoueurs.getInstance().getAllJoueurs();
        for (Joueurs t : themes) {
            //delete button
            Label lSupprimer = new Label("");
            lSupprimer.setUIID("NewsTopLine");
            Style supprimerStyle = new Style(lSupprimer.getUnselectedStyle());
            supprimerStyle.setFgColor(0xf21f1f);
            FontImage supprimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprimerStyle);
            lSupprimer.setIcon(supprimerImage);
            lSupprimer.setTextPosition(RIGHT);
           
            lSupprimer.addPointerPressedListener(l -> {

                Dialog dig = new Dialog("supprimer");

                if (dig.show("supprimer", "would you liike to delete this Theme? this action will not be reverted", "Cancel", "OK")) {
                    dig.dispose();
                } else {
                    dig.dispose();
                   //  appel de la fonction delete du service Experience
                    if (ServiceJoueurs.getInstance().Deletejr(t)) {

                        new AfficherForm(current);
                    }
                }
            });

            String Age = String.valueOf(t.getAge());
            String idc = String.valueOf(t.getIdClub());
            Container BodyConainter = new Container(gridLayout);
            BodyConainter.add(new Label(t.getNom()));
            BodyConainter.add(new Label(t.getPrenom()));
            BodyConainter.add(new Label(Age));
            BodyConainter.add(new Label(t.getEmail()));
            //BodyConainter.add(new Label(idc));
            BodyConainter.add(lSupprimer);


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
                    if(!(mb.getComponentAt(0) instanceof Button)){
                    Label label1 = (Label) mb.getComponentAt(0);
                    String line1 = label1.getText();
                    Label label2 = (Label) mb.getComponentAt(1);
                    String line2 = label2.getText();
                    Label label3 = (Label) mb.getComponentAt(2);
                    String line3 = label3.getText();
                    Label label4 = (Label) mb.getComponentAt(3);
                    String line4 = label4.getText();
                    boolean show = line1 != null && line1.toLowerCase().indexOf(text) > -1 ||
                    line2 != null && line2.toLowerCase().indexOf(text) > -1 ||
                    line3 != null && line3.toLowerCase().indexOf(text) > -1 ||
                    line4 != null && line4.toLowerCase().indexOf(text) > -1;
                    mb.setHidden(!show);
                    mb.setVisible(show);
                    }
                }
                tempForm.getContentPane().animateLayout(150);
            } }, 5);
        Button Addnew = new Button("Add new");
        Button supp = new Button ("Supprimer");
        Addnew.addActionListener(e -> new AjoutJoueurs(res).show());
       
        tempForm.add(Addnew);
        
        tempForm.show();
        tempForm.setTransitionOutAnimator(CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, false, 250));
        tempForm.getToolbar().addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK,
                e -> new HomeForm(res).showBack()); // Revenir vers l'interface précédente
        return tempForm;
        
       
    }
 
    
}
