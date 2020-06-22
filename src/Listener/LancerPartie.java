package Listener;

import Application.FenetreDeConnexion;
import Application.Partie;
import com.mashape.unirest.http.exceptions.UnirestException;
import info1.network.*;
import info1.ships.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

public class LancerPartie implements ActionListener {
    private FenetreDeConnexion vue;
    private Bataille modele;
    private Network n;
    private Player p;
    private boolean createur;
    private Game g;
    private String url;

    public LancerPartie(FenetreDeConnexion vue, Bataille b, Network n, Player p, boolean createur, Game g) {
        this.vue = vue;
        this.modele = b;
        this.n = n;
        this.p = p;
        this.createur = createur;
        this.g = g;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
            url = "http://"+vue.getIpServeur()+"/api/v0";
            System.out.println(url);
            System.out.println((n.getInfo(url,g,p)));
            if((n.getInfo(url,g,p) != 1)){

                n.suscribeNewPlayer(url,p);
                Partie partie = new Partie(modele,vue.getJoueur(),g,url);
                vue.dispose();
            }
            else{
                JOptionPane.showMessageDialog(vue, "Tu es seul enculé d'adrien");
            }
        } catch (UnirestException e) {
            e.printStackTrace();
        } catch (BadIdException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BadCoordException e) {
            e.printStackTrace();
        }
    }
}
