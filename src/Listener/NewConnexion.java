package Listener;

import Application.*;
import com.mashape.unirest.http.exceptions.UnirestException;
import info1.network.*;
import info1.ships.*;
import sun.nio.ch.Net;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NewConnexion implements ActionListener {
    private FenetreDeConnexion vue;
    private Network n;
    private Game g;
    private Bataille modele;
    private Player p;

    public NewConnexion(FenetreDeConnexion vue, Network n, Player p, Bataille modele) {
        this.vue = vue;
        this.n = n;
        this.modele = modele;
        this.p = p;
    }



    @Override
    public void actionPerformed(ActionEvent e) {

        if(vue.getLabel().equals("Créer un salon")){
            if(vue.getIpServeur().length() > 0){
                try {
                    System.out.println("http://"+vue.getIpServeur()+"/api/v0");
                    n.suscribeNewPlayer("http://"+vue.getIpServeur()+"/api/v0",p);
                    g = Network.initNewGame("http://"+vue.getIpServeur()+"/api/v0",p,modele.getBateaux());
                } catch (UnirestException e1) {
                    JOptionPane.showMessageDialog(vue,"Pas de serveur à cet IP");
                } catch (UncompleteFleetException e1) {
                    //e1.printStackTrace();
                } catch (BadCoordException e1) {
                    //e1.printStackTrace();
                }

                System.out.println(g);
                vue.changerLabel("ID game : " + g.getId());
                vue.activerLancerPartie();
                vue.desactiverEmplacementIP();
                vue.changerLabel("ID game : " + g.getId());


                try {
                    vue.attenteSecondJoueur(n,g);
                } catch (BadIdException e1) {
                    //e1.printStackTrace();
                } catch (UnirestException e1) {
                    //e1.printStackTrace();
                } catch (InterruptedException e1) {
                    //e1.printStackTrace();
                } catch (BadCoordException e1) {
                    //e1.printStackTrace();
                }
            }
            else{
                JOptionPane.showMessageDialog(vue,"Pour créer un serveur il faut rentrer l'IP et le port du serveur");
            }
        }
        else{
            vue.changerLabel("ID game : créer pour définir");
            vue.annulerSalon();
            vue.desactiverLancerPartie();
            vue.activerEmplacementIP();
        }
    }
}


/*


 List<Game> listeId = new ArrayList<>();
 try {
                listeId.add(new Game(6));
                listeId.add(new Game(1));
                listeId.add(new Game(2));
            } catch (BadIdException e1) {
                e1.printStackTrace();
            }

            System.out.println(listeId);


            try {
                    listeId = n.listInitializedGames("http://localhost:3000/api/v0");
                    System.out.println(listeId.toString());

                    } catch (UnirestException e1) {
                    e1.printStackTrace();
                    }

                    Game tmp = null;

                    try {
                    tmp = new Game(this.random(1,100));

                    while(listeId.contains(tmp)){
                    tmp = new Game(this.random(1,100));
                    }
                    } catch (BadIdException e1) {
                    e1.printStackTrace();
                    }

                    g = tmp; */

