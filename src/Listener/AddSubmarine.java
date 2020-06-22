package Listener;

import Application.CompositionFlotte;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddSubmarine implements ActionListener {
    private CompositionFlotte vue;

    public AddSubmarine(CompositionFlotte vue) {
        this.vue = vue;
    }

    @Override
    public void actionPerformed(ActionEvent e) {



            vue.decrement_compteur();
            int i = vue.getValeur_compteur();
            vue.setCompteur(i);
            vue.incrementCompteurSubmarine();
            i = vue.getValeur_compteur_submarine();
            vue.setCompteurSubmarine(i);
            vue.maj_All();




    }
}
