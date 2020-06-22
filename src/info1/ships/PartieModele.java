package info1.ships;

import com.mashape.unirest.http.exceptions.UnirestException;
import info1.network.BadIdException;
import info1.network.Game;
import info1.network.Network;
import info1.network.Player;

import javax.swing.*;
import java.awt.*;

public class PartieModele {


    private NavyFleet flote;
    private Player player;
    private int touche;
    private int total;

    public PartieModele(NavyFleet f1,Player p){
        flote = f1;
        player = p;
    }

    public boolean gagner(){
        return (this.touche == 20);
    }


    public int getTouche() {
        return touche;
    }

    public void setTouche(int touche) {
        this.touche = touche;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
