package demo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {

    private Vue vue;
    private Modele modele;

    public Controller(Vue vue, Modele modele) {
        this.vue = vue;
        this.modele = modele;
        this.vue.getButton().addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        modele.inc();
        vue.update(modele);
    }
}
