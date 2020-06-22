package Listener;

import Application.CompositionFlotte;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveDestroyer implements ActionListener {
    private CompositionFlotte vue;

    public RemoveDestroyer(CompositionFlotte vue) {

        this.vue = vue;
    }
    @Override
    public void actionPerformed(ActionEvent e) {


            vue.increment_compteur();
            vue.increment_compteur();
            int i = vue.getValeur_compteur();
            vue.setCompteur(i);
            vue.decrementCompteurDestroyer();
            i = vue.getValeur_compteur_Destroyer();
            vue.setCompteurDestroyer(i);
            vue.maj_All();


    }
}
