package Application;
import Listener.*;
import com.mashape.unirest.http.exceptions.UnirestException;
import info1.network.*;
import info1.ships.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class Partie extends JFrame{

    private JLabel nom_Player1;
    private JLabel nom_Player2;

    private JPanel principale;
    private JPanel PanneauJoueurs;
    private JPanel PanneauInformation;
    private JPanel Panel_Player1;
    private JPanel Panel_Player2;
    private JPanel Grille_Player1;
    private JPanel Grille_Player2;

    private JTextField coordonneSelect;

    private JPanel tableauP1[][];
    private JPanel tableauP2[][];



    private String ip;
    private JButton lancer_boulet;
    private JLabel tour;
    private JLabel LabelTouche;
    private JLabel LabelTotal;
    private Player leJoueur;
    private Bataille modele;
    private Game partie;
    private JLabel etatTire;


    public Partie(Bataille n,Player p,Game partie,String url) throws BadCoordException, BadIdException, UnirestException, InterruptedException {
        super("Déroulement de la partie");
        this.ip = url;

        this.partie = partie;
        leJoueur = p;

        modele = n;
        //Déclaration des Panels
        principale = new JPanel();
        PanneauJoueurs = new JPanel();
        PanneauInformation = new JPanel();

        principale.setLayout(new BorderLayout());
        principale.add(PanneauJoueurs, BorderLayout.CENTER);
        principale.add(PanneauInformation, BorderLayout.SOUTH);

        Panel_Player1 = new JPanel();
        Panel_Player2 = new JPanel();
        Grille_Player1 = new JPanel();
        Grille_Player2 = new JPanel();


        nom_Player1 = new JLabel(leJoueur.getName());
        //nom_Player1 = new JLabel();
        nom_Player1.setHorizontalAlignment(JLabel.CENTER);
        nom_Player1.setVerticalAlignment(JLabel.CENTER);
        nom_Player2 = new JLabel("Player 2");
        System.out.println(partie);
        //nom_Player2 = new JLabel(partie.getGuest().getName());
        nom_Player2.setHorizontalAlignment(JLabel.CENTER);
        nom_Player2.setVerticalAlignment(JLabel.CENTER);


        //Rangement
        PanneauJoueurs.setLayout(new GridLayout());
        PanneauJoueurs.add(Panel_Player1);
        PanneauJoueurs.add(Panel_Player2);

        Panel_Player1.setLayout(new BorderLayout());
        Panel_Player2.setLayout(new BorderLayout());
        Panel_Player1.add(nom_Player1, BorderLayout.NORTH);
        Panel_Player2.add(nom_Player2, BorderLayout.NORTH);
        Panel_Player1.add(Grille_Player1, BorderLayout.CENTER);
        Panel_Player2.add(Grille_Player2, BorderLayout.CENTER);


        Grille_Player1.setLayout(new GridLayout(11,11));
        Grille_Player2.setLayout(new GridLayout(11,11));


        tableauP1 = new JPanel[11][11];
        tableauP2 = new JPanel[11][11];

        this.initialiserBoard();
        this.afficherBateaux(modele.getBateaux());




        PanneauInformation.setLayout(new FlowLayout());
        LabelTouche = new JLabel("    Tire touché : " + modele.getTouche());
        PanneauInformation.add(LabelTouche);
        LabelTotal = new JLabel("    Tire total : " + modele.getTotalTirs());
        PanneauInformation.add(LabelTotal);
        etatTire = new JLabel("     Tirez");
        PanneauInformation.add(etatTire);



        JPanel panCord = new JPanel();

        JLabel labDep = new JLabel("    Cord tire : ");

        coordonneSelect = new JTextField();
        coordonneSelect.setColumns(7);
        ValiderLancer vl =new ValiderLancer(this);
        coordonneSelect.addCaretListener(vl);
        tour = new JLabel();

        panCord.add(labDep);
        panCord.add(coordonneSelect);
        lancer_boulet = new JButton("LANCER");
        JouerCase contJouer = new JouerCase(this,modele,this.ip);
        lancer_boulet.addActionListener(contJouer);
        PanneauInformation.add(panCord);

        PanneauInformation.add(lancer_boulet);



        this.affichage();

        this.getContentPane().add(principale);
        this.getContentPane().addMouseMotionListener(new ControleurTour(modele,this));

        this.setPreferredSize(new Dimension(900, 400));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setVisible(true);




    }

    public void updateAff(){
        this.update(this.getGraphics());
    }

    public void initialiserBoard(){
        char a='A';
        int b = 1;

        for(int i=0; i<11; i++){
            for(int j=0; j<11; j++){
                JPanel tmp = new JPanel();
                tmp.setBorder(BorderFactory.createEtchedBorder());


                if(i == 0 && j != 0){
                    tmp.setBackground(Color.WHITE);
                    tmp.add(new JLabel(String.valueOf(a)));
                    a++;
                }
                else if(i != 0 && j == 0){
                    tmp.setBackground(Color.WHITE);
                    tmp.add(new JLabel(String.valueOf(b)));
                    b++;
                }
                else if(i == 0 && j == 0){
                    tmp.setBorder(BorderFactory.createEmptyBorder());
                    tmp.add(new JLabel(""));
                }
                else{
                    tmp.setBackground(new Color(41,182,246));

                }

                tableauP1[i][j] = tmp;
            }

        }


        a='A';
        b = 1;

        for(int i=0; i<11; i++){
            for(int j=0; j<11; j++){
                JPanel tmp = new JPanel();
                tmp.setBorder(BorderFactory.createEtchedBorder());


                if(i == 0 && j != 0){
                    tmp.setBackground(Color.WHITE);
                    tmp.add(new JLabel(String.valueOf(a)));
                    a++;
                }
                else if(i != 0 && j == 0){
                    tmp.setBackground(Color.WHITE);
                    tmp.add(new JLabel(String.valueOf(b)));
                    b++;
                }
                else if(i == 0 && j == 0){
                    tmp.setBorder(BorderFactory.createEmptyBorder());
                    tmp.add(new JLabel(""));
                }
                else{
                    tmp.setBackground(new Color(41,182,246));

                }

                tableauP2[i][j] = tmp;
            }

        }
    }
    public void affichage(){
        System.out.println("Tesaaaaaaaaaaaaaaaaaaat");
        for(int i=0; i<11; i++){
            for(int j=0; j<11; j++){
                Grille_Player1.add(tableauP1[i][j]);
                Grille_Player2.add(tableauP2[i][j]);
            }
        }
    }
    public void refresh(){
        JPanel res = new JPanel();
        for(int i = 0;i<modele.getBateaux().getShips().size();i++){
            for(int j = 0;j<modele.getBateaux().getShips().get(i).getCoords().size();j++){
                res.add(tableauP1[modele.getBateaux().getShips().get(i).getCoords().get(j).getY()][modele.getBateaux().getShips().get(i).getCoords().get(j).getX()]);
            }
        }
        this.Grille_Player1=res;
        JPanel res2 = new JPanel();
        for(int i=0; i<modele.getDejaJoue().size(); i++){
            res2.add(tableauP2[modele.getDejaJoue().get(i).getY()][modele.getDejaJoue().get(i).getX()]);
        }
        this.Grille_Player2 = res2;
    }
    public void setEnableButtonTire(boolean b){
        this.lancer_boulet.setEnabled(b);
    }
    public Game getPartie() {
        return partie;
    }
    public void setTextTour(String s){
        this.LabelTouche.setText(s);
    }
    public Player getPlayer(){
        return this.leJoueur;
    }
    public String getCoordSelected(){
        return coordonneSelect.getText();
    }

    public void addTireLoupe(Coord co){
        tableauP2[co.getY()][co.getX()].setBackground(new Color(210,210,210));
    }
    public String getIp(){
        return this.ip;
    }
    public void addTireTouche(Coord co){
        /*JLabel tireloupe = new JLabel("X");

        tireloupe.setForeground(Color.RED);
        tireloupe.setFont(new Font("Arial",Font.BOLD,16));
        tireloupe.setVerticalAlignment(JLabel.CENTER);

        tableauP2[co.getY()][co.getX()].add(tireloupe);
        this.updateAff();

        this.setTouche(modele.getTouche());
        this.setTotal(modele.getTotalTirs());*/

        tableauP2[co.getY()][co.getX()].setBackground(new Color(210,0,0));
        this.setTotal(modele.getTotalTirs());
    }

    public String getNom_Player1() {
        return nom_Player1.getText();
    }

    public void setNom_Player1(String nom) {
        this.nom_Player1.setText(nom);
    }

    public String getNom_Player2() {
        return nom_Player2.getText();
    }

    public void setNom_Player2(String nom) {
        this.nom_Player2.setText(nom);
    }

    public int getTotal(){
        return Integer.parseInt(LabelTotal.getText());
    }
    public void setTouche(int touche) {
        LabelTouche.setText("Tire touché : " + touche);
    }
    public void setTotal(int total) {
        LabelTotal.setText("Tire total : " + total);
    }

    public void afficherBateaux(NavyFleet n){
        for(int i = 0; i<n.getShips().size(); i++){
            switch(n.getShips().get(i).getCategory()){
                case AIRCRAFT_CARRIER:
                    for(int j = 0; j<n.getShips().get(i).getCoords().size(); j++){
                        tableauP1[n.getShips().get(i).getCoords().get(j).getY()][n.getShips().get(i).getCoords().get(j).getX()].setBackground(new Color(128, 107, 52));
                    }
                    break;
                case BATTLESHIP:
                    for(int j = 0; j<n.getShips().get(i).getCoords().size(); j++){
                        tableauP1[n.getShips().get(i).getCoords().get(j).getY()][n.getShips().get(i).getCoords().get(j).getX()].setBackground(new Color(163, 138, 67));
                    }
                    break;
                case SUBMARINE:
                    for(int j = 0; j<n.getShips().get(i).getCoords().size(); j++){
                        tableauP1[n.getShips().get(i).getCoords().get(j).getY()][n.getShips().get(i).getCoords().get(j).getX()].setBackground(new Color(255, 233, 173));
                    }
                    break;
                case CRUISER:
                    for(int j = 0; j<n.getShips().get(i).getCoords().size(); j++){
                        tableauP1[n.getShips().get(i).getCoords().get(j).getY()][n.getShips().get(i).getCoords().get(j).getX()].setBackground(new Color(204, 172, 84));
                    }
                    break;
                default:
                    for(int j = 0; j<n.getShips().get(i).getCoords().size(); j++){
                        tableauP1[n.getShips().get(i).getCoords().get(j).getY()][n.getShips().get(i).getCoords().get(j).getX()].setBackground(new Color(255, 215, 104));
                    }
                    break;
            }


        }
    }
    public void changerLancer(boolean b){
        lancer_boulet.setEnabled(b);
    }

    public String getCase(){
        return coordonneSelect.getText();
    }

    public void setEtatTire(String s){
        etatTire.setText(s);
    }



}