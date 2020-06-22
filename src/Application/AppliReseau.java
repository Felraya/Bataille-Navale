package Application;

import com.mashape.unirest.http.exceptions.UnirestException;
import info1.network.*;
import info1.ships.*;

public class AppliReseau {

    private static String urlServer = "http://localhost:3000/";

    public static void main(String[] args) throws BadIdException, UncompleteFleetException, UnirestException, BadCoordException {
        Game g1 = new Game(10);
        Network n1 = new Network();

        Player p1 = new Player("Theo");
        Player p2 = new Player("Erwan");

        NavyFleet nf1 = new NavyFleet();
        NavyFleet nf2 = new NavyFleet();

        g1 = n1.initNewGame(urlServer, p1, nf1);

        n1.joinGame(urlServer, g1, p2, nf2);





    }
}
