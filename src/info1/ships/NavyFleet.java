package info1.ships;

import java.io.Serializable;
import java.util.*;

import static info1.ships.ShipCategory.*;

/**
 * Classe définissant une flotte de navires
 */

public class NavyFleet implements INavyFleet, Serializable {

    private List<IShip> liste;
    private int taille;
    private final int taillemax = 20;

    /**
     * NB : LA SIGNATURE DU CONSTRUCTEUR DOIT ETRE RESPECTEE
     *
     * Construit une nouvelle flotte
     */


    public NavyFleet() {
        this.liste = new ArrayList<IShip>();
        this.taille = 0;
    }

    @Override
    public int remainingSize() {
        return taillemax - taille; //retourne la taille restante
    }

    @Override
    public boolean isComplete() {
        boolean b = false;

        if (this.taille == taillemax){
            b = true;
        }
        return b; //est complet ssi la flotte est de 20 en taille
    }


    @Override
    public int add(IShip IShip) {
        int a = 0;
        if(IShip.getSize() + this.taille <= taillemax){
            this.liste.add(IShip);
            this.taille = this.taille + IShip.getSize();
            a = 1;
        }
        else
        {
            a = -1;
        }
        return a; //retourne -1 si on ne peut pas ajouter le bateau | sinon retourne 1, retourne 0 en cas de problème
    }

    @Override
    public List<IShip> getShips() {
        return this.liste; //retourne la liste de la flotte
    }

    @Override
    public Set<IShip> getShips(ShipCategory shipCategory) {
        Set<IShip> liste2 = new HashSet<>();

        for(int i=0 ; i <liste.size() ; i++){
            if(liste.get(i).getCategory() == shipCategory){
                liste2.add(liste.get(i));
            }
        }
        return liste2; //retounre une liste de bateau de la categori demandé
    }

    @Override
    public boolean isBelgianConfiguration() {
        boolean b = false;

        if(this.getShips(AIRCRAFT_CARRIER).size() == 0){
            if(this.getShips(BATTLESHIP).size() == 1){
                if(this.getShips(CRUISER).size() == 2){
                    if(this.getShips(DESTROYER).size() == 3){
                        if(this.getShips(SUBMARINE).size() == 4){
                            b =true;
                        }
                    }
                }
            }
        }

       return b;
    }

    @Override
    public boolean isFrenchConfiguration() {
        boolean b = false;

        if(this.getShips(AIRCRAFT_CARRIER).size() == 1){
            if(this.getShips(BATTLESHIP).size() == 1){
                if(this.getShips(CRUISER).size() == 2){
                    if(this.getShips(DESTROYER).size() == 2){
                        if(this.getShips(SUBMARINE).size() == 1){
                            b =true;
                        }
                    }
                }
            }
        }

        return b;
    }


    @Override
    public String toString()
    {
        String res ="[\n";
        for(int i = 0;i<liste.size();i++){
            res+=liste.get(i).toString()+"\n";
        }
        res+="]";
        return res;

    }
    public boolean coordsArePossible(Ship s){
        boolean res = true;
        for(int i =0;i<this.getShips().size();i++) {
            for (int j = 0; j < this.liste.get(i).getCoords().size(); j++){
                if (s.getCoords().contains(this.liste.get(i).getCoords().get(j))) {
                    res = false;
                }
            }
        }
        return res;
    }
    public ArrayList<ICoord> getCoords(){
        ArrayList<ICoord> res = new ArrayList<>();
        for(int i = 0 ;i<this.getShips().size();i++){
            for(int j=0;j<this.getShips().get(i).getCoords().size();j++){
                res.add(this.getShips().get(i).getCoords().get(j));
            }
        }
        return res;
    }
}
