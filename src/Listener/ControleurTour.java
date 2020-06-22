package Listener;

import Application.Partie;
import com.mashape.unirest.http.exceptions.UnirestException;
import info1.network.BadIdException;
import info1.network.Network;
import info1.ships.Bataille;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.peer.MouseInfoPeer;

public class ControleurTour implements MouseMotionListener {
    private Bataille modele;
    private Partie vue;
    public ControleurTour(Bataille b, Partie partie){
        modele = b;
        vue = partie;
    }


    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        int res = 0;
        try {
            res = Network.getInfo(vue.getIp(), vue.getPartie(), vue.getPlayer());
            System.out.println("Test_gameControl");

            System.out.println(res);
            if (res == 10) {
                vue.setEnableButtonTire(true);
                vue.setTextTour("tu peux jouer !");

            } else if (res == -10) {
                vue.setEnableButtonTire(false);
                vue.setTextTour("Ce n'est pas ton tour !");

            }
        } catch (UnirestException e) {
            e.printStackTrace();
        } catch (BadIdException e) {
            e.printStackTrace();
        }


    }
}
