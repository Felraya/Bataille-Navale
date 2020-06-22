import info1.network.BadIdException;
import info1.ships.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShipsTest {

    IShip s;
    AircraftCarrier c;


    @Test
    public void testSubMarine() throws Exception { // bon scnénario cruiser
        try{
            s = new Submarine("theo","A5");
            List<ICoord> liste = new ArrayList<>();
            liste.add(new Coord("A5"));

            assertEquals(s.getSize(),1,"taille de 1");
            assertEquals(s.getCategory(),ShipCategory.SUBMARINE,"son type");
            assertEquals(s.getBack(),"A5","son back est le bon");
            assertEquals(s.getFront(),"A5","son front est le bon");
            assertEquals(s.getName(),"theo","son nom est le bon");
            assertEquals(s.getCoords(),liste,"ses coordonnées sont les bonnes");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testDestroyer() throws Exception { // bon scnénario cruiser
        try{
            s = new Destroyer("theo","A5","A6");
            List<ICoord> liste = new ArrayList<>();
            liste.add(new Coord("A5"));
            liste.add(new Coord("A6"));
            assertEquals(s.getSize(),2,"taille de 2");
            assertEquals(s.getCategory(),ShipCategory.DESTROYER,"son type");
            assertEquals(s.getBack(),"A5","son back est le bon");
            assertEquals(s.getFront(),"A6","son front est le bon");
            assertEquals(s.getName(),"theo","son nom est le bon");
            assertEquals(s.getCoords(),liste,"ses coordonnées sont les bonnes");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testCruiser() throws Exception { // bon scnénario cruiser
        try{
            s = new Cruiser("theo","A5","A7");
            List<ICoord> liste = new ArrayList<>();
            liste.add(new Coord("A5"));
            liste.add(new Coord("A6"));
            liste.add(new Coord("A7"));
            assertEquals(s.getSize(),3,"taille de 3");
            assertEquals(s.getCategory(),ShipCategory.CRUISER,"son type");
            assertEquals(s.getBack(),"A7","son back est le bon");
            assertEquals(s.getFront(),"A5","son front est le bon");
            assertEquals(s.getName(),"theo","son nom est le bon");
            assertEquals(s.getCoords(),liste,"ses coordonnées sont les bonnes");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testBattleship() throws Exception { // bon scnénario cruiser
        try{
            s = new Battleship("theo","A5","A8");
            List<ICoord> liste = new ArrayList<>();
            liste.add(new Coord("A5"));
            liste.add(new Coord("A6"));
            liste.add(new Coord("A7"));
            liste.add(new Coord("A8"));

            assertEquals(s.getSize(),4,"taille de 4");
            assertEquals(s.getCategory(),ShipCategory.BATTLESHIP,"son type");
            assertEquals(s.getBack(),"A5","son back est le bon");
            assertEquals(s.getFront(),"A8","son front est le bon");
            assertEquals(s.getName(),"theo","son nom est le bon");
            assertEquals(s.getCoords(),liste,"ses coordonnées sont les bonnes");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void testAircraftCarrier() throws Exception { // bon scnénario cruiser
        try{
            s = new Battleship("theo","A5","A7");
            List<ICoord> liste = new ArrayList<>();
            liste.add(new Coord("A5"));
            liste.add(new Coord("A6"));
            liste.add(new Coord("A7"));
            liste.add(new Coord("A8"));
            liste.add(new Coord("A9"));

            assertEquals(s.getSize(),3,"taille de 5");
            assertEquals(s.getCategory(),ShipCategory.BATTLESHIP,"son type");
            assertEquals(s.getBack(),"A5","son back est le bon");
            assertEquals(s.getFront(),"A9","son front est le bon");
            assertEquals(s.getName(),"theo","son nom est le bon");
            assertEquals(s.getCoords(),liste,"ses coordonnées sont les bonnes");
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    @Test
    public void test2() throws Exception { // bon scénario avec front > back
        try{
            s = new Cruiser("theo","A7","A5");
            List<ICoord> liste = new ArrayList<>();
            liste.add(new Coord("A7"));
            liste.add(new Coord("A6"));
            liste.add(new Coord("A5"));
            assertEquals(s.getSize(),3,"taille de 3");
            assertEquals(s.getCategory(),ShipCategory.CRUISER,"son type");
            assertEquals(s.getBack(),"A5","son back est le bon");
            assertEquals(s.getFront(),"A7","son front est le bon");
            assertEquals(s.getName(),"theo","son nom est le bon");
            assertEquals(s.getCoords(),liste,"ses coordonnées sont les bonnes");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void test3() throws Exception { // test aux limites
        try{
            s = new AircraftCarrier("theo","A1","A5");
            List<ICoord> liste = new ArrayList<>();

            liste.add(new Coord("A1"));
            liste.add(new Coord("A2"));
            liste.add(new Coord("A3"));
            liste.add(new Coord("A4"));
            liste.add(new Coord("A5"));
            assertEquals(s.getSize(),5,"taille de 5");
            assertEquals(s.getCategory(),ShipCategory.AIRCRAFT_CARRIER,"son type");
            assertEquals(s.getBack(),"A1","son back est le bon");
            assertEquals(s.getFront(),"A5","son front est le bon");
            assertEquals(s.getName(),"theo","son nom est le bon");
            assertEquals(s.getCoords(),liste,"ses coordonnées sont les bonnes");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void test5() throws Exception { // test aux limites
        try{
            s = new AircraftCarrier("theo","A6","A10");
            List<ICoord> liste = new ArrayList<>();
            liste.add(new Coord("A6"));
            liste.add(new Coord("A7"));
            liste.add(new Coord("A8"));
            liste.add(new Coord("A9"));
            liste.add(new Coord("A10"));

            assertEquals(s.getSize(),5,"taille de 5");
            assertEquals(s.getCategory(),ShipCategory.AIRCRAFT_CARRIER,"son type");
            assertEquals(s.getBack(),"A6","son back est le bon");
            assertEquals(s.getFront(),"A10","son front est le bon");
            assertEquals(s.getName(),"theo","son nom est le bon");
            assertEquals(s.getCoords(),liste,"ses coordonnées sont les bonnes");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void test6() { // test exceptions
        Assertions.assertThrows(BadCoordException.class,
                ()->{
                    s = new Cruiser("theo","15","AA6");
                });
    }
    @Test
    public void test10() throws Exception { // test exceptions
        Assertions.assertThrows(info1.ships.BadCoordException.class,
                ()->{
                    s = new Cruiser("theo","I6","K6");
                });
    }

    @Test
    public void test7() throws Exception { // test exceptions, bateau trop grand
        Assertions.assertThrows(CoordsBadShipException.class,
                ()->{
                    s = new Cruiser("theo","A2","A7");
                });
    }
    @Test
    public void test8() throws Exception { // test exceptions, bateau en biais
        Assertions.assertThrows(CoordsBadShipException.class,
                ()->{
                    s = new Cruiser("theo","A2","C4");
                });
    }
    
}

