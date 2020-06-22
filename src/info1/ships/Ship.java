package info1.ships;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.lang.*;
import java.util.Objects;

/**
 * une implémentation "abstraite" d'un bateau quelconque, de taille indéterminée
 */


public abstract class Ship implements IShip, Serializable {

    // TODO
    private List<ICoord> sesCoords;
    private String name;
    private Color c;
    private Coord front;
    private Coord back;
    /**
     * NB : LA SIGNATURE DU CONSTRUCTEUR DOIT ETRE RESPECTEE
     *
     * construit un bateau quelconque
     * @param name le nom du bateau
     * @param ayFront la coordonnée de la proue du bateau
     * @param ayBack la coordonnée de la poupe du bateau
     * @throws BadCoordException si l'une des coordonnées données ne définit pas une coordonnée alphanumérique correcte
     * @throws CoordsBadShipException si les coordonnées données ne permettent pas de définir un bateau correct :
     *  une ligne, une colonne, de la bonne taille, etc.
     */
    public Ship(String name, String ayFront, String ayBack)
            throws BadCoordException, CoordsBadShipException { // TODO badCoordException
        this.name = name;
        sesCoords = new ArrayList<>();




            front = new Coord(ayFront);
            back = new Coord(ayBack);




        if(front.getX()==back.getX()){

                if(this.getSize()>5){
                    throw new CoordsBadShipException(); // taille trop grande
                }
                    if(front.getY()-back.getY()>0){ //tete plus grande que la queue
                        for(int i = front.getY();i>=back.getY();i--){
                            String tempsS = "";
                            tempsS+=front.getAlphaX();
                            tempsS+=i;

                            Coord temp = new Coord(tempsS);

                            sesCoords.add(temp);
                        }
                    }
                else{ //queue plus grande que la tete
                    for(int i = front.getY();i<=back.getY();i++){
                        String tempsS = "";
                        tempsS+=front.getAlphaX();
                        tempsS+=i;

                        Coord temp = new Coord(tempsS);
                        sesCoords.add(temp);
                    }
                }



        }
        else if(front.getY()==back.getY()){
            if(this.getSize()>5){
                throw new CoordsBadShipException(); // taille trop grande
            }
            if((front.getX()-back.getX())>0) {
                for (char i = front.getAlphaX(); i >= back.getAlphaX(); i--) {
                    String tempsS = "";
                    tempsS += i;
                    tempsS += front.getY();
                    Coord temp = new Coord(tempsS);
                    sesCoords.add(temp);
                }
            }
            else{
                for (char i = front.getAlphaX(); i <= back.getAlphaX(); i++) {
                    String tempsS = "";
                    tempsS += i;
                    tempsS += front.getY();
                    Coord temp = new Coord(tempsS);
                    sesCoords.add(temp);
                }
            }
        }
        else{
            throw new CoordsBadShipException(); // coords pas alignés
        }
        // TODO
    }




    @Override
    public List<ICoord> getCoords() {
        return sesCoords;
    }

    @Override
    public ICoord getFront() { //front est à l'indice 0 de la liste
        return front;
    }

    @Override
    public ICoord getBack() { //front est à l'indice MAX de la liste

        return back;
    }

    @Override
    public String getName() {

        return this.name;
    }

    @Override
    public int getSize() {
        if(this.getFront().getX()==this.getBack().getX()){
            return (Math.abs(this.getFront().getY()-this.getBack().getY())+1);
        }
        else{
            return (Math.abs(this.getFront().getX()-this.getBack().getX())+1);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ship ship = (Ship) o;
        return Objects.equals(sesCoords, ship.sesCoords) &&
                Objects.equals(name, ship.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(sesCoords, name);
    }

    @Override
    public String toString() {
        String res ="[  "+this.getName()+" : ";
        for(int i = 0;i<sesCoords.size();i++){
            res+=sesCoords.get(i).toString()+"  ";
        }
        res+="]";
        return res;
    }

    public Color getColor(){
        return Color.BLUE;
    }
}
