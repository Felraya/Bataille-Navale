package Application;

import Listener.*;
import info1.network.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CompositionFlotte extends JFrame {
    private Principale accueil;
    private Player joueur;
    private JLabel titre_num1;
    private JLabel titre_num2;
    private JLabel titre_num3;
    private JLabel titre_num4;
    private JLabel titre_num5;
    private JLabel titre_compteur;
    private JLabel compteur;
    private JLabel titre_liste;
    private JLabel titre_liste_decalage;
    private JLabel titre_liste_num1;
    private JLabel titre_liste_num2;
    private JLabel titre_liste_num3;
    private JLabel titre_liste_num4;
    private JLabel titre_liste_num5;
    private int compteur_liste_num1 = 0;
    private int compteur_liste_num2 = 0;
    private int compteur_liste_num3 = 0;
    private int compteur_liste_num4 = 0;
    private int compteur_liste_num5 = 0;
    private JLabel liste_num1;
    private JLabel liste_num2;
    private JLabel liste_num3;
    private JLabel liste_num4;
    private JLabel liste_num5;
    private JButton submit;
    private int valeur_compteur = 20;
    private JButton num1;
    private JButton num2;
    private JButton num3;
    private JButton num4;
    private JButton num5;
    private JButton remove_num1;
    private JButton remove_num2;
    private JButton remove_num3;
    private JButton remove_num4;
    private JButton remove_num5;

    private JButton flotteAlea;

    public CompositionFlotte(Principale p) throws HeadlessException {
        super("Bataille Navale");
        this.accueil = p;
        joueur=accueil.getPlayer();
        this.titre_num1 = new JLabel("Submarine (coût = 1): ");
        this.titre_num2 = new JLabel("Destroyer (coût = 2): ");
        this.titre_num3 = new JLabel("Cruiser (coût = 3): ");
        this.titre_num4 = new JLabel("Battleship (coût = 4): ");
        this.titre_num5 = new JLabel("Aircraft Carrier (coût = 5): ");
        this.num1 = new JButton("Add");
        this.num2 = new JButton("Add");
        this.num3 = new JButton("Add");
        this.num4 = new JButton("Add");
        this.num5 = new JButton("Add");
        this.remove_num1 = new JButton("Remove");
        this.remove_num2 = new JButton("Remove");
        this.remove_num3 = new JButton("Remove");
        this.remove_num4 = new JButton("Remove");
        this.remove_num5 = new JButton("Remove");
        this.titre_compteur = new JLabel("Points restants: ");
        this.compteur = new JLabel("20");
        this.titre_liste = new JLabel(" ");
        this.titre_liste_decalage = new JLabel(" ");
        this.titre_liste_num1 = new JLabel("Nombre Submarine: ");
        this.titre_liste_num2 = new JLabel("Nombre Destroyer: ");
        this.titre_liste_num3 = new JLabel("Nombre Cruiser: ");
        this.titre_liste_num4 = new JLabel("Nombre Battleship: ");
        this.titre_liste_num5 = new JLabel("Nombre Aircraft carrier: ");
        this.liste_num1 = new JLabel("0");
        this.liste_num2 = new JLabel("0");
        this.liste_num3 = new JLabel("0");
        this.liste_num4 = new JLabel("0");
        this.liste_num5 = new JLabel("0");
        this.submit = new JButton("Ajouter");

        this.flotteAlea = new JButton("Générer une flotte aléatoire");

        //Pannel Est
        JPanel principal = new JPanel();
        principal.setLayout(new BorderLayout());
        JPanel pannel_east = new JPanel();
        GridLayout grid_east = new GridLayout(4, 1);
        pannel_east.setLayout(grid_east);
        pannel_east.add(titre_compteur);
        pannel_east.add(compteur);
        pannel_east.add(titre_liste);
        pannel_east.add(titre_liste_decalage);
        pannel_east.add(titre_liste_num1);
        pannel_east.add(liste_num1);
        pannel_east.add(titre_liste_num2);
        pannel_east.add(liste_num2);
        pannel_east.add(titre_liste_num3);
        pannel_east.add(liste_num3);
        pannel_east.add(titre_liste_num4);
        pannel_east.add(liste_num4);
        pannel_east.add(titre_liste_num5);
        pannel_east.add(liste_num5);

        //Pannel Ouest
        JPanel pannel_west = new JPanel();
        GridLayout grid_west = new GridLayout(6, 3);
        pannel_west.setLayout(grid_west);
        pannel_west.add(titre_num1);
        pannel_west.add(num1);
        pannel_west.add(remove_num1);
        pannel_west.add(titre_num2);
        pannel_west.add(num2);
        pannel_west.add(remove_num2);
        pannel_west.add(titre_num3);
        pannel_west.add(num3);
        pannel_west.add(remove_num3);
        pannel_west.add(titre_num4);
        pannel_west.add(num4);
        pannel_west.add(remove_num4);
        pannel_west.add(titre_num5);
        pannel_west.add(num5);
        pannel_west.add(remove_num5);
        pannel_west.add(flotteAlea);

        //Pannel sud
        JPanel pannel_south = new JPanel();
        pannel_south.setLayout(new FlowLayout());
        pannel_south.add(submit);

        //Ajout des panneaux est, ouest et sud sur le principal
        principal.add(pannel_east, BorderLayout.EAST);
        principal.add(pannel_west, BorderLayout.WEST);
        principal.add(pannel_south, BorderLayout.SOUTH);
        this.getContentPane().add(principal);
        this.setPreferredSize(new Dimension(1300, 200));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setVisible(true);

        //Listener
        ValidationFlotte va = new ValidationFlotte(this, accueil);
        submit.addActionListener(va);

        GenerateFlotte gf = new GenerateFlotte(this);
        flotteAlea.addActionListener(gf);

        AddSubmarine adds = new AddSubmarine(this);
        num1.addActionListener(adds);

        AddDestroyer addd = new AddDestroyer(this);
        num2.addActionListener(addd);

        AddCruiser addc = new AddCruiser(this);
        num3.addActionListener(addc);

        AddBattleships addb = new AddBattleships(this);
        num4.addActionListener(addb);

        AddAircraft adda = new AddAircraft(this);
        num5.addActionListener(adda);

        RemoveSubmarine rs = new RemoveSubmarine(this);
        remove_num1.addActionListener(rs);

        RemoveDestroyer rd = new RemoveDestroyer(this);
        remove_num2.addActionListener(rd);

        RemoveCruiser rc = new RemoveCruiser(this);
        remove_num3.addActionListener(rc);

        RemoveBattleships rb = new RemoveBattleships(this);
        remove_num4.addActionListener(rb);

        RemoveAircraft ra = new RemoveAircraft(this);
        remove_num5.addActionListener(ra);
    }

    //Méthodes pour le compteur de points
    public String getCompteur() {
        return compteur.getText();
    }

    public int getValeur_compteur(){
        return valeur_compteur;
    }

    public void setCompteur(int i) {
        String tmp =  String.valueOf(i);
        compteur.setText(tmp);
    }

    public void decrement_compteur(){
        this.valeur_compteur--;
    }

    public void increment_compteur(){
        this.valeur_compteur++;
    }

    //Méthodes pour le compteur de sous marin
    public void incrementCompteurSubmarine() {
        this.compteur_liste_num1++;
    }

    public void decrementCompteurSubmarine() {
        this.compteur_liste_num1--;
    }

    public void setCompteurSubmarine(int i) {
        String tmp =  String.valueOf(i);
        liste_num1.setText(tmp);
    }

    public int getValeur_compteur_submarine(){
        return this.compteur_liste_num1;
    }

    //Méthode pour le compteur de Destroyer
    public void incrementCompteurDestroyer() {
        this.compteur_liste_num2++;
    }

    public void decrementCompteurDestroyer() {
        this.compteur_liste_num2--;
    }

    public void setCompteurDestroyer(int i) {
        String tmp =  String.valueOf(i);
        liste_num2.setText(tmp);
    }

    public int getValeur_compteur_Destroyer(){
        return this.compteur_liste_num2;
    }

    //Méthodes pour le compteur de Cruiser
    public void incrementCompteurCruiser() {
        this.compteur_liste_num3++;
    }

    public void decrementCompteurCruiser() {
        this.compteur_liste_num3--;
    }

    public void setCompteurCruiser(int i) {
        String tmp =  String.valueOf(i);
        liste_num3.setText(tmp);
    }

    public int getValeur_compteur_Cruiser(){
        return this.compteur_liste_num3;
    }

    //Méthodes pour le compteur de Battleships
    public void incrementCompteurBattlleships() {
        this.compteur_liste_num4++;
    }

    public void decrementCompteurBattlleships() {
        this.compteur_liste_num4--;
    }

    public void setCompteurBattlleships(int i) {
        String tmp =  String.valueOf(i);
        liste_num4.setText(tmp);
    }

    public int getValeur_compteur_Battlleships(){
        return this.compteur_liste_num4;
    }

    //Méthodes pour le compteur de Aircraft Carrier
    public void incrementCompteurAircraft() {
        this.compteur_liste_num5++;
    }

    public void decrementCompteurAircraft() {
        this.compteur_liste_num5--;
    }

    public void setCompteurAircraft(int i) {
        String tmp =  String.valueOf(i);
        liste_num5.setText(tmp);
    }

    public int getValeur_compteur_Aircraft(){
        return this.compteur_liste_num5;
    }

    public Player getPlayer() {
        return this.joueur;
    }

    
    public void maj_All(){
        //submarine
        if(getCompteur().equals("0")){
            num1.setEnabled(false);
        }
        else{
            num1.setEnabled(true);
        }

        if(getValeur_compteur_submarine()<=0){
            remove_num1.setEnabled(false);
        }
        else{
            remove_num1.setEnabled(true);
        }

        //destroyer
        if(getCompteur().equals("0")||getCompteur().equals("1")){
            num2.setEnabled(false);
        }
        else{
            num2.setEnabled(true);
        }

        if(getValeur_compteur_Destroyer()<=0){
            remove_num2.setEnabled(false);
        }
        else{
            remove_num2.setEnabled(true);
        }

        //cruiser
        if(getCompteur().equals("0")||getCompteur().equals("1")||getCompteur().equals("2")){
            num3.setEnabled(false);
        }
        else{
            num3.setEnabled(true);

        }

        if(getValeur_compteur_Cruiser()<=0){
            remove_num3.setEnabled(false);
        }
        else{
            remove_num3.setEnabled(true);
        }

        //BattleShip

        if(getCompteur().equals("0")||getCompteur().equals("1")||getCompteur().equals("2")||getCompteur().equals("3")){
            num4.setEnabled(false);
        }
        else{
            num4.setEnabled(true);
        }

        if(getValeur_compteur_Battlleships()<=0){
            remove_num4.setEnabled(false);
        }
        else{
            remove_num4.setEnabled(true);
        }

        //Aircraft Carrier

        if(getCompteur().equals("0")||getCompteur().equals("1")||getCompteur().equals("2")||getCompteur().equals("3")||getCompteur().equals("4")){
            num5.setEnabled(false);
        }
        else{
            num5.setEnabled(true);

        }

        if(getValeur_compteur_Aircraft()<=0){
            remove_num5.setEnabled(false);
        }
        else{
            remove_num5.setEnabled(true);
        }


    }


    public void declancherNum1(){
        num1.doClick();
    }

    public void declancherNum2(){
        num2.doClick();
    }

    public void declancherNum3(){
        num3.doClick();
    }

    public void declancherNum4(){
        num4.doClick();
    }

    public void declancherNum5(){
        num5.doClick();
    }

    public void reset(){
        valeur_compteur = 20;
        compteur_liste_num1 = 0;
        compteur_liste_num2 = 0;
        compteur_liste_num3 = 0;
        compteur_liste_num4 = 0;
        compteur_liste_num5 = 0;
        compteur.setText("20");
        liste_num1.setText("0");
        liste_num2.setText("0");
        liste_num3.setText("0");
        liste_num4.setText("0");
        liste_num5.setText("0");
        this.maj_All();


    }



}

