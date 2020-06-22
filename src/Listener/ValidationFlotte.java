package Listener;

import Application.CompositionFlotte;
import Application.Principale;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ValidationFlotte implements ActionListener {
    private CompositionFlotte vue;
    private Principale vue2;
    private ArrayList<Integer> flotte;

    public ValidationFlotte(CompositionFlotte vue, Principale vue2) {
        this.vue = vue;
        this.vue2 = vue2;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        flotte = new ArrayList<>();
        if(vue.getCompteur().equals("0")){
            flotte.add(vue.getValeur_compteur_submarine());
            flotte.add(vue.getValeur_compteur_Destroyer());
            flotte.add(vue.getValeur_compteur_Cruiser());
            flotte.add(vue.getValeur_compteur_Battlleships());
            flotte.add(vue.getValeur_compteur_Aircraft());

            vue2.setFlotte(flotte);
            vue2.change_button();
            vue.dispose();
        }
        else{
            JOptionPane.showMessageDialog(vue,"Merci de compléter votre flotte!");
        }
    }
}

