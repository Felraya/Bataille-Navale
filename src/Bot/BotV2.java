package Bot;

import Application.Partie;
import com.mashape.unirest.http.exceptions.UnirestException;
import info1.network.BadIdException;
import info1.network.Game;
import info1.network.Network;
import info1.network.Player;
import info1.ships.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.abs;


public class BotV2 {

    public static final String HTTP_LOCALHOST = "http://localhost:3000/api/v0";
    public static final int WAIT_TIME = 300;
    public static final String BOT = "Bob le petit Robot";

    public static String[] AlphaX = {"_", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};

    public static int MAX_X = 11;
    public static int MAX_Y = 11;


    public static void main(String[] args) throws BadCoordException, CoordsBadShipException, InterruptedException, BadIdException, UnirestException {
        ArrayList<Coord> caseTiree = new ArrayList<>();


        try {
            System.out.println("Lancement du bot " + BOT + "...");
            Player botPlayer = new Player(BOT);
            List<Player> players = Network.listActivePlayers(HTTP_LOCALHOST);
            if (!players.contains(botPlayer)) {
                if (Network.suscribeNewPlayer(HTTP_LOCALHOST, botPlayer)) {
                    System.out.println(BOT + " s'inscrit sur le serveur...");
                }
            }

            System.out.println("---------------");
            System.out.println("> Parties initialisées : " + Network.listInitializedGames(HTTP_LOCALHOST));

            System.out.println("---------------");
            NavyFleet flotte = newFlotteAlea();

            Bataille b = new Bataille(flotte);

            System.out.println("> Flotte : " + flotte.asJSON().toString());
            System.out.println("---------------");
            Game currentGame = Network.initNewGame(HTTP_LOCALHOST, botPlayer, flotte);
            System.out.println("> Parties initialisées : " + Network.listInitializedGames(HTTP_LOCALHOST));
            System.out.println("---------------");
            System.out.println("> Attente qu'un joueur rejoigne la partie...");


            int res = 1;
            while (res == 1) {
                Thread.sleep(WAIT_TIME);
                res = Network.getInfo(HTTP_LOCALHOST, currentGame, botPlayer);
                System.out.print(".");
            }
            System.out.print("OK ; \n La partie peut commencer...");

            Partie p6 = new Partie(b, botPlayer, currentGame, HTTP_LOCALHOST);
            res = Network.getInfo(HTTP_LOCALHOST, currentGame, botPlayer);

            int x = 1;
            int y = 1;
            boolean partieTerminee = false;
            while (!partieTerminee) {
                if (res == 10) {


                    Coord shoot = caseRandom();

                    while (!possible(shoot, caseTiree)) {
                        System.out.println(shoot);
                        shoot = caseRandom();
                    }


                    caseTiree.add(shoot);


                    System.out.println("\n" + BOT + " tire en " + shoot.toString());

                    int res2 = Network.playOneTurn(HTTP_LOCALHOST, currentGame, botPlayer, shoot);
                    if (res2 == 0) {
                        System.out.println("   (manqué)");
                        p6.addTireLoupe(shoot);
                    } else if (res2 == 1) {
                        System.out.println("   > touché <");
                        p6.addTireTouche(shoot);
                    } else if (res2 == 10) {
                        System.out.println("   >> coulé <<");
                        p6.addTireTouche(shoot);
                    } else if (res2 == 100) {
                        System.out.println("   ** Partie gagnée par " + BOT + " **");
                        JOptionPane.showMessageDialog(p6,"** Partie gagnée par " + BOT +" **");
                        partieTerminee = true;
                    }
                } else if (res == -100) {
                    System.out.println("   ((( Partie perdue )))");
                    JOptionPane.showMessageDialog(p6,"Partie perdu par le bot");
                    partieTerminee = true;
                } else {
                    System.out.print(".");
                    Thread.sleep(WAIT_TIME);
                }
                res = Network.getInfo(HTTP_LOCALHOST, currentGame, botPlayer);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (UnirestException e) {
            System.out.println("Il faut commencer par lancer le serveur...");
        } catch (BadCoordException e) {
            e.printStackTrace();
        } catch (CoordsBadShipException e) {
            e.printStackTrace();
        } catch (UncompleteFleetException e) {
            e.printStackTrace();
        } catch (BadIdException e) {
            e.printStackTrace();
        }
    }

    public static Coord caseRandom() throws BadCoordException {
        return new Coord(AlphaX[(int) (1 + (Math.random() * (MAX_X - 1)))] + (int) (1 + (Math.random() * (MAX_Y - 1))));
    }

    public static boolean possible(Coord c, ArrayList<Coord> caseTiree) {
        for (int i = 0; i < caseTiree.size(); i++) {
            if (caseTiree.contains(c)) return false;
        }
        return true;
    }

    public static Coord case2Random(int maxX, int maxY) throws BadCoordException {
        return new Coord(AlphaX[(int) (1 + (Math.random() * (maxX - 1)))] + (int) (1 + (Math.random() * (maxY - 1))));
    }

    public static int sensRandom() {
        return (int) (Math.random() * 2);
    }

    public static NavyFleet newFlotteAlea() throws BadCoordException, CoordsBadShipException {
        NavyFleet f = new NavyFleet();


        AircraftCarrier tmpAir = newAircraft();
        while (!f.coordsArePossible(tmpAir)) {
            tmpAir = newAircraft();
        }
        f.add(tmpAir);

        Battleship tmpBat = newBattleship();
        while (!f.coordsArePossible(tmpBat)) {
            tmpBat = newBattleship();
        }
        f.add(tmpBat);

        Submarine tmpSub = newSubmarine();
        while (!f.coordsArePossible(tmpSub)) {
            tmpSub = newSubmarine();
        }
        f.add(tmpSub);

        Cruiser tmpCru = newCruiser();
        while (!f.coordsArePossible(tmpCru)) {
            tmpCru = newCruiser();
        }
        f.add(tmpCru);

        Destroyer tmpDes = newDestroyer();
        while (!f.coordsArePossible(tmpDes)) {
            tmpDes = newDestroyer();
        }
        f.add(tmpDes);

        Destroyer tmpDes2 = newDestroyer();
        while (!f.coordsArePossible(tmpDes2)) {
            tmpDes2 = newDestroyer();
        }
        f.add(tmpDes2);

        Cruiser tmpCru2 = newCruiser();
        while (!f.coordsArePossible(tmpCru2)) {
            tmpCru2 = newCruiser();
        }
        f.add(tmpCru2);

        return f;
    }

    public static Coord ajoutSurCoord(Coord c, int ajoutX, int ajoutY) throws BadCoordException {
        return new Coord(AlphaX[c.getX() + ajoutX] + String.valueOf(c.getY() + ajoutY));
    }

    public static AircraftCarrier newAircraft() throws BadCoordException, CoordsBadShipException {
        if (sensRandom() == 1) { //mettre bateau en colonne
            System.out.println("Bateau en colonne");
            Coord tmp = case2Random(MAX_X, MAX_Y - 4);
            System.out.println("tmp : " + tmp);
            System.out.println("tmp+5 : " + ajoutSurCoord(tmp, 0, 4));
            return new AircraftCarrier("monPorteAvion", tmp.toString(), ajoutSurCoord(tmp, 0, 4).toString());
        } else { //mettre bateau en ligne (Sur le meme Y)
            System.out.println("Bateau en ligne");
            Coord tmp = case2Random(MAX_X - 4, MAX_Y);
            System.out.println("tmp : " + tmp);
            System.out.println("tmp+5 : " + ajoutSurCoord(tmp, 5, 0));
            return new AircraftCarrier("monPorteAvion", tmp.toString(), ajoutSurCoord(tmp, 4, 0).toString());
        }
    }

    public static Battleship newBattleship() throws BadCoordException, CoordsBadShipException {
        if (sensRandom() == 1) { //mettre bateau en colonne
            Coord tmp = case2Random(MAX_X, MAX_Y - 3);
            System.out.println("tmp : " + tmp);
            System.out.println("tmp+5 : " + ajoutSurCoord(tmp, 0, 3));
            return new Battleship("bateau", tmp.toString(), ajoutSurCoord(tmp, 0, 3).toString());
        } else { //mettre bateau en ligne (Sur le meme Y)
            System.out.println("Bateau en ligne");
            Coord tmp = case2Random(MAX_X - 3, MAX_Y);
            System.out.println("tmp : " + tmp);
            System.out.println("tmp+5 : " + ajoutSurCoord(tmp, 3, 0));
            return new Battleship("monPorteAvion", tmp.toString(), ajoutSurCoord(tmp, 3, 0).toString());
        }
    }

    public static Cruiser newCruiser() throws BadCoordException, CoordsBadShipException {
        if (sensRandom() == 1) { //mettre bateau en colonne
            Coord tmp = case2Random(MAX_X, MAX_Y - 2);
            System.out.println("tmp : " + tmp);
            System.out.println("tmp+5 : " + ajoutSurCoord(tmp, 0, 2));
            return new Cruiser("bateau", tmp.toString(), ajoutSurCoord(tmp, 0, 2).toString());
        } else { //mettre bateau en ligne (Sur le meme Y)
            System.out.println("Bateau en ligne");
            Coord tmp = case2Random(MAX_X - 2, MAX_Y);
            System.out.println("tmp : " + tmp);
            System.out.println("tmp+5 : " + ajoutSurCoord(tmp, 2, 0));
            return new Cruiser("monPorteAvion", tmp.toString(), ajoutSurCoord(tmp, 2, 0).toString());
        }
    }

    public static Destroyer newDestroyer() throws BadCoordException, CoordsBadShipException {
        if (sensRandom() == 1) { //mettre bateau en colonne
            Coord tmp = case2Random(MAX_X, MAX_Y - 1);
            System.out.println("tmp : " + tmp);
            System.out.println("tmp+5 : " + ajoutSurCoord(tmp, 0, 1));
            return new Destroyer("bateau", tmp.toString(), ajoutSurCoord(tmp, 0, 1).toString());
        } else { //mettre bateau en ligne (Sur le meme Y)
            System.out.println("Bateau en ligne");
            Coord tmp = case2Random(MAX_X - 1, MAX_Y);
            System.out.println("tmp : " + tmp);
            System.out.println("tmp+5 : " + ajoutSurCoord(tmp, 1, 0));
            return new Destroyer("monPorteAvion", tmp.toString(), ajoutSurCoord(tmp, 1, 0).toString());
        }
    }

    public static Submarine newSubmarine() throws BadCoordException, CoordsBadShipException {
        Coord tmp = case2Random(MAX_X, MAX_Y);
        System.out.println("tmp : " + tmp);
        return new Submarine("bateau", tmp.toString());

    }

}