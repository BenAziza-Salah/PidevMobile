/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.mycompany.entities.Terrain;
import com.mycompany.services.ServiceTerrain;

import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.util.Resources;


/**
 *
 * @author Lenovo
 */
public abstract class SideMenuBaseForm extends Form {

    Form current;

    public SideMenuBaseForm(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }

    public SideMenuBaseForm(String title) {
        super(title);
    }

    public SideMenuBaseForm() {
    }

    public SideMenuBaseForm(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }

    public void setupSideMenu( Form current) {
//        Image profilePic = res.getImage("" + u.getUserPhoto());
//        Image mask = res.getImage("round-mask.png");
//        mask = mask.scaledHeight(mask.getHeight() / 4 * 3);
//        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
//        Label profilePicLabel = new Label(u.getUserName(), profilePic, "SideMenuTitle");
//        profilePicLabel.setMask(mask.createMask());

//        Container sidemenuTop = BorderLayout.center(profilePicLabel);
//        sidemenuTop.setUIID("SidemenuTop");

//        getToolbar().addComponentToSideMenu(sidemenuTop);

//        getToolbar().addMaterialCommandToSideMenu("  Dashboard", FontImage.MATERIAL_DASHBOARD, e -> showOtherForm( res));
//        getToolbar().addMaterialCommandToSideMenu("  Meetings", FontImage.MATERIAL_TRENDING_UP, e -> new Meetings(u, res).show());
//        getToolbar().addMaterialCommandToSideMenu("  Projects", FontImage.MATERIAL_ACCESS_TIME, e -> new Projects(u, res).show());
//        getToolbar().addMaterialCommandToSideMenu("  Releases", FontImage.MATERIAL_ACCESS_TIME, e -> new Releases(u, res).show());
//
//        getToolbar().addMaterialCommandToSideMenu("  Documents", FontImage.MATERIAL_TRENDING_UP, e->  new Dailys(u, res).show());
//        getToolbar().addMaterialCommandToSideMenu("  Issues", FontImage.MATERIAL_ACCESS_TIME,e -> new Issues(u, res).show());
//
//        getToolbar().addMaterialCommandToSideMenu("  Meeting Claims", FontImage.MATERIAL_ACCESS_TIME, e -> new Claims(u, res).show());
//        getToolbar().addMaterialCommandToSideMenu("  Account Settings", FontImage.MATERIAL_SETTINGS, e -> showOtherForm(u, res));
//
//        getToolbar().addMaterialCommandToSideMenu("  Logout", FontImage.MATERIAL_EXIT_TO_APP, e -> new Login(current, res).show());
           getToolbar().addMaterialCommandToSideMenu("Terrain", FontImage.MATERIAL_SETTINGS, e -> new AfficherTerrainForm(current));
    }

    protected abstract void showOtherForm( Resources res);

}
