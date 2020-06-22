package Listener;

import Application.CompositionFlotte;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveAircraft implements ActionListener {
    private CompositionFlotte vue;

    public RemoveAircraft(CompositionFlotte vue) {
        this.vue = vue;
    }
    @Override
        public void actionPerformed(ActionEvent e) {


                vue.increment_compteur();
                vue.increment_compteur();
                vue.increment_compteur();
                vue.increment_compteur();
                vue.increment_compteur();
                int i = vue.getValeur_compteur();
                vue.setCompteur(i);
                vue.decrementCompteurAircraft();
                i = vue.getValeur_compteur_Aircraft();
                vue.setCompteurAircraft(i);
                vue.maj_All();


    }
}
