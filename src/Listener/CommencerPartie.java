package Listener;

import Application.*;
import com.mashape.unirest.http.exceptions.UnirestException;
import info1.network.*;
import info1.ships.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CommencerPartie implements ActionListener {
    private NavyFleet modele;
    private FenetrePlacement vue;
    private Player p;

    public CommencerPartie(NavyFleet m,FenetrePlacement v,Player p){
        vue = v;
        modele = m;
        this.p=p;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        try {
            System.out.println("AAAAAAAAAAAAAAa" + modele);
            FenetreDeConnexion fc = new FenetreDeConnexion(p, new Bataille(modele));
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        vue.dispose();
    }

    public void setFlotte(NavyFleet f){
        this.modele = f;
    }
}
