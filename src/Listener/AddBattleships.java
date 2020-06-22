package Listener;

import Application.CompositionFlotte;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddBattleships implements ActionListener {
    private CompositionFlotte vue;

    public AddBattleships(CompositionFlotte vue) {
        this.vue = vue;
    }
    @Override
    public void actionPerformed(ActionEvent e) {



            vue.decrement_compteur();
            vue.decrement_compteur();
            vue.decrement_compteur();
            vue.decrement_compteur();
            int i = vue.getValeur_compteur();
            vue.setCompteur(i);
            vue.incrementCompteurBattlleships();
            i = vue.getValeur_compteur_Battlleships();
            vue.setCompteurBattlleships(i);
            vue.maj_All();


    }
}
