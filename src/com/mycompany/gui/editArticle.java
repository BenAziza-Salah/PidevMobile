/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.l10n.L10NManager;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Article;
import com.mycompany.services.ServiceArticle;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author ArwaBj
 */
public class editArticle extends BaseForm {




    ArrayList<Double> myList = new ArrayList<>();

    public editArticle(Form previous, Article r) {
        myList.add((Double) 0.0);
        myList.add((Double) 1.0);
        myList.add((Double) 2.0);
        myList.add((Double) 3.0);
        myList.add((Double) 4.0);
        myList.add((Double) 5.0);
       
        setTitle("Edit My  Article");
           //super("", BoxLayout.y());
        
    
        Font fnt = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
       
      
        
 TextField tflibelle = new TextField(r.getLibelle());
   
        TextField tfImagearticle = new TextField(r.getImage_article());
         TextField tfqt = new TextField(r.getQt_article());
         TextField tfprix = new TextField(r.getPrix());
          TextField tfref = new TextField(r.getRef());
       tfprix.setEditable(true);
       tfqt.setEditable(true);
       tfref.setEditable(true);

        ComboBox RatingCombo = new ComboBox();
       
            for (int i = 0; i < myList.size(); i++) {
                RatingCombo.addItem(myList.get(i));
            }
           
            Button btnValider = new Button("Edit Article");
        btnValider.addActionListener(new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent evt) {
                if ((tflibelle.getText().length() == 0) || (tfImagearticle.getText().length() == 0)|| (tfqt.getText().length() == 0)|| (tfprix.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", "OK", null);
                } else {
                    try {

                        r.setLibelle(tflibelle.getText());
                       
                        r.setImage_article(tfImagearticle.getText());

                        r.setQt_article(tfqt.getText());
                        r.setPrix((tfprix.getText()));
                        r.setRef((tfref.getText()));

                        r.setRate((Double) RatingCombo.getSelectedItem());
                        if( ServiceArticle.getInstance().editArticle(r)){
                             Dialog.show("Success", "Connection accepted", "OK", null);
                              // ListArticlesForm f = new ListArticlesForm(previous);
                           // f.setTransitionOutAnimator(CommonTransitions.createEmpty());
                             }
                             
                        else
                             Dialog.show("Error", "Server ERROR", "OK", null);

                    } catch (NumberFormatException e) {
                        Dialog.show("Alert", "Please fill all the fields", "OK", null);
                    }

                }

            }
        });
        addAll(tflibelle, tfImagearticle, tfqt, RatingCombo, tfprix,tfref, btnValider);
        setTransitionOutAnimator(CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, false, 250));
        //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                 //e -> new ListArticlesForm(previous));
                this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());             
 
           
           
    }
   

}
