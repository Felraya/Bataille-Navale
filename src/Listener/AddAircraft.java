package Listener;

import Application.CompositionFlotte;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddAircraft implements ActionListener {
    private CompositionFlotte vue;

    public AddAircraft(CompositionFlotte vue) {
        this.vue = vue;
    }
    @Override
    public void actionPerformed(ActionEvent e) {



            vue.decrement_compteur();
            vue.decrement_compteur();
            vue.decrement_compteur();
            vue.decrement_compteur();
            vue.decrement_compteur();
            int i = vue.getValeur_compteur();
            vue.setCompteur(i);
            vue.incrementCompteurAircraft();
            i = vue.getValeur_compteur_Aircraft();
            vue.setCompteurAircraft(i);
            vue.maj_All();

    }
}
