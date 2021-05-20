/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;


import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Article;
import com.mycompany.services.ServiceArticle;

import java.util.ArrayList;

/**
 *
 * @author ArwaBj
 */
public class ListArticlesForm  extends HomeForm1 {

    Form current;

    public ListArticlesForm(Resources res) {

  //super("", BoxLayout.y());
 
      
      // getTitleArea().setUIID("Container");
      
      
      setTitle("Article");
      
//       Toolbar tb = new Toolbar(true);
//        setToolbar(tb);
//      // getTitleArea().setUIID("Container");
//     // setTitle("Ajouter Film");
//        getContentPane().setScrollVisible(false);
//       super.addSideMenu(res); 
       Form tempForm = new Form();

        tempForm.setTitle("Article");
        tempForm.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        tempForm.setTransitionOutAnimator(CommonTransitions.createEmpty());
        GridLayout gridLayout = new GridLayout(1, 6);

        Font fnt = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
        Label labelLibelle = new Label("Libelle");
       // Label labelImagearticle = new Label("image_Article");
        Label labelQtarticle = new Label("Quantité");
        Label labelPrix = new Label("Prix");
       // Label labelref = new Label("Reference");
                 Label labelrate= new Label("Rate");

        Label labelEdit = new Label(" ");
        Label labelDelete = new Label(" ");
        

        labelLibelle.getUnselectedStyle().setFont(fnt);
        //labelImagearticle.getUnselectedStyle().setFont(fnt);
        labelQtarticle.getUnselectedStyle().setFont(fnt);
          labelPrix.getUnselectedStyle().setFont(fnt);
        //labelref.getUnselectedStyle().setFont(fnt);
        labelrate.getUnselectedStyle().setFont(fnt);
        labelEdit.getUnselectedStyle().setFont(fnt);
        labelDelete.getUnselectedStyle().setFont(fnt);

        Container HeadConainter = new Container(gridLayout);
        HeadConainter.add(labelLibelle);
        //HeadConainter.add(labelImagearticle);
        
        HeadConainter.add(labelQtarticle);
        HeadConainter.add(labelPrix);
        //HeadConainter.add(labelref);
        HeadConainter.add(labelrate);
        HeadConainter.add(labelEdit);
        HeadConainter.add(labelDelete);
//        tempForm.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
//                 e -> new HomeReview());
        tempForm.add(HeadConainter);

        ArrayList<Article> Articles = ServiceArticle.getInstance().getAllArticles();
        for (Article r : Articles) {
         //  if (r.getNom_client_review().equals(Username)) {
                Container BodyConainter = new Container(gridLayout);
                BodyConainter.add(new Label(r.getLibelle()));
               // BodyConainter.add(new Label(r.getImage_article()));
                               BodyConainter.add(new Label(r.getQt_article()));
                               BodyConainter.add(new Label(r.getPrix()));

//                String qtarticle= String.valueOf(r.getQt_article());
//                                BodyConainter.add(new Label(qtarticle));

//                String prix = String.valueOf(r.getPrix());
//                 BodyConainter.add(new Label(prix));
                String rate = String.valueOf(r.getRate());
                //String ref = String.valueOf(r.getRef());
               
                //BodyConainter.add(new Label(ref));
                BodyConainter.add(new Label(rate));
                
                Button deletebtn = new Button("Delete");

                deletebtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
    if (ServiceArticle.getInstance().deleteArticle(r)) {                           
        Dialog.show("Success", "Article has been deleted! ", "Ok", null);
                          new  ListArticlesForm(res); 
                            
                        } else {
                            Dialog.show("ERROR", "Error , Could NOT delete Review", "Ok", null);
                        }
                    }

                });

                Button editbtn = new Button("Edit");

                editbtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        tempForm.setTransitionOutAnimator(CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, false, 250));
                        new editArticle(tempForm, r).show();
                        tempForm.setTransitionOutAnimator(CommonTransitions.createEmpty());
                        
                    }
                });
                BodyConainter.add(editbtn);
                BodyConainter.add(deletebtn);
                tempForm.add(BodyConainter);
        }

    
        Button AddArticle = new Button("Add a Article");
        AddArticle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                tempForm.setTransitionOutAnimator(CommonTransitions.createSlide(CommonTransitions.SLIDE_HORIZONTAL, false, 250));
                new AddArticleForm(tempForm).show();
                tempForm.setTransitionOutAnimator(CommonTransitions.createEmpty());
            }
        });
        Container cnAdd = new Container(gridLayout);
       
       
        cnAdd.add(AddArticle);
        tempForm.add(cnAdd);
       

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
            }
        }, 4);
          tempForm.show();
 
    }
    }

    


