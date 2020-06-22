package Application;

import Listener.*;
import info1.network.*;
import info1.ships.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class FenetrePlacement extends JFrame{
    private Player joueur;
    private NavyFleet modele;
    private ArrayList<Integer> l;
    private JTextField coordonneDep;
    private JTextField coordonneArr;
    private JLabel cruiser;
    private JLabel destroyer;
    private JLabel subMarine;
    private JLabel aircraftCarrier;
    private JLabel battleship;
    private JPanel[][] tableau;
    private JPanel affichage;
    private JButton jouer;
    private JButton ajouter;
    private JButton placementAlea;
    private CommencerPartie cp;

    public static String[] AlphaX = {"_", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};

    public FenetrePlacement(ArrayList<Integer> l, Player p ){
        super("Placement de la flotte");
        this.l = l;
        this.joueur = p;
        modele = new NavyFleet();

        JPanel main = new JPanel();
        this.setContentPane(main);
        //NORTH
        main.setLayout(new BorderLayout());
        JPanel coordDepPan = new JPanel();
        JPanel coordArrPan = new JPanel();
        JLabel coDep = new JLabel("Coordonnées de départ");
        JLabel coArr = new JLabel("Coordonnées d'arrivé");
        coordonneArr = new JTextField();
        coordonneDep = new JTextField();
        coordonneDep.setColumns(7);
        coordonneArr.setColumns(7);

        coordDepPan.add(coDep);
        coordDepPan.add(coordonneDep);
        coordArrPan.add(coArr);
        coordArrPan.add(coordonneArr);
        JPanel north = new JPanel();
        north.add(coordDepPan);
        north.add(coordArrPan);
        ajouter = new JButton("Envoyer");
        placementAlea = new JButton("Placement aléatoire");

        AjouterBateaux controleurAjouter = new AjouterBateaux(modele,this);
        ajouter.addActionListener(controleurAjouter);

        PlacementAuto pa = new PlacementAuto(this);
        placementAlea.addActionListener(pa);

        north.add(ajouter);
        north.add(placementAlea);
        main.add(north,BorderLayout.NORTH);
        //CENTER
        affichage = new JPanel();
        affichage.setLayout(new GridLayout(11,11));


        tableau = new JPanel[11][11];

        char b='A';
        int a = 1;

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
                else if(i == 0 && j == i){
                    tmp.setBackground(Color.WHITE);
                    tmp.add(new JLabel("X"));
                }
                else{
                    tmp.setBackground(new Color(71,177,230));

                }
                tableau[j][i] = tmp;
            }
        }

        this.affichage();


        main.add(affichage,BorderLayout.CENTER);
        //WEST
        JPanel types = new JPanel();
        types.setLayout(new BoxLayout(types,BoxLayout.Y_AXIS));
        JPanel nbCruiser = new JPanel();
        subMarine = new JLabel("Nb de submarine (1) :"+ l.get(0));
        destroyer = new JLabel("Nb de destroyer (2) :" + l.get(1));
        cruiser = new JLabel("Nb de cruisers (3) :"+ l.get(2));
        battleship = new JLabel("Nb de battleship (4) :"+ l.get(3));
        aircraftCarrier = new JLabel("Nb de aircraft carrier (5) :"+ l.get(4));
        jouer = new JButton("Prêt");
        cp = new CommencerPartie(modele, this,joueur);
        jouer.addActionListener(cp);


        if(!modele.isComplete()){
            System.out.println("flote pleine : "+modele.isComplete());
            this.enableChanger(false);
        }

        types.add(subMarine);
        types.add(destroyer);
        types.add(cruiser);
        types.add(battleship);
        types.add(aircraftCarrier);
        types.add(jouer);
        types.add(nbCruiser);

        types.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        main.add(types,BorderLayout.EAST);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setSize(new Dimension(800,600));
        this.setVisible(true);

    }
    public String getCoordDepart(){
        return coordonneDep.getText();
    }
    public String getCoordArrive(){
        return coordonneArr.getText();
    }

    public int getNbrCruiser() {
        return l.get(2);
    }

    public int getNbrDestroyer() {
        return l.get(1);
    }

    public int getNbrSubMarine() {
        return l.get(0);
    }

    public int getNbrAircraftCarrier() {
        return l.get(4);
    }


    public int getNbrBattleship() {
        return l.get(3);
    }

    public void setBattleship(JLabel battleship) {
        this.battleship = battleship;
    }



    public void changerNbCruiserRestant(int i){
        this.cruiser.setText("Nb de cruisers (3) :"+i);


    }
    public void changerNbsubMarineRestant(int i){
        this.subMarine.setText("Nb de submarine (1) :"+i);

    }
    public void changerNbAircraftCarrierRestant(int i){
        this.aircraftCarrier.setText("Nb de Aircraft Carrier (5) :"+i);

    }
    public void changerNbDestroyerRestant(int i){
        this.destroyer.setText("Nb de destroyer (2) :"+i);
    }

    public void changerNbBattleShipRestant(int i){
        this.battleship.setText("Nb de battleship (4) :"+i);
    }

    public void affichage(){
        for(int i=0; i<11; i++){
            for(int j=0; j<11; j++){
                affichage.add(tableau[i][j]);
            }
        }
    }
    public void afficherBateau(Ship s,Color c){
        for(ICoord i:s.getCoords()) {

            System.out.println("x : "+i.getX());
            System.out.println("y : "+i.getY());

            tableau[i.getY()][i.getX()].setBackground(c);
        }
    }
    public void enableChanger(boolean b){
        this.jouer.setEnabled(b);
    }
    public void enableAjouter(boolean b){
        this.ajouter.setEnabled(b);
    }
    public void viderTextField(){
        coordonneDep.setText("");
        coordonneArr.setText("");
    }

    public ArrayList<Integer> getNbBateaux(){
        return l;
    }




    //NOUV FONCTION POUR PLACEMENT AUTO

    public void reset(){
        this.subMarine.setText("Nb de submarine (1) : 0");
        this.destroyer.setText("Nb de destroyer (2) : 0");
        this.cruiser.setText("Nb de cruisers (3) : 0");
        this.battleship.setText("Nb de battleship (4) : 0");
        this.aircraftCarrier.setText("Nb de aircraft carrier (5) : 0");
        this.modele = new NavyFleet();

        char b='A';
        int a = 1;

        for(int i=1; i<11; i++){
            for(int j=1; j<11; j++){
                tableau[j][i].setBackground(new Color(71,177,230));
            }
        }
    }


    public void setFloot(NavyFleet f){
        this.modele = f;
        cp.setFlotte(this.modele);
    }


    public static Coord caseRandom() throws BadCoordException {
        return new Coord(AlphaX[(int) (1 + (Math.random() * (10+1 - 1)))] + (int) (1 + (Math.random() * (10+1 - 1))));
    }

    public static boolean possible(Coord c, ArrayList<Coord> caseTiree){
        for(int i=0; i<caseTiree.size(); i++){
            if(caseTiree.contains(c)) return false;
        }
        return true;
    }


    public static Coord case2Random(int maxX, int maxY) throws BadCoordException {
        return new Coord(AlphaX[(int) (1 + (Math.random() * (maxX+1 - 1)))] + (int) (1 + (Math.random() * (maxY+1 - 1))));
    }

    public static int sensRandom(){
        return (int) (Math.random()*2);
    }


    public static Coord ajoutSurCoord(Coord c, int ajoutX, int ajoutY) throws BadCoordException {
        return new Coord(AlphaX[c.getX()+ajoutX]+String.valueOf(c.getY()+ajoutY));
    }

    public static AircraftCarrier newAircraft() throws BadCoordException, CoordsBadShipException {
        if(sensRandom() == 1){ //mettre bateau en colonne
            System.out.println("Bateau en colonne");
            Coord tmp = case2Random(10,10-4);
            System.out.println("tmp : " + tmp);
            System.out.println("tmp+5 : " + ajoutSurCoord(tmp,0,4));
            return new AircraftCarrier("monPorteAvion", tmp.toString(), ajoutSurCoord(tmp,0,4).toString());
        }
        else{ //mettre bateau en ligne (Sur le meme Y)
            System.out.println("Bateau en ligne");
            Coord tmp = case2Random(10-4,10);
            System.out.println("tmp : " + tmp);
            System.out.println("tmp+5 : " + ajoutSurCoord(tmp,4,0));
            return new AircraftCarrier("monPorteAvion", tmp.toString(), ajoutSurCoord(tmp,4,0).toString());
        }
    }

    public static Battleship newBattleship() throws BadCoordException, CoordsBadShipException {
        if(sensRandom() == 1){ //mettre bateau en colonne
            Coord tmp = case2Random(10,10-3);
            System.out.println("tmp : " + tmp);
            System.out.println("tmp+5 : " + ajoutSurCoord(tmp,0,3));
            return new Battleship("bateau", tmp.toString(), ajoutSurCoord(tmp,0,3).toString());
        }
        else{ //mettre bateau en ligne (Sur le meme Y)
            System.out.println("Bateau en ligne");
            Coord tmp = case2Random(10-3,10);
            System.out.println("tmp : " + tmp);
            System.out.println("tmp+5 : " + ajoutSurCoord(tmp,3,0));
            return new Battleship("monPorteAvion", tmp.toString(), ajoutSurCoord(tmp,3,0).toString());
        }
    }

    public static Cruiser newCruiser() throws BadCoordException, CoordsBadShipException {
        if(sensRandom() == 1){ //mettre bateau en colonne
            Coord tmp = case2Random(10,10-2);
            System.out.println("tmp : " + tmp);
            System.out.println("tmp+5 : " + ajoutSurCoord(tmp,0,2));
            return new Cruiser("bateau", tmp.toString(), ajoutSurCoord(tmp,0,2).toString());
        }
        else{ //mettre bateau en ligne (Sur le meme Y)
            System.out.println("Bateau en ligne");
            Coord tmp = case2Random(10-2,10);
            System.out.println("tmp : " + tmp);
            System.out.println("tmp+5 : " + ajoutSurCoord(tmp,2,0));
            return new Cruiser("monPorteAvion", tmp.toString(), ajoutSurCoord(tmp,2,0).toString());
        }
    }

    public static Destroyer newDestroyer() throws BadCoordException, CoordsBadShipException {
        if(sensRandom() == 1){ //mettre bateau en colonne
            Coord tmp = case2Random(10,10-1);
            System.out.println("tmp : " + tmp);
            System.out.println("tmp+5 : " + ajoutSurCoord(tmp,0,1));
            return new Destroyer("bateau", tmp.toString(), ajoutSurCoord(tmp,0,1).toString());
        }
        else{ //mettre bateau en ligne (Sur le meme Y)
            System.out.println("Bateau en ligne");
            Coord tmp = case2Random(10-1,10);
            System.out.println("tmp : " + tmp);
            System.out.println("tmp+5 : " + ajoutSurCoord(tmp,1,0));
            return new Destroyer("monPorteAvion", tmp.toString(), ajoutSurCoord(tmp,1,0).toString());
        }
    }

    public static Submarine newSubmarine() throws BadCoordException, CoordsBadShipException {
        Coord tmp = case2Random(10,10);
        System.out.println("tmp : " + tmp);
        return new Submarine("bateau", tmp.toString());

    }

    public void disableBoutonAlea() {
        placementAlea.setEnabled(false);
    }
}
