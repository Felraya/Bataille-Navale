package Listener;

import Application.CompositionFlotte;
import Application.Principale;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddFlotte implements ActionListener {
    private Principale vue;
    public AddFlotte(Principale vue) {
    this.vue = vue;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        CompositionFlotte cf = new CompositionFlotte(vue);
        cf.maj_All();
        System.out.println("lance personnalisation de flotte");
        vue.addFlottePerso();
    }
}