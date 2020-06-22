package Listener;

import Application.CompositionFlotte;
import Application.FenetrePlacement;
import info1.ships.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlacementAuto implements ActionListener {
    private FenetrePlacement vue;

    public PlacementAuto(FenetrePlacement vue) {
        this.vue = vue;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        int rd =0; //choisi le type de bateau a placer

        vue.reset();


        NavyFleet f = new NavyFleet();



        try{
                for(int i=0; i<vue.getNbrAircraftCarrier(); i++){
                    AircraftCarrier tmpAir = vue.newAircraft();
                    while(!f.coordsArePossible(tmpAir)){
                        tmpAir = vue.newAircraft();
                    }
                    f.add(tmpAir);
                    vue.afficherBateau(tmpAir,new Color(128, 107, 52));
                }

                for(int i=0; i<vue.getNbrBattleship(); i++){
                    Battleship tmpBat = vue.newBattleship();
                    while(!f.coordsArePossible(tmpBat)){
                        tmpBat = vue.newBattleship();
                    }
                    f.add(tmpBat);
                    vue.afficherBateau(tmpBat,new Color(163, 138, 67));
                }

                for(int i=0; i<vue.getNbrSubMarine(); i++){
                    Submarine tmpSub = vue.newSubmarine();
                    while(!f.coordsArePossible(tmpSub)){
                        tmpSub = vue.newSubmarine();
                    }
                    f.add(tmpSub);
                    vue.afficherBateau(tmpSub,new Color(255, 233, 173));
                }


                for(int i=0; i<vue.getNbrDestroyer(); i++){
                    Destroyer tmpDes = vue.newDestroyer();
                    while (!f.coordsArePossible(tmpDes)){
                        tmpDes = vue.newDestroyer();
                    }
                    f.add(tmpDes);
                    vue.afficherBateau(tmpDes,new Color(255, 215, 104));
                }


                for(int i=0; i<vue.getNbrCruiser(); i++){
                    Cruiser tmpCru2 = vue.newCruiser();
                    while(!f.coordsArePossible(tmpCru2)){
                        tmpCru2 = vue.newCruiser();
                    }
                    f.add(tmpCru2);
                    vue.afficherBateau(tmpCru2,new Color(204, 172, 84));
                }
        }
        catch (BadCoordException e1) {
            e1.printStackTrace();
        } catch (CoordsBadShipException e1) {
            e1.printStackTrace();
        }



        vue.enableChanger(true);
        System.out.println("NavyFleet créer" + f);
        vue.setFloot(f);





    }




    public int random(int min, int max){
        return (int) (min + Math.random()*(max+1-min));
    }
}
