package info1.ships;


import java.awt.*;

/**
 * Classe définissant un navire de catégorie "Croiseur" (taille 3)
 * @author lanoix-a
 */

public class Cruiser extends Ship {
    private Color c;

    public Cruiser(String name, String xyFront, String xyBack)
            throws BadCoordException, CoordsBadShipException {
        super(name, xyFront, xyBack);
        c = Color.RED;
    }

    @Override
    public ShipCategory getCategory() {
        return ShipCategory.CRUISER;
    }

}
