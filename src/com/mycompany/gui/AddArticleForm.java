/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Article;
import com.mycompany.services.ServiceArticle;
import java.util.ArrayList;

/**
 *
 * @author ArwaBj
 */
public class AddArticleForm extends BaseForm{
Form current;
    ArrayList<Double> myList = new ArrayList<>();

    public AddArticleForm(Form previous) {
        /*
        Le paramètre previous définit l'interface(Form) précédente.
        Quelque soit l'interface faisant appel à AddTask, on peut y revenir
        en utilisant le bouton back
        */
        setTitle("Add a new article");
        //setLayout(BoxLayout.y());
        
           myList.add((Double) 0.0);
        myList.add((Double) 1.0);
        myList.add((Double) 2.0);
        myList.add((Double) 3.0);
        myList.add((Double) 4.0);
        myList.add((Double) 5.0);
        TextField libelle = new TextField("","libelle");
        
        TextField imagearticle= new TextField("", "image_article");
        TextField qt_article= new TextField("", "qt_article");
        TextField prix= new TextField("", "prix");
        TextField ref= new TextField("", "ref");
    ComboBox RatingCombo = new ComboBox();
       
            for (int i = 0; i < myList.size(); i++) {
                RatingCombo.addItem(myList.get(i));
            }
        //TextField idcat= new TextField("", "idcat");
        Button btnValider = new Button("Add article");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((libelle.getText().length()==0)||(imagearticle.getText().length()==0)||(prix.getText().length()==0)||(qt_article.getText().length()==0)||(ref.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        Article t = new Article();
                        t.setLibelle(libelle.getText());
                        t.setImage_article(imagearticle.getText());
                        t.setQt_article(qt_article.getText());
                        t.setPrix(prix.getText());
                         t.setRef(ref.getText());
                        t.setRate((Double) RatingCombo.getSelectedItem());

                        if( ServiceArticle.getInstance().addArticle(t))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                                     // ListArticlesForm a = new ListArticlesForm(f,res);
                           // f.setTransitionOutAnimator(CommonTransitions.createEmpty());}
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(libelle,imagearticle,qt_article,prix,ref,RatingCombo,btnValider);
     // this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());             
    this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());             

      
    }
    
    
}
