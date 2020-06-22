package Listener;

import Application.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Valider implements ActionListener {
    private Principale vue;

    public Valider(Principale vue) {
        this.vue = vue;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        String flotteSelectionne = vue.getComboBox();
        System.out.println(flotteSelectionne);
        ArrayList<Integer> flotte = new ArrayList<>();
        if(flotteSelectionne.equals("Flotte Française")){
            flotte.add(1);
            flotte.add(2);
            flotte.add(2);
            flotte.add(1);
            flotte.add(1);
            vue.setFlotte(flotte);
        }
        else if (flotteSelectionne.equals("Flotte Belge")){
            flotte.add(4);
            flotte.add(3);
            flotte.add(2);
            flotte.add(1);
            flotte.add(0);
            vue.setFlotte(flotte);
        }

        System.out.println(vue.getFlotte());


        String nom=vue.getNickname();
        if(!(nom.equals("")||vue.getFlotte().size()==0)) {
            System.out.println("validé");
            FenetrePlacement f = new FenetrePlacement(vue.getFlotte(),vue.getPlayer());

            vue.dispose();

        }

        else if(nom.equals("") ){
            JOptionPane.showMessageDialog(vue,"Merci de choisir un pseudo non vide");
        }

        else {
            JOptionPane.showMessageDialog(vue,"Merci de finir de completer votre flotte personnalisée");
        }
    }
}