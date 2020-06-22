package Application;

import Listener.*;
import com.mashape.unirest.http.exceptions.UnirestException;
import info1.network.*;
import info1.ships.*;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLOutput;

public class FenetreDeConnexion extends JFrame {
    private JLabel titre_creer_salon;
    private JButton creer_salon;
    private JLabel titre_rejoindre_salon;
    private JButton rejoindre;
    private JButton lancer;
    private JPanel num1, num2, num3, num4,  global;
    private JTextField ip_join;
    private JLabel ip_create;
    private JTextField ipServeur;
    private Player joueur;
    private Network n;
    private Bataille modele;
    private boolean createur = false;

    public FenetreDeConnexion(Player p, Bataille modele) throws UnirestException {
        super("Fenêtre de connexion");
        this.joueur = p;
        this.modele = modele;

        System.out.println(joueur);


        JPanel panIp = new JPanel();
        JLabel labIp = new JLabel("Entrez l'ip du pc serveur : ");

        ipServeur = new JTextField();
        ipServeur.setColumns(7);
        panIp.add(labIp);
        panIp.add(ipServeur);

        //
        num1 = new JPanel();
        num2 = new JPanel();
        num3 = new JPanel();
        num4 = new JPanel();
        titre_creer_salon = new JLabel("Créer un salon: ");
        creer_salon = new JButton("Créer un salon");
        lancer = new JButton("Lancer la partie");
        ip_create = new JLabel(" ");
        rejoindre = new JButton("Rejoindre un salon");
        titre_rejoindre_salon = new JLabel("ID Game: ");
        changerLabel("ID game : créer pour définir");
        ip_join = new JTextField();

        //Sizes
        ip_create.setPreferredSize(new Dimension(200,  25));
        ip_join.setPreferredSize(new Dimension(200,25));

        //Ajout Panel1
        num1.add(titre_creer_salon);
        num1.add(creer_salon);

        //Ajout Panel2
        num2.add(ip_create);
        desactiverLancerPartie();
        num2.add(lancer);

        //Ajout Panel3
        num3.add(titre_rejoindre_salon);
        num3.add(ip_join);

        //Ajout Panel4
        num4.add(rejoindre);

        //Ajout sur Panel global
        global = new JPanel();
        global.setLayout(new GridLayout(4,2));
        global.add(num1);
        global.add(num2);

        global.add(num4);
        JPanel panButton = new JPanel();
        JButton buttonBot = new JButton("Jouer contre un bot");

        panButton.add(buttonBot);

        global.add(panButton);
        global.add(num3);
        global.add(panIp);


        this.getContentPane().add(global);
        this.setPreferredSize(new Dimension(600, 300));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setVisible(true);

        //Listener
        n = new Network();
        NewConnexion nc = new NewConnexion(this,n, joueur, modele);
        creer_salon.addActionListener(nc);


        JoinButton jb = new JoinButton(this,n,joueur,modele);
        rejoindre.addActionListener(jb);

        //Network



    }

    //Méthodes
    public void changerLabel(String s){
        this.ip_create.setText(s);
    }

    public String getIpServeur(){
        return ipServeur.getText();
    }
    public Player getJoueur(){return this.joueur;}
    public void attenteSecondJoueur(Network n, Game g) throws BadIdException, UnirestException, InterruptedException, BadCoordException {
        this.creer_salon.setText("Annuler la création de salon");
        this.rejoindre.setEnabled(false);
        this.ip_join.setEnabled(false);
        createur = true;

        LancerPartie lp = new LancerPartie(this,modele,n,joueur,createur,g);
        lancer.addActionListener(lp);


        this.changerLabel("ID game : " + g.getId());

        /*while(n.getInfo("http://localhost:3000/api/v0",g,joueur) == 1){
            Thread.sleep(5 * 1000);
            System.out.println(n.getInfo("http://localhost:3000/api/v0",g,joueur));
        }
        System.out.println("AAAAAAAAA");
        */
    }

    public void annulerSalon(){
        this.creer_salon.setText("Créer un salon");
        this.rejoindre.setEnabled(true);
        this.ip_join.setEnabled(true);
        createur = false;

    }

    public int getIdText(){
        return Integer.parseInt(ip_join.getText());
    }


    public String getLabel(){
        return creer_salon.getText();
    }


    public static void main(String[] args) throws UnirestException, BadCoordException, CoordsBadShipException, BadIdException {
        Player p1 = new Player("jeHost");
        Player p2 = new Player("JeJoin");

        NavyFleet n1 = new NavyFleet();
        n1.add(new AircraftCarrier("monPorteAvion", "E5", "E9"));
        n1.add(new Battleship("monCuirasse", "B2", "E2"));
        n1.add(new Submarine("monSousMarin", "G10"));
        n1.add(new Cruiser("monCroiseur", "B8", "B6"));
        n1.add(new Destroyer("monTorpilleur", "H3", "H4"));
        n1.add(new Destroyer("autreTorpilleur", "D9", "C9"));
        n1.add(new Cruiser("autreCroiseur", "J8", "H8"));

        NavyFleet n2 = new NavyFleet();
        n2.add(new AircraftCarrier("monPorteAvion", "E5", "E9"));
        n2.add(new Battleship("monCuirasse", "B2", "E2"));
        n2.add(new Submarine("monSousMarin", "G10"));
        n2.add(new Cruiser("monCroiseur", "B8", "B6"));
        n2.add(new Destroyer("monTorpilleur", "H3", "H4"));
        n2.add(new Destroyer("autreTorpilleur", "D9", "C9"));
        n2.add(new Cruiser("autreCroiseur", "J8", "H8"));
        Bataille bat1 = new Bataille(n1);
        Bataille bat2 = new Bataille(n2);
        FenetreDeConnexion f1 = new FenetreDeConnexion(p1,bat1);
        FenetreDeConnexion f2 = new FenetreDeConnexion(p2,bat2);

        //Partie partie = new Partie(n1,p1,new Game(5));
        //partie.addTireLoupe(new Coord("A1"),2);
    }

    public void desactiverLancerPartie(){
        this.lancer.setEnabled(false);
    }

    public void activerLancerPartie(){
        this.lancer.setEnabled(true);
    }

    public void desactiverJoinPartie(){
        this.rejoindre.setEnabled(false);
    }

    public void activerJoinPartie(){
        this.rejoindre.setEnabled(true);
    }

    public void desactiverEmplacementIP(){
        this.ipServeur.setEnabled(false);
    }

    public void activerEmplacementIP(){
        this.ipServeur.setEnabled(true);
    }

    public int tailleCaractereDansTextField(){
        return ip_join.getText().length();
    }
}
