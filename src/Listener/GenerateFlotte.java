package Listener;

import Application.CompositionFlotte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GenerateFlotte implements ActionListener {
    private CompositionFlotte vue;

    public GenerateFlotte(CompositionFlotte vue) {
        this.vue = vue;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        vue.reset();

        int point = 20;
        int tire;
        int i = 5;

        while (point > 0){

            if(point<5){
                i = point; //regarde la taille qui reste
            }

            tire = random(1,i); //tire un nombre random

            if(tire == 1){ //submarine
                vue.declancherNum1();
            }
            if(tire == 2){ //destroyeur
                vue.declancherNum2();
            }
            if(tire == 3){ //cruiser
                vue.declancherNum3();
            }
            if(tire == 4){ //battleship
                vue.declancherNum4();
            }
            if(tire == 5){ //aircraft carrier
                vue.declancherNum5();
            }

            point = point - tire;



        }

    }




    public int random(int min, int max){
        return (int) (min + Math.random()*(max+1-min));
    }
}
