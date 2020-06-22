package info1.ships;

import java.awt.*;

/**
 * classe définissant un navire de catégorie "Cuirassé" (taille 4)
 * @author lanoix-a
 */

public class Battleship extends Ship {
    private Color c;

    public Battleship(String name, String xyFront, String xyBack)
            throws BadCoordException, CoordsBadShipException {
        super(name, xyFront, xyBack);
        c = Color.RED;
    }

    @Override
    public ShipCategory getCategory() {
        return ShipCategory.BATTLESHIP;
    }

}
