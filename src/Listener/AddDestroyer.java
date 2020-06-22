package Listener;

import Application.CompositionFlotte;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddDestroyer implements ActionListener {
    private CompositionFlotte vue;

    public AddDestroyer(CompositionFlotte vue) {
        this.vue = vue;
    }
    @Override
    public void actionPerformed(ActionEvent e) {


            vue.decrement_compteur();
            vue.decrement_compteur();
            int i = vue.getValeur_compteur();
            vue.setCompteur(i);
            vue.incrementCompteurDestroyer();
            i = vue.getValeur_compteur_Destroyer();
            vue.setCompteurDestroyer(i);
            vue.maj_All();




    }
}
