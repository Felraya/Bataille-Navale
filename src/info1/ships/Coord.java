package info1.ships;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * une implementation de l'interface ICoord manipulant des coordonnées alphanumériques comme "A1", "B6", "J3", etc.
 */

public class Coord implements ICoord, Serializable {
    char X;
    int Y;

    static char lettres[] = {' ','A','B','C','D','E','F','G','H','I','J'};

    /**
     * NB : LA SIGNATURE DU CONSTRUCTEUR DOIT ETRE RESPECTEE
     *
     * constructeur d'un objet Coord
     * @param xy la coordonnée aphanumérique sous la forme d'une chaine de caractères
     * @throws BadCoordException si la chaine de caractère ne permet pas de définir une coordonnée alphanumérique
     */
    public Coord(String xy) throws BadCoordException {
        char x;
        int y;
        try{
            x=xy.substring(0, 1).charAt(0);
            x=this.transfoMaj(x);
            y = Integer.parseInt(xy.substring(1, xy.length()));
        }catch(NumberFormatException e){
            throw new BadCoordException();
        }




        if(Arrays.binarySearch(lettres, x) < 0 || y > 10 || y < 1){
            throw new BadCoordException();
        }
        else{
            this.X = x;
            this.Y = y;
        }
    }

    @Override
    public char getAlphaX() {
        return X;
    }

    @Override
    public int getX() {
        //System.out.println("lettre : "+X+" correspond a "+String.valueOf(lettres).indexOf(this.X));
        return String.valueOf(lettres).indexOf(this.X);
    }

    @Override
    public int getY() {
        return this.Y;
    }

    @Override
    public String toString() {
        return ""+this.getAlphaX()+""+this.getY();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coord coord = (Coord) o;
        return Y == coord.Y && Objects.equals(X, coord.X);
    }
    public static boolean coordPossible(char x,int y){
        if(!(Arrays.binarySearch(lettres, x) < 0 || y > 10 || y < 1)){
            return false;
        }
        return true;
    }
    @Override
    public int hashCode() {
        return Objects.hash(X, Y);
    }


    public char transfoMaj(char ch){
        int ascii = (int) ch;
        if(ascii >= 97 && ascii<=122){
            ascii=ascii-32;
        }
        return (char) ascii;
    }
}
