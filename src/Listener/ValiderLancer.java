package Listener;

import Application.*;

import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

public class ValiderLancer implements CaretListener {
    private Partie vue;


    public ValiderLancer(Partie vue) {
        this.vue = vue;
    }


    @Override
    public void caretUpdate(CaretEvent caretEvent) {
        String coord = vue.getCoordSelected();

        if(coord.length()!=2&&coord.length()!=3){
            vue.changerLancer(false);
        }
        else{
            vue.changerLancer(true);
        }
    }
}
