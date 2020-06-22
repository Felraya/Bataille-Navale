package Listener;

import Application.CompositionFlotte;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RemoveBattleships implements ActionListener {
    private CompositionFlotte vue;

    public RemoveBattleships(CompositionFlotte vue) {

        this.vue = vue;
    }
    @Override
    public void actionPerformed(ActionEvent e) {


            vue.increment_compteur();
            vue.increment_compteur();
            vue.increment_compteur();
            vue.increment_compteur();
            int i = vue.getValeur_compteur();
            vue.setCompteur(i);
            vue.decrementCompteurBattlleships();
            i = vue.getValeur_compteur_Battlleships();
            vue.setCompteurBattlleships(i);
            vue.maj_All();

    }
}
