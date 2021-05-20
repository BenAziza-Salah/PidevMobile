/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author lenovo
 */
public class HomeForm extends BaseForm{
Form current;
    public HomeForm(Resources res) {
        current = this;
        
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        
        setTitle("Gérer les joueurs");
       
        setLayout(BoxLayout.y());
        getContentPane().setScrollVisible(false);
        
        tb.addSearchCommand(e -> {});
         
  
        add (new Label ("Bienvenue"));
        Button btnJoueur =  new Button ("Consulter les joueurs");
        Button btnliste =  new Button ("Lister les joueurs");
        
        btnJoueur.addActionListener(e-> new AjoutJoueurs(res).show());
        btnliste.addActionListener(e-> new AfficherForm(current));
        addAll(btnJoueur,btnliste);
        
    }

}