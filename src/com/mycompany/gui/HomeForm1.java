/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;
import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.charts.ChartDemosForm;
/**
 *
 * @author ArwaBj
 */



public class HomeForm1 extends Form {

    Form current;

    /*Garder traçe de la Form en cours pour la passer en paramètres
    aux interfaces suivantes pour pouvoir y revenir plus tard en utilisant
    la méthode showBack*/
    public HomeForm1() {
        current = this; //Récupération de l'interface(Form) en cours
        setTitle("Home");
       

        Menu(this);

    }

    public static void Menu(Form f) {

        f.getToolbar().addMaterialCommandToLeftSideMenu("Accueil", " ".charAt(0), e -> new HomeForm1());
        Resources res = null;
        f.getToolbar().addMaterialCommandToLeftSideMenu("List Article", " ".charAt(0),e -> new ListArticlesForm(res));
                f.getToolbar().addMaterialCommandToLeftSideMenu("List Category", " ".charAt(0),e -> new ListCatsForm(res));

          f.getToolbar().addMaterialCommandToLeftSideMenu("Stat Article", " ".charAt(0),e -> new ChartDemosForm(res).show());

    }

    void addSideMenu(Resources res, Form f) {
    }

}