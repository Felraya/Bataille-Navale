package Listener;

import Application.FenetrePlacement;
import info1.ships.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AjouterBateaux implements ActionListener{
    NavyFleet modele;
    FenetrePlacement vue;
    public AjouterBateaux(NavyFleet m,FenetrePlacement v){
        modele = m;
        vue = v;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
            Coord coordDep = new Coord(vue.getCoordDepart());
            Coord coordArr = new Coord(vue.getCoordArrive());
            int xDep = coordDep.getX();
            int yDep = coordDep.getY();
            int xArr = coordArr.getX();
            int yArr = coordArr.getY();
            Color c = new Color(135,255,232);

            String coordDepString = String.valueOf(coordDep.getAlphaX());
            coordDepString+=String.valueOf(coordDep.getY());
            String coordArrString = String.valueOf(coordArr.getAlphaX());
            coordArrString+=String.valueOf(coordArr.getY());
            try {
                System.out.println("les bateaux crée : \n aircraft : "+vue.getNbBateaux().get(4)+"\n "+vue.getNbBateaux().get(3)+"\n "+vue.getNbBateaux().get(2)+"\n "+vue.getNbBateaux().get(1)+"\n "+vue.getNbBateaux().get(0)+"\n ");
                System.out.println("les bateaux crée : \n autre : "+modele.getShips(ShipCategory.AIRCRAFT_CARRIER).size()+"\n "+modele.getShips(ShipCategory.BATTLESHIP).size()+"\n "+modele.getShips(ShipCategory.CRUISER).size()+
                        "\n "+modele.getShips(ShipCategory.DESTROYER).size()+"\n "+modele.getShips(ShipCategory.SUBMARINE).size()+"\n ");

                Ship bat = new Cruiser("a",coordDepString,coordArrString);
                if(!modele.coordsArePossible(bat)){
                    JOptionPane.showMessageDialog(vue,"chevauche un autre");
                }
                else{
                    switch(bat.getSize()) {
                        case 5:
                            System.out.println("vue = "+vue.getNbBateaux().get(4));
                            System.out.println("modele = "+modele.getShips(ShipCategory.AIRCRAFT_CARRIER).size());
                            if (vue.getNbBateaux().get(4)-modele.getShips(ShipCategory.AIRCRAFT_CARRIER).size() > 0) {
                                bat = new AircraftCarrier("a", coordDepString, coordArrString);
                                c = new Color(128, 107, 52);

                                vue.changerNbAircraftCarrierRestant(vue.getNbBateaux().get(4)-modele.getShips(ShipCategory.AIRCRAFT_CARRIER).size()-1);
                            } else {
                                JOptionPane.showMessageDialog(vue, "Vous ne pouvez plus poser de AirCraft Carrier");
                                bat = null;
                            }
                            break;
                        case 2:

                            if (vue.getNbBateaux().get(1)-modele.getShips(ShipCategory.DESTROYER).size() >0 ) {
                                bat = new Destroyer("a", coordDepString, coordArrString);
                                c = new Color(255, 215, 104);
                                vue.changerNbDestroyerRestant(vue.getNbBateaux().get(1)-modele.getShips(ShipCategory.DESTROYER).size()-1);

                            } else {
                                JOptionPane.showMessageDialog(vue, "Vous ne pouvez plus poser de Destroyer");
                                bat = null;

                            }
                            break;
                        case 3:
                            if (vue.getNbBateaux().get(2)-modele.getShips(ShipCategory.CRUISER).size() > 0 ) {
                                bat = new Cruiser("a", coordDepString, coordArrString);
                                c = new Color(204, 172, 84);
                                vue.changerNbCruiserRestant(vue.getNbBateaux().get(2)-modele.getShips(ShipCategory.CRUISER).size()-1);

                            } else {
                                JOptionPane.showMessageDialog(vue, "Vous ne pouvez plus poser de Cruiser");
                                bat = null;

                            }
                            break;
                        case 4:
                            if (vue.getNbBateaux().get(3)-modele.getShips(ShipCategory.BATTLESHIP).size() > 0) {
                                bat = new Battleship("a", coordDepString, coordArrString);
                                c = new Color(163, 138, 67);
                                vue.changerNbBattleShipRestant(vue.getNbBateaux().get(3)-modele.getShips(ShipCategory.BATTLESHIP).size()-1);

                            } else {
                                JOptionPane.showMessageDialog(vue, "Vous ne pouvez plus poser de Battleship");
                                bat = null;

                            }
                            break;
                        default:
                            if (vue.getNbBateaux().get(0)-modele.getShips(ShipCategory.SUBMARINE).size() > 0) {

                                bat = new Submarine("a", coordDepString);
                                c = new Color(255, 233, 173);
                                vue.changerNbsubMarineRestant(vue.getNbBateaux().get(0)-modele.getShips(ShipCategory.SUBMARINE).size()-1);


                            } else {
                                JOptionPane.showMessageDialog(vue, "Vous ne pouvez plus poser de Submarine");
                                bat = null;
                            }
                            break;
                    }
                        modele.add(bat);
                        vue.afficherBateau(bat,c);
                        System.out.println("modele est complet :"+modele.isComplete());
                        if(modele.isComplete()) {
                        vue.enableChanger(true);
                        vue.enableAjouter(false);
                        }
                        else {this.vue.enableChanger(false);}

                    }

                vue.viderTextField();



            } catch (CoordsBadShipException e) {
                e.printStackTrace();
            }


        } catch (BadCoordException e) {
            e.printStackTrace();
        }
    }
}
