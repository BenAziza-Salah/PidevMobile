/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Article;
import com.mycompany.entities.Categoriearticle;
import com.mycompany.services.ServiceArticle;
import com.mycompany.services.ServiceCat;

/**
 *
 * @author ArwaBj
 */
public class AddCatForm extends BaseForm{
Form current;
    public AddCatForm(Form previous) {
        /*
        Le paramètre previous définit l'interface(Form) précédente.
        Quelque soit l'interface faisant appel à AddTask, on peut y revenir
        en utilisant le bouton back
        */
        setTitle("Add a new category");
        setLayout(BoxLayout.y());
        
        
        TextField nomcat = new TextField("","Category Name");
        
      

        Button btnValider = new Button("Add category");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((nomcat.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        Categoriearticle t = new Categoriearticle();
                        t.setNomcat(nomcat.getText());
                        
                        if( ServiceCat.getInstance().addCat(t))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a string", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(nomcat,btnValider);
    this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());             

      
    }
    
    
}
