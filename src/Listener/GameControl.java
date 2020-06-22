package Listener;

import Application.FenetreDeConnexion;
import Application.Partie;
import Application.Principale;
import com.mashape.unirest.http.exceptions.UnirestException;
import info1.network.BadIdException;
import info1.network.Game;
import info1.network.Network;
import info1.ships.BadCoordException;
import info1.ships.Bataille;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

public class GameControl {
    private FenetreDeConnexion fenConn;
    private Partie vue;
    private Bataille modele;
    private Game game;
    public GameControl(FenetreDeConnexion v,Bataille m,Game g){
        fenConn = v;
        modele = m;
        game = g;
    }

    public void jouer() throws InvocationTargetException, InterruptedException {
        boolean partieTerminee = false;
        try{
            vue = new Partie(modele,fenConn.getJoueur(),game,fenConn.getIpServeur());
            System.out.println("ip : "+vue.getIp()+"partie : "+vue.getPartie()+"player : "+vue.getPlayer());
            int res = Network.getInfo(vue.getIp(),vue.getPartie(),vue.getPlayer());
        while(!partieTerminee) {
            System.out.println("Test_gameControl");

            Thread.sleep(3000);
            System.out.println(res);
            if (res == 10) {
                vue.setEnableButtonTire(true);
                vue.setTextTour("tu peux jouer !");

            } else if (res == -10) {
                vue.setEnableButtonTire(false);
                vue.setTextTour("Ce n'est pas ton tour !");

            } else {
                Thread.sleep(3000);
            }
            res = Network.getInfo(vue.getIp(), vue.getPartie(), vue.getPlayer());

            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    vue.update(vue.getGraphics());
                }
            });

        }



        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (UnirestException e) {
            e.printStackTrace();
        } catch (BadIdException e) {
            e.printStackTrace();
        } catch (BadCoordException e) {
            e.printStackTrace();
        }
    }
}
