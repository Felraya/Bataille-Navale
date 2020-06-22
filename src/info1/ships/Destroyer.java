package info1.ships;


import java.awt.*;

/**
 * classe définissant un navire de catégorie "Tropilleur" (taille 2)
 * @author lanoix-a
 */

public class Destroyer extends Ship {
    private Color c;

    public Destroyer(String name, String xyFront, String xyBack)
            throws BadCoordException, CoordsBadShipException {
        super(name, xyFront, xyBack);
        c = Color.RED;
    }

    @Override
    public ShipCategory getCategory() {
        return ShipCategory.DESTROYER;
    }

}
