package Listener;

import Application.*;
import com.mashape.unirest.http.exceptions.UnirestException;
import info1.network.*;
import info1.ships.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class JouerCase implements ActionListener {
    private Bataille modele;
    private Partie vue;
    private String url;

    public JouerCase(Partie p,Bataille b,String url){
        this.url = url;
        vue = p;
        modele = b;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String coordString = vue.getCoordSelected();

        System.out.println("Etat de la game : " + modele);

        try {
            if(Network.getInfo(vue.getIp(),vue.getPartie(),vue.getPlayer()) == 10){

                if(modele.possible(coordString)){
                    System.out.println("url : "+this.url+" partie : "+vue.getPlayer()+" coor : "+coordString);
                    int res = Network.playOneTurn(url,vue.getPartie(),vue.getPlayer(),new Coord(coordString));
                    modele.setDejaJoue(new Coord(coordString));
                    System.out.println(modele.getTotalTirs());
                    vue.setTotal(modele.getTotalTirs());
                    if(res == 1){
                        modele.setCoordTouche(new Coord(coordString));
                        System.out.println(modele.getTouche());
                        vue.setTouche(modele.getTouche());
                        vue.setEtatTire("     Touché");
                        vue.addTireTouche(new Coord(coordString));
                        //vue.setNom_Player2(modele.getGuest().getName());
                    }
                    else if(res==10){
                        vue.setEtatTire("     Coulé");
                        vue.addTireTouche(new Coord(coordString));new Coord(coordString);
                        //vue.setNom_Player2("Player 21");
                    }
                    else if(res==100) {
                        System.out.println("Gagné");
                        vue.dispose();
                        JOptionPane.showMessageDialog(vue, "Bien joué(e), tu as gagné");
                        Principale p = new Principale();
                    }

                    else if(res==0) {
                        modele.setCoordLoupe(new Coord(coordString));
                        System.out.println(modele.getLoupe());

                        vue.setEtatTire("     Manqué");
                        vue.addTireLoupe(new Coord(coordString));
                        //vue.setNom_Player2("Player 21");

                    }
                }
                else{
                    JOptionPane.showMessageDialog(vue,"Case déjà jouée");
                }


            }
            else{
                JOptionPane.showMessageDialog(vue,"Attend que l'adversaire joue");
            }


            //message serveur
        } catch (BadCoordException e) {
            e.printStackTrace();
        } catch (UnirestException e) {
            e.printStackTrace();
        } catch (BadIdException e) {
            e.printStackTrace();
        }
    }



}
