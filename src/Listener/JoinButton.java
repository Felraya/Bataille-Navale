package Listener;

import Application.FenetreDeConnexion;
import Application.Partie;
import Application.Principale;
import com.mashape.unirest.http.exceptions.UnirestException;
import info1.network.*;
import info1.ships.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class JoinButton implements ActionListener {
    private FenetreDeConnexion vue;
    private Network n;
    private Game g;
    private Bataille modele;
    private Player p;
    private String url;

    public JoinButton(FenetreDeConnexion vue, Network n, Player p, Bataille modele) {
        this.vue = vue;
        this.n = n;
        this.modele = modele;
        this.p = p;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            List<Game> listeId = new ArrayList<>();

            url = "http://"+vue.getIpServeur()+"/api/v0";

            listeId = Network.listInitializedGames(url);

            System.out.println(url);
            System.out.println(listeId);

            int i=0;
            boolean existe = false;
            g = new Game(vue.getIdText());
            while(i<listeId.size() && !existe){
                System.out.println(listeId.get(i).getId() + " " + vue.getIdText());
                if(listeId.get(i).getId() == vue.getIdText()){
                    existe = true;
                    System.out.println("test");
                }
                i++;
            }
            System.out.println(existe);

            if(existe){
                //System.out.println("url : " + url + " ID : " + vue.getIdText() + " player : " + p + " bateau : " + modele.toString());
                System.out.println("TEST : " + modele.getBateaux());
                System.out.println("url : " + url + " id : " + vue.getIdText() + " player : " + p);
                Network.suscribeNewPlayer(url,p);
                System.out.println(n.joinGame(url,new Game(vue.getIdText()),p,modele.getBateaux()));
                System.out.println("Après affichage bateaux");
                if((Network.getInfo(url,g,p) != 1)){
                    vue.dispose();
                    Partie partie = new Partie(modele,vue.getJoueur(),g,this.url);

                }
                else{
                    JOptionPane.showMessageDialog(vue, "Attend le second joueur");
                }
            }
            else{
                JOptionPane.showMessageDialog(vue,"Game qui n'existe pas");
            }

        } catch (UnirestException e1) {
            JOptionPane.showMessageDialog(vue,"Pas de serveur à cet IP");
        } catch (UncompleteFleetException e1) {
            e1.printStackTrace();
        } catch (BadCoordException e1) {
            e1.printStackTrace();
        } catch (BadIdException e1) {
            e1.printStackTrace();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        //vue.attenteSecondJoueur();
    }
}