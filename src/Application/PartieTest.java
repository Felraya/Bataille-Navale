package Application;


import info1.ships.*;
import Application.*;
import info1.network.*;
import java.util.ArrayList;


public class PartieTest{

    public static void main(String[] args){
        try {

            Ship bat1 = bat1 = new Submarine("gerge","A5");
            Ship bat2 = new Submarine("gerge","A6");
            Ship bat3 = new Submarine("gerge","A7");
            Ship bat4 = new Submarine("gerge","A8");
            NavyFleet flote = new NavyFleet();

            flote.add(bat1);
            flote.add(bat2);
            flote.add(bat3);
            flote.add(bat4);

            //Player player = 1;


            //Partie f = new Partie(flotte, player);


        } catch (BadCoordException e) {
            e.printStackTrace();
        } catch (CoordsBadShipException e) {
            e.printStackTrace();
        }







    }


}