package info1.ships;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Bataille {
    private NavyFleet flotte1;
    private ArrayList<Coord> coordLoupe;
    private ArrayList<Coord> coordTouche;
    private ArrayList<Coord> dejaJoue;
    private int touche;
    private int totalTirs;
    public  Bataille(NavyFleet f){
        flotte1 = f;
        coordLoupe = new ArrayList<>();
        coordTouche = new ArrayList<>();
        dejaJoue = new ArrayList<>();

    }
    public boolean possible(String coordString) throws BadCoordException {
        for(int i=0; i<dejaJoue.size(); i++){
            if(dejaJoue.contains(new Coord(coordString))){
                return false;
            }
        }
        return true;
    }

    public NavyFleet getBateaux() {
        return flotte1;
    }

    public ArrayList<Coord> getDejaJoue() {
        return dejaJoue;
    }

    public int getTouche() {
        return coordTouche.size();
    }
    public int getLoupe() {
        return coordLoupe.size();
    }
    public int getTotalTirs(){
        return dejaJoue.size();
    }

    public void setDejaJoue(Coord c) {
        this.dejaJoue.add(c);
    }
    public void setCoordLoupe(Coord c) {
        this.coordLoupe.add(c);
    }
    public void setCoordTouche(Coord c) {
        this.coordTouche.add(c);
    }
    public void saveFlotte(){
        FileOutputStream file = null;
        try {
            file = new FileOutputStream("save.csv");
            ObjectOutputStream oos = new ObjectOutputStream(file);
            oos.writeObject(this.flotte1);
            oos.flush();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }/*
    public NavyFleet chargerFlotte(int i){
        FileInputStream l_fis = null;
        try {
            l_fis = new FileInputStream("save.csv");
            ObjectInputStream l_ois = new ObjectInputStream(l_fis);
            NavyFleet[] data_objet=(NavyFleet[])l_ois.readObject();

          //  tableau_objets[0]=data_objet[0];
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }*/
    public static void main(String[] args){
        NavyFleet n = new NavyFleet();
        try {
            n.add(new Cruiser("theo","A1","A3"));
        } catch (BadCoordException e) {
            e.printStackTrace();
        } catch (CoordsBadShipException e) {
            e.printStackTrace();
        }
        Bataille bat = new Bataille(n);
        bat.saveFlotte();
    }


}
